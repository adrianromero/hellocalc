//    HelloCalc (Calculator) is a JavaFX calculator
//    Copyright (C) 2016 Adrián Romero Corchado.
//
//    This file is part of HelloCalc
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.
package com.adr.hellocalc;


import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
 * @author adrian
 */
public class ButtonAnimation {
    
    public static void addPressedCircle(Control n) {
        addPressedCircle(n, Color.gray(1.0, 0.25), Color.gray(1.0, 0.7));
    }
    
    public static void addPressedCircle(Control n, Paint p, Paint s) {
        
        n.setOnMousePressed(ev1 -> {
            Point2D point = new Point2D(n.getWidth() / 2.0, n.getHeight() / 2.0);
            Parent root = n;
            while(!(root instanceof AnchorPane)) {
                point = root.localToParent(point);
                root = root.getParent();
            }            
            AnchorPane rootanchor = (AnchorPane) root;
            
            Circle c = new Circle(point.getX(), point.getY(), 10.0, p);
            c.setStroke(s);
            c.setStrokeWidth(0.15);
            c.setMouseTransparent(true);
            rootanchor.getChildren().add(c);
            
            Mutable<Boolean> continueTillEnd = new Mutable<>();
            continueTillEnd.value = false;    
            
            // exit transition
            FadeTransition outtransition = new FadeTransition(Duration.millis(400), c);
            outtransition.setFromValue(1.0);
            outtransition.setToValue(0.0);      
            outtransition.setInterpolator(Interpolator.EASE_IN);      
            outtransition.setOnFinished(ev -> {
                rootanchor.getChildren().remove(c);
            });            
                        
            // enter transtion
            FadeTransition enterfade = new FadeTransition(Duration.millis(200));
            enterfade.setFromValue(0.5);
            enterfade.setToValue(1.0);
            enterfade.setInterpolator(Interpolator.EASE_IN);            
            ScaleTransition enterscale = new ScaleTransition(Duration.millis(200));
            enterscale.setFromX(0.5);
            enterscale.setFromY(0.5);
            enterscale.setToX(6.0);
            enterscale.setToY(6.0);
            enterscale.setInterpolator(Interpolator.EASE_IN);
            ParallelTransition entertransition = new ParallelTransition(c, enterfade, enterscale);
            entertransition.setOnFinished(ev4 -> {
                if (continueTillEnd.value) {
                    outtransition.play();
                }
            });
            entertransition.play();
                
            Mutable<EventHandler<MouseEvent>> exit = new Mutable<>();
            exit.value = (ev2) -> {                
                if (entertransition.getStatus() == Animation.Status.RUNNING) {
                    continueTillEnd.value = true;
                } else { 
                    outtransition.play();
                }
                n.removeEventFilter(MouseEvent.MOUSE_RELEASED, exit.value);
            };
            
            n.addEventFilter(MouseEvent.MOUSE_RELEASED, exit.value);
        });     
    }

    private static class Mutable<T> {
        public T value;
    }    
}

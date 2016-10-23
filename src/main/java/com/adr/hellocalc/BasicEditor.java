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

import java.util.Deque;
import java.util.LinkedList;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 *
 * @author adrian
 */
public class BasicEditor {

    private final ScrollableEditor text;
    private final Deque<String> textqueue = new LinkedList<>();
    private final Deque<String> backtextqueue = new LinkedList<>();
    
    private final Transition cleartransition;

    public BasicEditor() {
        text = new ScrollableEditor(32.0, 18.0);

        FadeTransition ft2 = new FadeTransition(Duration.millis(300));
        ft2.setFromValue(1.0);
        ft2.setToValue(0.0);
        
        TranslateTransition tr2 = new TranslateTransition(Duration.millis(300));
        tr2.setFromY(0.0);
        tr2.setToY(-35.0);
        tr2.setInterpolator(Interpolator.EASE_OUT);
        
        cleartransition = new ParallelTransition(text.getNode(), ft2, tr2);
        cleartransition.setOnFinished(e -> {
            text.setText(getExpression());
            text.getNode().setOpacity(1.0);
            text.getNode().setTranslateY(0.0);
        });        
    }

    public void addCommand(String str) {
        if ("<CLEAR>".equals(str)) { // Delete all entries.
            if (!textqueue.isEmpty()) {
                backtextqueue.clear();
                backtextqueue.addAll(textqueue);
            }
            textqueue.clear();
            cleartransition.playFromStart();
        } else if ("<DEL>".equals(str)) { // Delete last entry 
            textqueue.pollLast();
            text.setText(getExpression());
        } else if ("<REV>".equals(str)) { // Revert
            Deque<String> swaptextqueue = new LinkedList<>(textqueue);      
            textqueue.clear();
            textqueue.addAll(backtextqueue);
            backtextqueue.clear();
            backtextqueue.addAll(swaptextqueue);
            text.setText(getExpression());
        } else {
            textqueue.add(str);
            text.setText(getExpression());
        }      
    }

    public String getExpression() {
        StringBuilder b = new StringBuilder();
        for (String part : textqueue) {
            b.append(part);
        }
        return b.toString();
    }

    public Node getNode() {
        return text.getNode();
    }  
}

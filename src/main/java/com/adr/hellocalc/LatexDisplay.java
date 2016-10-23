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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

/**
 *
 * @author adrian
 */
public class LatexDisplay implements Display {
    
    private static final Logger LOG = Logger.getLogger(LatexDisplay.class.getName());

    private final ScrollableLatexList maindisplay;
    private final VBox viewdisplay;
    private final ResizableLatex latexresult;
    private final Text mode0;
    private final Transition displaytransition;

    private final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
    private final NumberFormat simpleFormat = new DecimalFormat("0.#########", symbols);
    private final NumberFormat expFormat = new DecimalFormat("0.#########E00", symbols);

    public LatexDisplay() {
        
        mode0 = new Text();
        mode0.getStyleClass().add("calcindicator");
        HBox indicatorsbox = new HBox(new TextFlow(mode0));
        indicatorsbox.setPadding(new Insets(5, 5, 5, 5));
        VBox.setVgrow(indicatorsbox, Priority.NEVER);
        
        maindisplay = new ScrollableLatexList();       
        maindisplay.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox.setVgrow(maindisplay, Priority.SOMETIMES);

        latexresult = new ResizableLatex(1.0, 3.0, HPos.RIGHT);
        
        ScrollableLatexList viewresult = new ScrollableLatexList();
        VBox.setVgrow(viewresult, Priority.NEVER);     
        viewresult.addLatex(latexresult);
    
        viewdisplay = new VBox(indicatorsbox, maindisplay, viewresult);
        
        // Transition
        FadeTransition ft2 = new FadeTransition(Duration.millis(150));
        ft2.setFromValue(0.0);
        ft2.setToValue(1.0);       
        TranslateTransition tr2 = new TranslateTransition(Duration.millis(150));
        tr2.setFromY(35.0);
        tr2.setToY(0.0);
        tr2.setInterpolator(Interpolator.EASE_OUT); 
        displaytransition = new ParallelTransition(viewresult, ft2, tr2);         
    }


    @Override
    public void updateResult(Script calc, Object result) { 
        // if result is undefined the it means there has not been any result
        // So no need to update the display for the results just the modes.        
        if (result != null) {
            formatLatexResult(latexresult, result);
            displaytransition.playFromStart();
            
            Object latex = calc.exec("calculator.latex()");
            if (latex instanceof List) {
                List listlatex = (List) latex;
                maindisplay.clearLatex();
                for (Object l: listlatex) {
                    ResizableLatex nlatex = new ResizableLatex(1.0, 2.5); 
                    maindisplay.addLatex(nlatex);
                    nlatex.setLatex(formatLatex(l), Color.DARKBLUE);   
                }
                maindisplay.setVvalue(maindisplay.getVmin());
            } else {
                LOG.warning("Calculator function calculator.latex() must return an array.");   
            }
        }
        // Mode
        Object mode = calc.exec("calculator.mode()");
        if (mode instanceof List) {
            List listmode = (List) mode;
            if (listmode.size() > 0) {
                setTextResult(mode0, listmode.get(0).toString());
            }
        } else {
            LOG.warning("Calculator function calculator.mode() must return an array.");
        }
    }

    @Override
    public Node getNode() {
        return viewdisplay;
    }

    private void setTextResult(Text t, String result) {
        t.setText(result);
        t.getStyleClass().removeAll("displayerror", "displayresult");
        t.getStyleClass().add("displayresult");
    }

    private String formatLatex(Object obj) {
        if (obj.equals("")) {
            return null;
        } else if (obj instanceof Throwable) {
            return "SYNTAX\\\\ ERROR";
        } else {
            return obj.toString();
        }
    }
    
    private void formatLatexResult(ResizableLatex t, Object obj) {
        if (obj.equals("")) {
            t.setLatex(null);
        } else if (obj instanceof Number) {
            double d = ((Number) obj).doubleValue();
            if (Double.isInfinite(d)) {
                t.setLatex("INFINITY", Color.RED);
            } else if (Double.isNaN(d)) {
                t.setLatex("NOT\\ A\\ NUMBER", Color.RED);
            } else if (d == Double.MAX_VALUE || d == Double.MIN_VALUE) {
                t.setLatex("OVERFLOW", Color.RED);
            } else if (d == 0.0) {
                t.setLatex("=" + simpleFormat.format(d));
            } else if (d < -9999999999.0 || d > 9999999999.0 || (d >= -0.0000000001 && d <= 0.0000000001)) {
                String expresult = expFormat.format(d);
                t.setLatex("=" + (expresult.contains("E-") ? expresult : expresult.replace("E", "E+")));
            } else {
                t.setLatex("=" + simpleFormat.format(d));
            }
        } else if (obj instanceof Throwable) {
            t.setLatex("SYNTAX\\ ERROR", Color.RED);
        } else {
            t.setLatex("=" + obj.toString());
        }
    } 
}

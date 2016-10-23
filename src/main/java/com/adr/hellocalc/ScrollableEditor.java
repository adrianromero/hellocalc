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

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author adrian
 */
public class ScrollableEditor {
    
    private static final String FONT_NAME = "ROBOTO MONO BOLD";   
    
    private final ScrollPane container;
    private final VBox box;
    private final double maxfontsize;
    private final double minfontsize;
    
    private String text = null;
    
    public ScrollableEditor(double maxfontsize, double minfontsize) {

        this.box = new VBox();
        this.container = new ScrollPane(this.box);
        this.maxfontsize = maxfontsize;
        this.minfontsize = minfontsize;
        // this.setAlignment(Pos.TOP_RIGHT);
        container.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        container.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        container.setFocusTraversable(false);
        container.setPannable(true);
        container.setBackground(Background.EMPTY);
        container.setBorder(Border.EMPTY);
        container.setPadding(new Insets(0.0));
        container.setFitToHeight(true);   
        container.setPrefHeight(60.0);  
        container.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            setText(text);
        });    
    }
    
    public Node getNode() {
        return container;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
        box.getChildren().clear();
        Text t1 = createText();
        box.getChildren().add(t1);

        printTextSize(t1, text, container.getWidth(), maxfontsize, minfontsize);
        splitText(box, t1, container.getWidth(), minfontsize);  
        Platform.runLater(() -> {
            container.setVvalue(container.getVmax());
        });
    }
    
    private static Text createText() {
        Text t = new Text();        
        t.getStyleClass().add("displayedit");
        return t;
    }

    private static void splitText(VBox parent, Text text, final double maxwidth, double minFontSize) {
        StringBuilder b = new StringBuilder();
        while(text.getLayoutBounds().getWidth() > maxwidth) {
            String s = text.getText();
            b.insert(0, s.charAt(s.length() - 1));
            text.setText(s.substring(0, s.length() - 1));
        }
        if (b.length() > 0) {
            Text t1 = createText();
            t1.setFont(new Font(FONT_NAME, minFontSize));
            t1.setText(b.toString());
            parent.getChildren().add(t1);
            splitText(parent, t1, maxwidth, minFontSize);
        }        
    }
    
    private static void printTextSize(final Text text, final String t, final double maxwidth, double maxFontSize, double minFontSize) {
        double actualSize = maxFontSize;
        text.setFont(new Font(FONT_NAME, actualSize));
        text.setText(t);
        while (text.getLayoutBounds().getWidth() > maxwidth && maxFontSize > minFontSize) {
            maxFontSize -= 0.1;
            text.setFont(new Font(FONT_NAME, maxFontSize));
        }
    }     
}

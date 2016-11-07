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

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;

/**
 *
 * @author adrian
 */
public class ScrollableLatexList extends ScrollPane {

    private static final Logger LOG = Logger.getLogger(ScrollableLatexList.class.getName());
    
    private final VBox view;
    private final Set<ResizableLatex> latexs = new HashSet<>();

    public ScrollableLatexList() {

        view = new VBox();
        setContent(view);
        
        // this.setAlignment(Pos.TOP_RIGHT);
        setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setFocusTraversable(false);
        setPannable(true);
        setBackground(Background.EMPTY);
        setBorder(Border.EMPTY);
        setPadding(new Insets(0.0));
        setFitToHeight(true);
        widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                adjustEditorSize(newValue.doubleValue());
            }
        });        
    }
    
    public void clearLatex() {
        view.getChildren().removeAll(latexs);
        latexs.clear();
        setHvalue(getHmin());        
    }
    
    public void addLatex(ResizableLatex latex) {
        latexs.add(latex);
        view.getChildren().add(latex);
        latex.adjustEditorSize(getWidth());
        setHvalue(getHmin());        
    }

    private void adjustEditorSize(double width) {
        for(ResizableLatex latex: latexs) {
            latex.adjustEditorSize(width);
        }
        setHvalue(getHmin());
    }
}

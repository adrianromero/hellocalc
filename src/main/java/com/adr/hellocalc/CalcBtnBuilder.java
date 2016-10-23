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

import javafx.scene.control.Button;

/**
 *
 * @author adrian
 */
public class CalcBtnBuilder {

    public CalcButton createCalcButton() {
        return new CalcButton();
    }

    public static class CalcButton {

        private final Button btn;

        private CalcButton() {
            btn = new Button();
            btn.getStyleClass().add("buttonkeyboard");
            btn.setFocusTraversable(false);
            btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            ButtonAnimation.addPressedCircle(btn);
        }

        public CalcButton addClasses(String... classes) {
            btn.getStyleClass().addAll(classes);
            return this;
        }

        public CalcButton addVisitor(VisitorButton visitor) {
            visitor.visit(btn);
            return this;
        }

        public Button build() {
            return btn;
        }
    }
}

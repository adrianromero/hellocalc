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

/* global KEYBOARDBUILDER, CALCBUTTONBUILDER */
(function () {
    var keyboard = KEYBOARDBUILDER.createKeyboard(6, 6);

    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton()
            .addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("<EXEC>calculator.radiansfactorswitch()")))
            .addVisitor(KEYBOARDBUILDER.createLabeler("calculator.radiansmodenext"))
            .addClasses("functionkeyboard", "textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton()
            .addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("sin", "sin("),
                    KEYBOARDBUILDER.createCommand("sin\u207B\u00B9", "asin(")))
            .addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton()
            .addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("cos", "cos("),
                    KEYBOARDBUILDER.createCommand("cos\u207B\u00B9", "acos(")))
            .addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton()
            .addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("tan", "tan("),
                    KEYBOARDBUILDER.createCommand("tan\u207B\u00B9", "atan(")))
            .addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton()
            .addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("log", "log("),
                    KEYBOARDBUILDER.createCommand("10\u207F", "pow10(")))
            .addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton()
            .addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("ln", "ln("),
                    KEYBOARDBUILDER.createCommand("e\u207F", "exp(")))
            .addClasses("textkeyboard").build());

    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("("))).addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand(","))).addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand(")"))).addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("\u221Ax", "sqrt("))).addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("x\u207F", "pow("))).addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("x!", "fac("))).addClasses("textkeyboard").build());

    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("INV", "<KB>INV"))).addClasses("functionkeyboard", "textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("7"))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("8"))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("9"))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("DEL", "<DEL>"))).addClasses("functionkeyboard", "textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("AC", "<EXEC>calculator.reset();", "<CLEAR>"))).addClasses("functionkeyboard", "textkeyboard").build());

    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("e"))).addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("4"))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("5"))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("6"))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("*"))).build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("/"))).build());

    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("\u03C0", "pi"))).addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("1"))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("2"))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("3"))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("+"))).build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("-"))).build());

    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("ANS"))).addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("0"))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("."))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("EXX", "E"))).addClasses("graykeyboard", "textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("ENTER", "<EXEC>calculator.eval(%%%%%%)", "<CLEAR>"))).addClasses("execkeyboard", "textkeyboard").build(), 2);

}());

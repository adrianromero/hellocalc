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

    var keyboard = KEYBOARDBUILDER.createKeyboard(6, 7);
    
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("0", "<KB>0"))).addClasses("modekeyboard", "modekeyboardselected").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("1", "<KB>1"))).addClasses("modekeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("2", "<KB>2"))).addClasses("modekeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("3", "<KB>3"))).addClasses("modekeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("4", "<KB>4"))).addClasses("modekeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("5", "<KB>5"))).addClasses("modekeyboard").build());

    

    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton()
            .addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("<EXEC>calculator.radiansfactorswitch()")))
            .addVisitor(KEYBOARDBUILDER.createLabeler("calculator.radiansmodenext"))
            .addClasses("functionkeyboard", "textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton()
            .addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("sin", "sin("),
                    KEYBOARDBUILDER.createCommand("asin", "asin(")))
            .addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton()
            .addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("cos", "cos("),
                    KEYBOARDBUILDER.createCommand("acos", "acos(")))
            .addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton()
            .addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("tan", "tan("),
                    KEYBOARDBUILDER.createCommand("atan", "atan(")))
            .addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(
            KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("log10", "log10("),
            KEYBOARDBUILDER.createCommand("10\u207F", "pow10("))).addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton()
            .addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("log", "log("),
                    KEYBOARDBUILDER.createCommand("e\u207F", "exp(")))
            .addClasses("textkeyboard").build());

    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("INV", "<KB>INV"))).addClasses("functionkeyboard", "textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("("), KEYBOARDBUILDER.createCommand("["))).addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand(")"), KEYBOARDBUILDER.createCommand("]"))).addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(
            KEYBOARDBUILDER.createCommand("\u221Ax", "sqrt("),
            KEYBOARDBUILDER.createCommand(","))).addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("x\u00B2", "^2"))).addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("x\u207F", "^"))).addClasses("textkeyboard").build());

    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("i"), KEYBOARDBUILDER.createCommand("x"))).addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("7"))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("8"))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("9"))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("DEL", "<DEL>"))).addClasses("functionkeyboard", "textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("AC", "<EXEC>calculator.reset();", "<CLEAR>"))).addClasses("functionkeyboard", "textkeyboard").build());

    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("e"), KEYBOARDBUILDER.createCommand("f"))).addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("4"))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("5"))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("6"))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("*"))).build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("/"))).build());

    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("\u03C0", "pi"), KEYBOARDBUILDER.createCommand("="))).addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("1"))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("2"))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("3"))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("+"))).build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("-"))).build());

    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("ANS"))).addClasses("textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("0"))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("."))).addClasses("graykeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("EXX", "E"))).addClasses("graykeyboard", "textkeyboard").build());
    keyboard.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("ENTER", "<EXEC>calculator.eval(\"%%%%%%\")", "<CLEAR>"))).addClasses("execkeyboard", "textkeyboard").build(), 2);
    
    
    var keyboard2 = KEYBOARDBUILDER.createKeyboard(12, 2);
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("0", "<KB>0"))).addClasses("modekeyboard").build(), 2);
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("1", "<KB>1"))).addClasses("modekeyboard", "modekeyboardselected").build(), 2);
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("2", "<KB>2"))).addClasses("modekeyboard").build(), 2);
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("3", "<KB>3"))).addClasses("modekeyboard").build(), 2);
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("4", "<KB>4"))).addClasses("modekeyboard").build(), 2);
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("5", "<KB>5"))).addClasses("modekeyboard").build(), 2);

    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("1", "<KB>0"))).addClasses("execkeyboard", "textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("1", "<KB>0"))).addClasses("execkeyboard", "textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("1", "<KB>0"))).addClasses("execkeyboard", "textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("1", "<KB>0"))).addClasses("execkeyboard", "textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("1", "<KB>0"))).addClasses("execkeyboard", "textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("1", "<KB>0"))).addClasses("execkeyboard", "textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("1", "<KB>0"))).addClasses("execkeyboard", "textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("1", "<KB>0"))).addClasses("execkeyboard", "textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("DEL", "<DEL>"))).addClasses("functionkeyboard", "textkeyboard").build(), 2);
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("AC", "<EXEC>calculator.reset();", "<CLEAR>"))).addClasses("functionkeyboard", "textkeyboard").build(), 2);


    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("1"), KEYBOARDBUILDER.createCommand("!"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("2"), KEYBOARDBUILDER.createCommand("@"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("3"), KEYBOARDBUILDER.createCommand("#"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("4"), KEYBOARDBUILDER.createCommand("$"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("5"), KEYBOARDBUILDER.createCommand("%"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("6"), KEYBOARDBUILDER.createCommand("^"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("7"), KEYBOARDBUILDER.createCommand("&"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("8"), KEYBOARDBUILDER.createCommand("*"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("9"), KEYBOARDBUILDER.createCommand("("))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("0"), KEYBOARDBUILDER.createCommand(")"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("-"), KEYBOARDBUILDER.createCommand("_"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("="), KEYBOARDBUILDER.createCommand("+"))).addClasses("textkeyboard").build());

    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("q"), KEYBOARDBUILDER.createCommand("Q"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("w"), KEYBOARDBUILDER.createCommand("W"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("e"), KEYBOARDBUILDER.createCommand("E"))).addClasses("textkeyboard").build()); 
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("r"), KEYBOARDBUILDER.createCommand("R"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("t"), KEYBOARDBUILDER.createCommand("T"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("y"), KEYBOARDBUILDER.createCommand("Y"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("u"), KEYBOARDBUILDER.createCommand("U"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("i"), KEYBOARDBUILDER.createCommand("I"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("o"), KEYBOARDBUILDER.createCommand("O"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("p"), KEYBOARDBUILDER.createCommand("P"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("["), KEYBOARDBUILDER.createCommand("{"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("]"), KEYBOARDBUILDER.createCommand("}"))).addClasses("textkeyboard").build());

    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("a"), KEYBOARDBUILDER.createCommand("A"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("s"), KEYBOARDBUILDER.createCommand("S"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("d"), KEYBOARDBUILDER.createCommand("D"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("f"), KEYBOARDBUILDER.createCommand("F"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("g"), KEYBOARDBUILDER.createCommand("G"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("h"), KEYBOARDBUILDER.createCommand("H"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("j"), KEYBOARDBUILDER.createCommand("J"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("k"), KEYBOARDBUILDER.createCommand("K"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("l"), KEYBOARDBUILDER.createCommand("L"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand(";"), KEYBOARDBUILDER.createCommand(":"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("'"), KEYBOARDBUILDER.createCommand("\""))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("\\"), KEYBOARDBUILDER.createCommand("|"))).addClasses("textkeyboard").build());

    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("\u21E7", "<KB>INV"))).addClasses("graykeyboard", "textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("z"), KEYBOARDBUILDER.createCommand("Z"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("x"), KEYBOARDBUILDER.createCommand("X"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("c"), KEYBOARDBUILDER.createCommand("C"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("v"), KEYBOARDBUILDER.createCommand("V"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("b"), KEYBOARDBUILDER.createCommand("B"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("n"), KEYBOARDBUILDER.createCommand("N"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("m"), KEYBOARDBUILDER.createCommand("M"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand(","), KEYBOARDBUILDER.createCommand("<"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("."), KEYBOARDBUILDER.createCommand(">"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("/"), KEYBOARDBUILDER.createCommand("?"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("\u21E7", "<KB>INV"))).addClasses("graykeyboard", "textkeyboard").build());

    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("1"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("2"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("3"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("4"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("SPACE", " "))).addClasses("textkeyboard").build(), 4);
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("1"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("2"))).addClasses("textkeyboard").build());
    keyboard2.addButton(CALCBUTTONBUILDER.createCalcButton().addVisitor(KEYBOARDBUILDER.createCommander(KEYBOARDBUILDER.createCommand("ENTER", "<EXEC>calculator.eval(\"%%%%%%\");", "<CLEAR>"))).addClasses("execkeyboard", "textkeyboard").build(), 2);

}());

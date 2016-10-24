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

/* global math */
(function () {

    var scope = {};

    // system namespace
    calculator = {};// 
    // radians to labels switch    
    calculator.radiansmode = "RAD";
    calculator.radiansmodenext = "DEG";
    calculator.radiansfactor = 1.0;
    calculator.radiansfactorswitch = function () {
        if ("RAD" === calculator.radiansmode) {
            calculator.radiansmode = "DEG";
            calculator.radiansfactor = Math.PI / 180.0;
            calculator.radiansmodenext = "RAD";
        } else {
            calculator.radiansmode = "RAD";
            calculator.radiansfactor = 1.0;
            calculator.radiansmodenext = "DEG";
        }
    };
    calculator.mode = function () {
        return [calculator.radiansmode];
    };
    calculator.latex = function () {
        return calculator.latexmodel;
    };
    calculator.reset = function () {
        calculator.latexmodel = [];
        calculator.EXP = null;
        ANS = scope.ANS = 0;
        return "";
    };
    calculator.eval = function (strexpression) {
        if (strexpression === "") {
            return;
        }

        calculator.latexmodel = [];
        calculator.EXP = strexpression;

        var exp = math.parse(strexpression);
        var result = exp.eval(scope);
        calculator.latexmodel = [exp.toTex()];

        ANS = scope.ANS = result;
        return math.parse(math.format(result, 10)).toTex();
    };

    var funcs = {};
    // create trigonometric functions replacing the input depending on angle config
    ["sin", "cos", "tan", "sec", "cot", "csc"].forEach(function (name) {
        var fn = math[name]; // the original function
        var fnNumber = function (x) {
            var result = fn(x * calculator.radiansfactor);
            if (result > -1E-12 && result < 1E-12) {
                return 0.0;
            } else if (result > 1E12) {
                return 1 / 0;
            } else if (result < -1E12) {
                return -1 / 0;
            } else {
                return result;
            }
        };
        // create a typed-function which check the input types
        funcs[name] = math.typed(name, {
            "number": fnNumber,
            "Array | Matrix": function (x) {
                return math.map(x, fnNumber);
            }
        });
    });
    // create trigonometric functions replacing the output depending on angle config
    ["asin", "acos", "atan", "atan2", "acot", "acsc", "asec"].forEach(function (name) {
        var fn = math[name]; // the original function
        var fnNumber = function (x) {
            var result = fn(x);
            if (typeof result === "number") {
                return result / calculator.radiansfactor;
            } else {
                return result;
            }
        };
        // create a typed-function which check the input types
        funcs[name] = math.typed(name, {
            "number": fnNumber,
            "Array | Matrix": function (x) {
                return math.map(x, fnNumber);
            }
        });
    });
    // import all replacements into math.js, override existing trigonometric functions
    math.import(funcs, {override: true});

    //initialization of engine
    calculator.reset();
    math.parse("sqrt(pi/2)*sin(3)+e^2").eval();
    return;

}());
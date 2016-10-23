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
(function () {

    // system namespace
    calculator = {};

    // constants
    e = Math.E;
    pi = Math.PI;

    // functions
    sqrt = function (x) {
        return Math.sqrt(x);
    };
    pow = function (x, y) {
        return Math.pow(x, y);
    };
    log = function (x) {
        return Math.log10(x);
    };
    ln = function (x) {
        return Math.log(x);
    };
    pow10 = function (x) {
        return Math.pow(10.0, x);
    };
    exp = function (x) {
        return Math.exp(x);
    };
    var fmemo = [];
    var ffac = function (n) {
        if (n <= 1)
            return 1;
        if (fmemo[n] > 0)
            return fmemo[n];
        return fmemo[n] = ffac(n - 1) * n;
    };
    fac = function (n) {
        return ffac(Math.floor(n));
    };

    // radians to labels switch    
    calculator.radiansmode = 'RAD';
    calculator.radiansmodenext = 'DEG';
    calculator.radiansfactor = 1.0;
    calculator.radiansfactorswitch = function () {
        if ('RAD' === calculator.radiansmode) {
            calculator.radiansmode = 'DEG';
            calculator.radiansfactor = Math.PI / 180.0;
            calculator.radiansmodenext = 'RAD';
        } else {
            calculator.radiansmode = 'RAD';
            calculator.radiansfactor = 1.0;
            calculator.radiansmodenext = 'DEG';
        }
    };
    calculator.mode = function () {
        return [calculator.radiansmode];
    };
    calculator.latex = function() {
        return [];
    }
    // Trigonometric functions with adjustments
    sin = function (x) {
        var result = Math.sin(x * calculator.radiansfactor);
        return (result > -1E-12 && result < 1E-12) ? 0.0 : result;
    };
    cos = function (x) {
        var result = Math.cos(x * calculator.radiansfactor);
        return (result > -1E-12 && result < 1E-12) ? 0.0 : result;
    };
    tan = function (x) {
        var result = Math.tan(x * calculator.radiansfactor);
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
    asin = function (x) {
        return Math.asin(x) / calculator.radiansfactor;
    };
    acos = function (x) {
        return Math.acos(x) / calculator.radiansfactor;
    };
    atan = function (x) {
        return Math.atan(x) / calculator.radiansfactor;
    };

    // Calculator functions
    calculator.reset = function () {
        ANS = 0;
        return "";
    };
    calculator.eval = function (expression) {
        if (expression === undefined) {
            return;
        }
        return ANS = expression;
    };
    
    calculator.reset();
}());
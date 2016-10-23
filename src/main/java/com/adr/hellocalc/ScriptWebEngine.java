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

import java.util.ArrayList;
import java.util.List;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;

/**
 *
 * @author adrian
 */
public class ScriptWebEngine implements Script {

    private WebView browser;
    private WebEngine webEngine;

    @Override
    public void init() {
        browser = new WebView();
        webEngine = browser.getEngine();
    }

    @Override
    public Object exec(String script) {
        try {
            Object result = webEngine.executeScript(script);
            if (result.equals("undefined")) {
                return null;
            } else if (result instanceof JSObject) {
                JSObject jsresult = (JSObject) result;
                Object length = jsresult.getMember("length");
                if (length instanceof Integer) {
                    // It is an array
                    Integer ilength = (Integer) length;
                    List lresult = new ArrayList<>(ilength);
                    for (int i = 0; i < ilength; i++) {
                        lresult.add(jsresult.getSlot(i));
                    }
                    return lresult;
                } else {
                    return result;
                }
            } else {
                return result;
            }
        } catch (JSException ex) {
            return ex; // do not throw it, just return it
        }
    }
}

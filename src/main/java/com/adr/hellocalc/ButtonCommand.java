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

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author adrian
 */
public class ButtonCommand {
    private final String label;
    private final EventHandler<ActionEvent> command;

    public ButtonCommand(String label, EventHandler<ActionEvent> command) {
        this.label = label;
        this.command = command;
    }

    public String getLabel() {
        return label;
    }

    public EventHandler<ActionEvent> getCommand() {
        return command;
    }
}

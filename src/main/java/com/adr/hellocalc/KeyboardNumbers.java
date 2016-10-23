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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author adrian
 */
public class KeyboardNumbers {

    private final BasicEditor editor;
    private final VBox container;
    private final StackPane keyboardcontainer;

    private final ObservableStatus status = new ObservableStatus(0);
    private final ObservableFunc<Script> calculator = new ObservableFunc<>();

    private EventConsumer<String> execHandler = null;

    public KeyboardNumbers() {
        editor = new BasicEditor();
        editor.addCommand("<CLEAR>");
        
        keyboardcontainer = new StackPane();
        keyboardcontainer.setPadding(new Insets(1.0));
        container = new VBox(editor.getNode(), keyboardcontainer);
    }
       
    public KeyboardBuilder createKeyboardBuilder() {
        return new KeyboardBuilder();
    }

    public void setOnExec(EventConsumer<String> execHandler) {
        this.execHandler = execHandler;
    }

    private void fireExecHandler(String script) {
        if (execHandler != null) {
            execHandler.handle(script);
        }
    }

    public Node getNode() {
        return container;
    }

    public void updateView(Script calc) {
        calculator.notify(calc);
    }

    private void onActionButton(ActionEvent e, String command) {

        if (command.startsWith("<KB>")) {
            handleKeyboardCommand(command.substring(4));
        } else if (command.startsWith("<EXEC>")) {
            fireExecHandler(command.substring(6).replaceAll("%%%%%%", editor.getExpression()));
            status.setStatus(0);
        } else {
            editor.addCommand(command);
            status.setStatus(0);
        }
    }

    private void handleKeyboardCommand(String command) {
        if ("INV".equals(command)) {
            int s = status.getStatus();
            if (s == 1) {
                s = 0;
            } else {
                s++;
            }
            status.setStatus(s);
        } else {
            // display the keyboard
            int kb = Integer.parseInt(command);
            if (kb >= keyboardcontainer.getChildren().size()) {
                return;
            }
            int i = 0;
            for (Node n: keyboardcontainer.getChildren()) {
                n.setVisible(i++ == kb);
            }
            status.setStatus(0);
        }
    }
    
    public class KeyboardBuilder {
        private KeyboardBuilder() {}
        public Keyboard createKeyboard(int cols, int rows) {
            return new Keyboard(cols, rows);
        }
        public ButtonCommand createCommand(String command) {
            return new KeyboardCommand(command);
        }
        public ButtonCommand createCommand(final String label, final String command, final String... morecommands) {
            return new KeyboardCommand(label, command, morecommands);
        }
        public VisitorButton createLabeler(String labelexpression) {
            return new Labeler(labelexpression);
        }
        public VisitorButton createCommander(ButtonCommand... commands) {
            return new Commander(commands);
        }
    }
    
    public class Keyboard {
        private final GridPane keyboard;
        private int size;
        private int x;
        private int y;        
        private Keyboard(int cols, int rows) {
            keyboard = new GridPane();
            keyboard.setHgap(1.0);
            keyboard.setVgap(1.0);
            keyboard.setVisible(keyboardcontainer.getChildren().isEmpty()); // The first one is the initial visible.
            keyboardcontainer.getChildren().add(keyboard);

            double percent = 100.0 / cols;
            for (int i = 0; i < cols; i++) {
                ColumnConstraints c = new ColumnConstraints();
                c.setPercentWidth(percent);
                keyboard.getColumnConstraints().add(c);
            }
            for (int i = 0; i < rows; i++) {
                RowConstraints r = new RowConstraints();
                keyboard.getRowConstraints().add(r);
            }
            size = cols;
            x = 0;
            y = 0;            
        }
        
        public void addSpan() {
            addSpan(1);
        }
        
        public void addSpan(int span) {
            x += span;
            if (x >= size) {
                y += 1;
                x = 0;
            }           
        }
        
        public void addButton(Button b) {
            addButton(b, 1);
        }

        public void addButton(Button b, int span) {
            keyboard.add(b, x, y, span, 1);
            addSpan(span);
        }        
    }
    
    public class KeyboardCommand extends ButtonCommand {

        private KeyboardCommand(final String command) {
            this(command, command);
        }

        private KeyboardCommand(final String label, final String command, final String... morecommands) {
            super(label, new EventHandler<ActionEvent>() {
                // Do not convert to lambda to make retrolambda work
                @Override
                public void handle(ActionEvent event) {
                    onActionButton(event, command);
                    if (morecommands != null) {
                        for (String c : morecommands) {
                            onActionButton(event, c);
                        }
                    }
                }
            });
        }
    }

    public class Labeler implements VisitorButton {

        private final String labelexpression;

        private Labeler(String labelexpression) {
            this.labelexpression = labelexpression;
        }

        @Override
        public void visit(Button b) {
            b.setText(null);
            calculator.addObserver(calc -> {
                b.setText(calc.exec(labelexpression).toString());
            });
        }
    }

    public class Commander implements VisitorButton {

        private final ButtonCommand[] commands;

        private Commander(ButtonCommand... commands) {
            if (commands == null || commands.length == 0) {
                throw new RuntimeException("Comands list cannot be null");
            }
            this.commands = commands;
        }

        @Override
        public void visit(Button b) {
            b.setText(commands[0].getLabel());
            b.setOnAction(commands[0].getCommand());
            if (commands.length > 1) {
                // if there are more commands then we must observe the status.
                status.addObserver(i -> {
                    int imod = i % commands.length;
                    b.setText(commands[imod].getLabel());
                    b.setOnAction(commands[imod].getCommand());
                });
            }
        }
    }
}

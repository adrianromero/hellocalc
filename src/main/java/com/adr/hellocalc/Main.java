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

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ForkJoinPool;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Load fonts
        Font.loadFont(Main.class.getResource("/com/adr/hellocalc/styles/RobotoMono-Bold.ttf").toExternalForm(), 14.0);
        Font.loadFont(Main.class.getResource("/com/adr/hellocalc/styles/RobotoMono-Light.ttf").toExternalForm(), 14.0);

        primaryStage.setTitle("Calculator");

        String name = "mathcalc";
        String keyboard = "local:mathcalc_keyboard";
        String[] resources = new String[]{"/com/adr/hellocalc/scripts/math.min.js", "local:mathcalc"};
//        String keyboard = "local:scientific_keyboard";
//        String[] resources = new String[]{"local:scientific"};
//        String keyboard = "local:basic_keyboard";
//        String[] resources = new String[]{"local:basic"};

        Script calculator = new ScriptRhino();
//        Script calculator = new ScriptWebEngine();

        Display display = new LatexDisplay();

        KeyboardNumbers keyboardcalc = new KeyboardNumbers();
        ScriptRhino keyboardscript = new ScriptRhino();
        keyboardscript.init();
        keyboardscript.putScopeObject("KEYBOARDBUILDER", keyboardcalc.createKeyboardBuilder());
        keyboardscript.putScopeObject("CALCBUTTONBUILDER", new CalcBtnBuilder());
        loadResources(keyboardscript, keyboard);

        keyboardcalc.setOnExec(script -> {
            ForkJoinPool.commonPool().execute(() -> {
                Object result = calculator.exec(script);
                Platform.runLater(() -> {
                    display.updateResult(calculator, result);
                    keyboardcalc.updateView(calculator);
                });
            });
        });

        Loading loading = new Loading();
        StackPane containerdisplay = new StackPane(display.getNode(), loading.getNode());
        VBox.setVgrow(containerdisplay, Priority.ALWAYS);

        VBox root = new VBox(containerdisplay, keyboardcalc.getNode());

        root.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        AnchorPane.setRightAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setBottomAnchor(root, 0.0);

        AnchorPane anchorroot = new AnchorPane(root);

        double width;
        double height;

        if ("android".equals(System.getProperty("javafx.platform"))) {
            Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
            width = visualBounds.getWidth();
            height = visualBounds.getHeight();
        } else {
            width = 400.0;
            height = 580.0;
        }
        Scene scene = new Scene(anchorroot, width, height);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, e -> {
            if (KeyCode.ESCAPE.equals(e.getCode())) {
                primaryStage.close();
            }
        });

        scene.getStylesheets()
                .add(getClass().getResource("/com/adr/hellocalc/styles/main.css").toExternalForm());
        primaryStage.getIcons()
                .add(new Image(Main.class.getResourceAsStream("/com/adr/hellocalc/images/icon.png")));
        primaryStage.setScene(scene);

        primaryStage.show();

        loading.start();
        ForkJoinPool.commonPool().execute(() -> {
            calculator.init();
            loadResources(calculator, resources);
//            File path;
//            try {
//                path = PlatformFactory.getPlatform().getPrivateStorage();
//            } catch (IOException ex) {
//                path = new File(System.getProperty("java.io.tmpdir"));
//            }
//            File f = new File(path, name + ".ser");             
//
//            try {               
//                calculator.init(new FileInputStream(f));
//            } catch (IOException ex) {
//                calculator.init();
//                loadResources(calculator, resources);
//                try {
//                    calculator.save(new FileOutputStream(f));
//                } catch (IOException ex1) {
//                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex1);
//                }
//            }

            Platform.runLater(() -> {
                display.updateResult(calculator, null);
                keyboardcalc.updateView(calculator);
                loading.stop();
            });
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void loadResources(Script sc, String resource) {
        Object result = sc.exec(resourceToString(getAbsoluteResource(resource)));
        if (result instanceof Exception) {
            throw new RuntimeException((Exception) result);
        }
    }

    private static void loadResources(Script sc, String[] resources) {
        for (String res : resources) {
            loadResources(sc, res);
        }
    }

    private static String getAbsoluteResource(String resource) {
        if (resource.startsWith("local:")) {
            return "/com/adr/hellocalc/scripts/" + resource.substring(6) + ".js";
        } else {
            return resource;
        }
    }

    static String resourceToString(String resource) {
        try (InputStream is = Main.class.getResourceAsStream(resource)) {
            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

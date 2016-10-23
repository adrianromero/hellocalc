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

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

/**
 *
 * @author adrian
 */
public class Loading {
    
    private final static String GEARPATH = "M436.25,181.438c-1.529-2.002-3.524-3.193-5.995-3.571l-52.249-7.992c-2.854-9.137-6.756-18.461-11.704-27.98 c3.422-4.758,8.559-11.466,15.41-20.129c6.851-8.661,11.703-14.987,14.561-18.986c1.523-2.094,2.279-4.281,2.279-6.567 c0-2.663-0.66-4.755-1.998-6.28c-6.848-9.708-22.552-25.885-47.106-48.536c-2.275-1.903-4.661-2.854-7.132-2.854 c-2.857,0-5.14,0.855-6.854,2.567l-40.539,30.549c-7.806-3.999-16.371-7.52-25.693-10.565l-7.994-52.529 c-0.191-2.474-1.287-4.521-3.285-6.139C255.95,0.806,253.623,0,250.954,0h-63.38c-5.52,0-8.947,2.663-10.278,7.993 c-2.475,9.513-5.236,27.214-8.28,53.1c-8.947,2.86-17.607,6.476-25.981,10.853l-39.399-30.549 c-2.474-1.903-4.948-2.854-7.422-2.854c-4.187,0-13.179,6.804-26.979,20.413c-13.8,13.612-23.169,23.841-28.122,30.69 c-1.714,2.474-2.568,4.664-2.568,6.567c0,2.286,0.95,4.57,2.853,6.851c12.751,15.42,22.936,28.549,30.55,39.403 c-4.759,8.754-8.47,17.511-11.132,26.265l-53.105,7.992c-2.093,0.382-3.9,1.621-5.424,3.715C0.76,182.531,0,184.722,0,187.002 v63.383c0,2.478,0.76,4.709,2.284,6.708c1.524,1.998,3.521,3.195,5.996,3.572l52.25,7.71c2.663,9.325,6.564,18.743,11.704,28.257 c-3.424,4.761-8.563,11.468-15.415,20.129c-6.851,8.665-11.709,14.989-14.561,18.986c-1.525,2.102-2.285,4.285-2.285,6.57 c0,2.471,0.666,4.658,1.997,6.561c7.423,10.284,23.125,26.272,47.109,47.969c2.095,2.094,4.475,3.138,7.137,3.138 c2.857,0,5.236-0.852,7.138-2.563l40.259-30.553c7.808,3.997,16.371,7.519,25.697,10.568l7.993,52.529 c0.193,2.471,1.287,4.518,3.283,6.14c1.997,1.622,4.331,2.423,6.995,2.423h63.38c5.53,0,8.952-2.662,10.287-7.994 c2.471-9.514,5.229-27.213,8.274-53.098c8.946-2.858,17.607-6.476,25.981-10.855l39.402,30.84c2.663,1.712,5.141,2.563,7.42,2.563 c4.186,0,13.131-6.752,26.833-20.27c13.709-13.511,23.13-23.79,28.264-30.837c1.711-1.902,2.569-4.09,2.569-6.561 c0-2.478-0.947-4.862-2.857-7.139c-13.698-16.754-23.883-29.882-30.546-39.402c3.806-7.043,7.519-15.701,11.136-25.98l52.817-7.988 c2.279-0.383,4.189-1.622,5.708-3.716c1.523-2.098,2.279-4.288,2.279-6.571v-63.376 C438.533,185.671,437.777,183.438,436.25,181.438z M270.946,270.939c-14.271,14.277-31.497,21.416-51.676,21.416 c-20.177,0-37.401-7.139-51.678-21.416c-14.272-14.271-21.411-31.498-21.411-51.673c0-20.177,7.135-37.401,21.411-51.678 c14.277-14.272,31.504-21.411,51.678-21.411c20.179,0,37.406,7.139,51.676,21.411c14.274,14.277,21.413,31.501,21.413,51.678 C292.359,239.441,285.221,256.669,270.946,270.939z";
 
    private final Group container; 
    private final FadeTransition st;
    private final RotateTransition rt;
    private final RotateTransition rt2;

    public Loading() {
        
        Rectangle rect = new Rectangle(100.0, 100.0, Color.gray(0.9));
        rect.setArcHeight(50.0);
        rect.setArcWidth(50.0);
        rect.setMouseTransparent(true);
        
        SVGPath gear1 = new SVGPath();
        gear1.setContent(GEARPATH);
        gear1.setLayoutX(-182.0);
        gear1.setLayoutY(-182.0);
        gear1.setScaleX(0.1);
        gear1.setScaleY(0.1);
        gear1.setFill(Color.gray(0.3));
        gear1.setMouseTransparent(true);      
        rt = new RotateTransition(Duration.millis(3000), gear1);
        rt.setFromAngle(0.0);
        rt.setToAngle(360.0);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        
        SVGPath gear2 = new SVGPath();
        gear2.setContent(GEARPATH);
        gear2.setLayoutX(-154.0);
        gear2.setLayoutY(-154.0);
        gear2.setScaleX(0.08);
        gear2.setScaleY(0.08);
        gear2.setFill(Color.gray(0.3));
        gear2.setMouseTransparent(true);
        rt2 = new RotateTransition(Duration.millis(2400), gear2); // 2400 = 3000 * 0.08 / 0.1
        rt2.setFromAngle(360.0);
        rt2.setToAngle(0.0);
        rt2.setCycleCount(Animation.INDEFINITE);
        rt2.setInterpolator(Interpolator.LINEAR);
        
        container = new Group(rect, gear1, gear2);
        container.setOpacity(0.0);
        container.setMouseTransparent(true);       
        st = new FadeTransition(Duration.millis(200), container);
        st.setFromValue(0.0);
        st.setToValue(1.0);  
        st.setInterpolator(Interpolator.EASE_OUT);
  
        st.setOnFinished(e -> {
            if (st.getRate() < 0) {
                rt.stop();
                rt2.stop();
            }       
        });          
    }
    
    public Node getNode() {
        return container;
    }
    
    public void start() {
        st.setRate(1.0);
        st.play();
        rt.play();
        rt2.play();
    }
    
    public void stop() {
        st.setRate(-1.0);
        st.play();
        rt.play();   
        rt2.play();
    }    
}

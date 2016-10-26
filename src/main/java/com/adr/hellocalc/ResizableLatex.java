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

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.HPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.scilab.forge.jlatexmath.ParseException;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

/**
 *
 * @author adrian
 */
public class ResizableLatex extends Canvas {

    private static final Logger LOG = Logger.getLogger(ResizableLatex.class.getName());

    private final double minsize;
    private final double maxsize;
    private final HPos alignment;

    private String latex = null;
    private Paint color = Color.BLACK;    
    private double currentwidth = 100.0;
    
    public ResizableLatex(double minsize, double maxsize) {
        this(minsize, maxsize, HPos.LEFT);
    }
    
    public ResizableLatex(double minsize, double maxsize, HPos alignment) {
        this.minsize = minsize;
        this.maxsize = maxsize;
        this.alignment = alignment;
    }
    
    
    public void setLatex(String latex) {
        setLatex(latex, Color.BLACK);
    }

    public void setLatex(String latex, Paint color) {
        this.latex = latex;
        this.color = color;
        adjustEditorSize(currentwidth);
    }   
    
    public void adjustEditorSize(double newwidth) {
        
        currentwidth = newwidth;
        if (latex == null || latex.isEmpty()) {
            clearCanvas();
        } else {
            try {
                TeXFormula teXFormula = new TeXFormula(latex.replaceAll("~", "\\ ")); // The replaceAll is an awful and dirty hack to print properly complex numbers: 1 + 4i

                TeXIcon teXIcon = teXFormula.createTeXIcon(TeXConstants.STYLE_DISPLAY, maxsize);
                double height = teXIcon.getTrueIconHeight() + 10.0;
                double width = teXIcon.getTrueIconWidth() + 10.0;
                double size = maxsize;

                while (width > currentwidth && size > minsize) {
                    size -= 0.1;
                    teXIcon = teXFormula.createTeXIcon(TeXConstants.STYLE_DISPLAY, size);
                    height = teXIcon.getTrueIconHeight() + 10.0;
                    width = teXIcon.getTrueIconWidth() + 10.0;
                }
                setHeight(height);
                switch (alignment) {
                    case RIGHT:
                        setWidth(currentwidth);
                        teXIcon.paintInCanvas(this, (int) (currentwidth - width) + 5, 5, color, Color.TRANSPARENT);                      
                        break;
                    case CENTER:
                        setWidth(currentwidth);
                        teXIcon.paintInCanvas(this, (int) ((currentwidth - width) / 2) - 5, 5, color, Color.TRANSPARENT);                             
                        break;
                    default:
                        setWidth(width);
                        teXIcon.paintInCanvas(this, 5, 5, color, Color.TRANSPARENT);
                        break;
                }
            } catch (ParseException ex) {
                LOG.log(Level.WARNING, "Cannot parse LaTex formula." + latex, ex);
                clearCanvas();
            }
        }
    }

    private void clearCanvas() {
        setHeight(0.0);
        setWidth(0.0);
    }    
}

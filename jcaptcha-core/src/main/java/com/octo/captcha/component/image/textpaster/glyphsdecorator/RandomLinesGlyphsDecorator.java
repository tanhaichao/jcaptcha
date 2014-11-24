package com.octo.captcha.component.image.textpaster.glyphsdecorator;

import com.octo.captcha.component.image.color.ColorGenerator;
import com.octo.captcha.component.image.textpaster.Glyphs;

import java.awt.*;
import java.awt.geom.QuadCurve2D;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @author mag
 * @Date 11 mars 2008
 */
public class RandomLinesGlyphsDecorator implements GlyphsDecorator {
     private static final double SQRT_2=Math.sqrt(2);

      private Random myRandom = new SecureRandom();

    /**
     * Number of holes per glyph. Default : 3
     */
    private double numberOfLinesPerGlyph = 3;

    /**
     * ColorGenerator for the holes
     */
    private ColorGenerator linesColorGenerator = null;

    private double lineWidth;
    private double lineLength;

    private int alphaCompositeType = AlphaComposite.SRC_OVER;



    public RandomLinesGlyphsDecorator(double numberOfLinesPerGlyph, ColorGenerator linesColorGenerator, double lineWidth, double lineLength) {
        this.numberOfLinesPerGlyph = numberOfLinesPerGlyph;
        this.linesColorGenerator = linesColorGenerator;
        this.lineWidth = lineWidth;
        this.lineLength = lineLength;
    }

    public void decorate(Graphics2D g2, Glyphs glyphs, BufferedImage background) {
        Composite originalComposite = g2.getComposite();
        Stroke originalStroke = g2.getStroke();
        Color originalColor = g2.getColor();

        g2.setComposite(AlphaComposite.getInstance(alphaCompositeType));

        for (int j = 0; j < Math.round(glyphs.size()*numberOfLinesPerGlyph); j++) {
            double length = around(lineLength,0.5) /(2*SQRT_2);
            double width = around(lineWidth,0.3);

            double startX = (background.getWidth()-lineWidth) * myRandom.nextDouble();
            double startY = (background.getHeight()-lineWidth) * myRandom.nextDouble();
            double curveX = startX+around(length,0.5)*nextSign();
            double curveY = startY+around(length,0.5)*nextSign();
            double endX = curveX+around(length,0.5)*nextSign();
            double endY = curveY+around(length,0.5)*nextSign();
            QuadCurve2D q = new QuadCurve2D.Double(startX,startY,curveX,curveY, endX, endY);


            g2.setColor(linesColorGenerator.getNextColor());
            g2.setStroke(new BasicStroke((float)width));
            g2.draw(q);
            
        }

        g2.setComposite(originalComposite);
        g2.setColor(originalColor);
        g2.setStroke(originalStroke);

    }

    private double around(double from,double precision){
        double aFrom = from*precision;
        return (2*aFrom*myRandom.nextDouble())+from-aFrom;
    }

    private double nextSign(){
        return myRandom.nextBoolean()?1:-1;
    }


}

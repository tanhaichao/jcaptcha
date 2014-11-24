package com.octo.captcha.component.image.textpaster.glyphsvisitor;

import com.octo.captcha.component.image.textpaster.Glyphs;

import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.util.Random;
import java.security.SecureRandom;

/**
 * @author mag
 * @Date 6 mars 2008
 */
public class ShearGlyphsRandomVisitor implements GlyphsVisitors {

    private double maxShearX;
    private double maxShearY;
    private Random myRandom = new SecureRandom();

    public ShearGlyphsRandomVisitor(double maxShearX, double maxShearY) {
        this.maxShearX = maxShearX;
        this.maxShearY = maxShearY;
    }

    public void visit(Glyphs gv, Rectangle2D backroundBounds) {

        for(int i=0;i<gv.size();i++){
            AffineTransform af = new AffineTransform();
            af.setToShear(maxShearX*myRandom.nextGaussian(),maxShearY*myRandom.nextGaussian());
            gv.addAffineTransform(i, af);
        }
    }
}
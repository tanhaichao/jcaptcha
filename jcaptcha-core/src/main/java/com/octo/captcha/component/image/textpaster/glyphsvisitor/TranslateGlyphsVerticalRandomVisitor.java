package com.octo.captcha.component.image.textpaster.glyphsvisitor;

import com.octo.captcha.component.image.textpaster.Glyphs;

import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.security.SecureRandom;

/**
 * @author mag
 * @Date 7 mars 2008
 */
public class TranslateGlyphsVerticalRandomVisitor implements GlyphsVisitors {

    private Random myRandom = new SecureRandom();
    private double verticalRange = 1;
    public TranslateGlyphsVerticalRandomVisitor(double verticalRange) {
        this.verticalRange = verticalRange;
    }

    public void visit(Glyphs gv, Rectangle2D backroundBounds) {

       for(int i=0;i<gv.size();i++){
            double tx =0;
            double ty =verticalRange*myRandom.nextGaussian();
            //System.out.println("tx="+tx+",ty="+ty);
            gv.translate(i,tx,ty);
        }
    }
}

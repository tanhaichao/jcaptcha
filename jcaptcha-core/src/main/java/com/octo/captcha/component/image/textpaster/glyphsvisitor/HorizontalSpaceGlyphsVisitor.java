package com.octo.captcha.component.image.textpaster.glyphsvisitor;

import com.octo.captcha.component.image.textpaster.Glyphs;

import java.awt.geom.Rectangle2D;

/**
 * @author mag
 * @Date 6 mars 2008
 */
public class HorizontalSpaceGlyphsVisitor implements GlyphsVisitors {

    private int spaceBetweenGlyphs=0;

    public HorizontalSpaceGlyphsVisitor(int spaceBetweenGlyphs) {
        this.spaceBetweenGlyphs = spaceBetweenGlyphs;
    }

    public void visit(Glyphs glyphs, Rectangle2D backroundBounds) {

        for(int i=1;i< glyphs.size();i++){
            double tx = glyphs.getBoundsX(i-1)+ glyphs.getBoundsWidth(i-1)- glyphs.getBoundsX(i)+spaceBetweenGlyphs;
            double ty = 0;
            glyphs.translate(i,tx,ty);

        }
    }
}

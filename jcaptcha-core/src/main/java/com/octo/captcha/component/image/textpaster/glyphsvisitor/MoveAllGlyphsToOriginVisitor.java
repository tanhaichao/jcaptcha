package com.octo.captcha.component.image.textpaster.glyphsvisitor;

import com.octo.captcha.component.image.textpaster.Glyphs;

import java.awt.geom.Rectangle2D;

/**
 * @author mag
 * @Date 6 mars 2008
 */
public class MoveAllGlyphsToOriginVisitor implements GlyphsVisitors {


    public void visit(Glyphs glyphs, Rectangle2D backroundBounds) {
        for(int i=0;i< glyphs.size();i++){
        
            double tx =-glyphs.getX(i);
            double ty =-glyphs.getY(i);
            glyphs.translate(i,tx,ty);
        }
    }
}
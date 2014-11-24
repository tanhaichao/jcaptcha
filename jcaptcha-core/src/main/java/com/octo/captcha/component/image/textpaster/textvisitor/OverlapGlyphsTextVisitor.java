package com.octo.captcha.component.image.textpaster.textvisitor;

import com.octo.captcha.component.image.textpaster.MutableAttributedString;

/**
 * @author mag
 * @Date 5 mars 2008
  * @deprecated
 */
public class OverlapGlyphsTextVisitor implements TextVisitor{
    private int overlapPixs=0;

    public OverlapGlyphsTextVisitor(int overlapPixs) {
        this.overlapPixs = overlapPixs;
    }

    public void visit(MutableAttributedString mas) {
       mas.overlap(overlapPixs);
    }
}

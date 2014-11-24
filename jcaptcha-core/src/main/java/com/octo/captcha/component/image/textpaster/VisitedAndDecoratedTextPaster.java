package com.octo.captcha.component.image.textpaster;


import com.octo.captcha.CaptchaException;
import com.octo.captcha.component.image.color.ColorGenerator;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.textpaster.textvisitor.TextVisitor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.AttributedString;

/**
 * @author mag
 * @Date 5 mars 2008
 * @deprecated 
 */
public class VisitedAndDecoratedTextPaster extends AbstractTextPaster {


    protected final int kerning = 20;
    private TextVisitor[] textVisitors;
    private TextDecorator[] textDecorators;

    public VisitedAndDecoratedTextPaster(Integer minAcceptedWordLength, Integer maxAcceptedWordLength, TextVisitor[] textVisitors, TextDecorator[] textDecorators) {
        super(minAcceptedWordLength, maxAcceptedWordLength);
        this.textVisitors = textVisitors;
        this.textDecorators = textDecorators;
    }

    public VisitedAndDecoratedTextPaster(Integer minAcceptedWordLength, Integer maxAcceptedWordLength, Color textColor, TextVisitor[] textVisitors, TextDecorator[] textDecorators) {
        super(minAcceptedWordLength, maxAcceptedWordLength, textColor);
        this.textVisitors = textVisitors;
        this.textDecorators = textDecorators;
    }

    public VisitedAndDecoratedTextPaster(Integer minAcceptedWordLength, Integer maxAcceptedWordLength, ColorGenerator colorGenerator, TextVisitor[] textVisitors, TextDecorator[] textDecorators) {
        super(minAcceptedWordLength, maxAcceptedWordLength, colorGenerator);
        this.textVisitors = textVisitors;
        this.textDecorators = textDecorators;
    }

    public VisitedAndDecoratedTextPaster(Integer minAcceptedWordLength, Integer maxAcceptedWordLength, ColorGenerator colorGenerator, Boolean manageColorPerGlyph, TextVisitor[] textVisitors, TextDecorator[] textDecorators) {
        super(minAcceptedWordLength, maxAcceptedWordLength, colorGenerator, manageColorPerGlyph);
        this.textVisitors = textVisitors;
        this.textDecorators = textDecorators;
    }

    /**
     * Pastes the attributed string on the backround image and return the final image. Implementation must take into
     * account the fact that the text must be readable by human and non by programs
     *
     * @return the final image
     *
     * @throws com.octo.captcha.CaptchaException
     *          if any exception accurs during paste routine.
     */
    public BufferedImage pasteText(BufferedImage background, AttributedString attributedWord)
            throws CaptchaException {
        BufferedImage out = copyBackground(background);
        Graphics2D g2 = pasteBackgroundAndSetTextColor(out, background);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);


        // convert string into a series of glyphs we can work with
        MutableAttributedString mas = new MutableAttributedString(g2,
                      attributedWord, kerning);


        //First decorate
        if (textVisitors != null) {
            for (int i = 0; i < textVisitors.length; i++) {
                textVisitors[i].visit(mas);

            }
        }

         // shift string to a random spot in the output imge
        mas.moveToRandomSpot(background);
        // now draw each glyph at the appropriate spot on the image.
        if (isManageColorPerGlyph()) {
            mas.drawString(g2, getColorGenerator());
        } else {
            mas.drawString(g2);
        }

        //and now decorate
        if (textDecorators != null) {
            for (int i = 0; i < textDecorators.length; i++) {
                textDecorators[i].decorateAttributedString(g2, mas);

            }
        }
        g2.dispose();
        return out;
    }

}

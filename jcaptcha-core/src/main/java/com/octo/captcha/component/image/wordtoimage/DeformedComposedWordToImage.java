/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.component.image.wordtoimage;

import com.octo.captcha.CaptchaException;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.textpaster.TextPaster;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.AttributedString;
import java.util.*;
import java.util.List;

/**
 * <p>This implementation uses deformation components to distord the image. </br>It takes three array of deformations :
 * for the background image, for the text only, and for the final image it proceeds as folows : <ul> <li>Checks the word
 * length</li> <li>Creates an java.text.AttributedString from the word</li> <li>Create an image for the background a
 * BackgroundGenerator component</li> <li>Apply background deformations</li> <li>Apply font to the AttributedString
 * using the abstract method getFont</li> <li>Create a transparent backround </li> <li>Put the text on the transparent
 * backround using the abstact method pasteText</li> <li>Apply the text deformations </li> <li>Paste the transparent
 * image using an alpha composite</li> <li>Apply the final deformations </li> <li>Return the newly created image</li>
 * </ul>
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class DeformedComposedWordToImage extends ComposedWordToImage {

    private List<ImageDeformation> backgroundDeformations = new ArrayList<ImageDeformation>();
    private List<ImageDeformation> textDeformations = new ArrayList<ImageDeformation>();
    private List<ImageDeformation> finalDeformations = new ArrayList<ImageDeformation>();



    /**
     * Composed word to image that applys filters
     *
     * @param fontGenerator         a AbstractFontGenerator to implement the getFont() method
     * @param background            a AbstractBackgroundGenerator to implement the getBackround() method
     * @param textPaster            a AbstractTextParser to implement the pasteText() method
     * @param backgroundDeformation to be apply on the background image
     * @param textDeformation       to be apply on the text image
     * @param finalDeformation      to be apply on the final image
     */
    public DeformedComposedWordToImage(FontGenerator fontGenerator,
                                       BackgroundGenerator background,
                                       TextPaster textPaster,
                                       ImageDeformation backgroundDeformation,
                                       ImageDeformation textDeformation,
                                       ImageDeformation finalDeformation) {
        super(fontGenerator, background, textPaster);
        if(backgroundDeformation !=null)this.backgroundDeformations.add(backgroundDeformation);
        if(textDeformation !=null) this.textDeformations.add(textDeformation);
        if(finalDeformation !=null)this.finalDeformations.add(finalDeformation);
    }

    /**
     * Composed word to image that applys filters
     *
     * @param fontGenerator         a AbstractFontGenerator to implement the getFont() method
     * @param background            a AbstractBackgroundGenerator to implement the getBackround() method
     * @param textPaster            a AbstractTextParser to implement the pasteText() method
     * @param backgroundDeformations to be apply on the background image
     * @param textDeformations      to be apply on the text image
     * @param finalDeformations      to be apply on the final image
     */
    public DeformedComposedWordToImage(FontGenerator fontGenerator,
                                       BackgroundGenerator background,
                                       TextPaster textPaster,
                                       List<ImageDeformation> backgroundDeformations,
                                        List<ImageDeformation> textDeformations,
                                       List<ImageDeformation> finalDeformations) {
        super(fontGenerator, background, textPaster);
        this.backgroundDeformations = backgroundDeformations;
        this.textDeformations = textDeformations;
        this.finalDeformations = finalDeformations;
    }

    public DeformedComposedWordToImage(boolean manageFontByCharacter, FontGenerator fontGenerator, BackgroundGenerator background, TextPaster textPaster, List<ImageDeformation> backgroundDeformations, List<ImageDeformation> textDeformations, List<ImageDeformation> finalDeformations) {
        super(manageFontByCharacter, fontGenerator, background, textPaster);
        this.backgroundDeformations = backgroundDeformations;
        this.textDeformations = textDeformations;
        this.finalDeformations = finalDeformations;
    }

    /**
     * Creates an image of the provided String This method is a skeleton for creation algorithm. it proceeds as folows :
     * <ul> <li>Checks the word length</li> <li>Creates an java.text.AttributedString from the word</li> <li>Create an
     * image for the background using the abstact method getBackround</li> <li>Apply background filters</li> <li>Apply
     * font to the AttributedString using the abstract method getFont</li> <li>Create a transparent backround </li>
     * <li>Put the text on the transparent backround using the abstact method pasteText</li> <li>Apply the text filters
     * </li> <li>Paste the transparent image using an alpha composite</li> <li>Apply the final filters </li> <li>Return
     * the newly created image</li> </ul>
     *
     * @return an image representation of the word
     *
     * @throws com.octo.captcha.CaptchaException
     *          if word is invalid or if image generation fails.
     */
    public BufferedImage getImage(String word) throws CaptchaException {
        BufferedImage background = getBackground();
        AttributedString aword = getAttributedString(word, checkWordLength(word));
        //copy background
        BufferedImage out = new BufferedImage(background.getWidth(), background.getHeight(),
                background.getType());
        Graphics2D g2 = (Graphics2D) out.getGraphics();
        //paste background
        g2.drawImage(background, 0, 0, out.getWidth(), out.getHeight(), null);
        g2.dispose();
        //apply filters to backround
        for (ImageDeformation deformation:backgroundDeformations) {
            out = deformation.deformImage(out);
        }


        //paste text on a transparent background
        BufferedImage transparent = new BufferedImage(out.getWidth(), out.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        //use textpaster to paste the text
        transparent = pasteText(transparent, aword);

        //and apply deformation
      for (ImageDeformation deformation:textDeformations) {
            transparent = deformation.deformImage(transparent);
        }


        Graphics2D g3 = (Graphics2D) out.getGraphics();

        g3.drawImage(transparent, 0, 0, null);
        g3.dispose();
        //apply final deformation
        for (ImageDeformation deformation:finalDeformations) {
            out = deformation.deformImage(out);
        }
       
        return out;

    }
}

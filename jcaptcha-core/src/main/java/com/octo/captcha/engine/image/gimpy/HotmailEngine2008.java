/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.engine.image.gimpy;

import com.jhlabs.image.SwimFilter;
import com.jhlabs.image.PinchFilter;
import com.jhlabs.math.ImageFunction2D;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.SingleColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByBufferedImageOp;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.GlyphsPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.*;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;

import java.awt.*;
import java.util.ArrayList;

/**
 * <p/>
 * This is the default captcha engine. It provides a sample gimpy challenge that has no automated solution known. It is
 * based on the Baffle SPARC Captcha.
 * <p/>
 * </p>
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class HotmailEngine2008 extends ListImageCaptchaEngine {

    /**
     * this method should be implemented as folow : <ul> <li>First construct all the factories you want to initialize
     * the gimpy with</li> <li>then call the this.addFactoriy method for each factory</li> </ul>
     */
    protected void buildInitialFactories() {

        //word generator
        com.octo.captcha.component.word.wordgenerator.WordGenerator dictionnaryWords =
                new RandomWordGenerator("ABCDEGHJKLMNRSTUWXY235689");

        //wordtoimage components
        TextPaster randomPaster = new GlyphsPaster(8, 8,
                new SingleColorGenerator(new Color(0, 0, 80))
                ,new GlyphsVisitors[]{
                        new OverlapGlyphsUsingShapeVisitor(3),
                        new TranslateAllToRandomPointVisitor(20,20)
                }
                );
        

        BackgroundGenerator back = new UniColorBackgroundGenerator(
                218, 48, new Color(238, 238,238));

        FontGenerator shearedFont = new RandomFontGenerator(30,
                35,
                new Font[]{
                        new Font("Caslon",Font.BOLD, 30)
                }
        ,false);




        SwimFilter swim= new SwimFilter();



        swim.setScale(30);
        swim.setAmount(10);
        swim.setEdgeAction(ImageFunction2D.CLAMP);

        SwimFilter swim2= new SwimFilter();
        swim2.setScale(30);
        swim2.setAmount(10);
        swim2.setTime(90);
        swim2.setEdgeAction(ImageFunction2D.CLAMP);

        java.util.List<ImageDeformation> def =  new ArrayList<ImageDeformation>();

        def.add(new ImageDeformationByBufferedImageOp(swim));
        def.add(new ImageDeformationByBufferedImageOp(swim2)); 


        com.octo.captcha.component.image.wordtoimage.WordToImage word2image;
        word2image = new DeformedComposedWordToImage(false,shearedFont, back, randomPaster,
                new ArrayList<ImageDeformation>(),
                //new ArrayList<ImageDeformation>(),
                                def,
                new ArrayList<ImageDeformation>()
        );
         this.addFactory(
                new com.octo.captcha.image.gimpy.GimpyFactory(dictionnaryWords,
                        word2image, false));

    }
}
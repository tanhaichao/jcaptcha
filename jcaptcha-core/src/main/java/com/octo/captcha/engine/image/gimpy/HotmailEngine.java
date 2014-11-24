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
import com.octo.captcha.component.image.textpaster.glyphsdecorator.GlyphsDecorator;
import com.octo.captcha.component.image.textpaster.glyphsdecorator.RandomLinesGlyphsDecorator;
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
public class HotmailEngine extends ListImageCaptchaEngine {

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
                new TranslateGlyphsVerticalRandomVisitor(5),
                new RotateGlyphsRandomVisitor(Math.PI/32),
                new ShearGlyphsRandomVisitor(0.2,0.2),
                new HorizontalSpaceGlyphsVisitor(4),
                new TranslateAllToRandomPointVisitor()
                }
                ,
                new GlyphsDecorator[]{
                        new RandomLinesGlyphsDecorator(1.2,new SingleColorGenerator(new Color(0, 0, 80)),2,25),
                          new RandomLinesGlyphsDecorator(1,new SingleColorGenerator(new Color(238, 238,238)),1,25)
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
        swim.setStretch(1);
        swim.setTurbulence(1);
        swim.setAmount(2);
        swim.setTime(0);
        swim.setEdgeAction(ImageFunction2D.CLAMP);

        java.util.List<ImageDeformation> def =  new ArrayList<ImageDeformation>();
              def.add(new ImageDeformationByBufferedImageOp(swim));



        com.octo.captcha.component.image.wordtoimage.WordToImage word2image;
        word2image = new DeformedComposedWordToImage(false,shearedFont, back, randomPaster,
                new ArrayList<ImageDeformation>(),
                new ArrayList<ImageDeformation>(),
                def


        );
         this.addFactory(
                new com.octo.captcha.image.gimpy.GimpyFactory(dictionnaryWords,
                        word2image, false));

    }
}
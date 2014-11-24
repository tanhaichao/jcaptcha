/*
 * JCaptcha, the open source java framework for captcha definition and
 * integration Copyright (c) 2007 jcaptcha.net. All Rights Reserved. See the
 * LICENSE.txt file distributed with this package.
 */

/*
 * jcaptcha, the open source java framework for captcha definition and
 * integration copyright (c) 2007 jcaptcha.net. All Rights Reserved. See the
 * LICENSE.txt file distributed with this package.
 */

/*
 * jcaptcha, the open source java framework for captcha definition and
 * integration copyright (c) 2007 jcaptcha.net. All Rights Reserved. See the
 * LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.engine.image.gimpy;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByBufferedImageOp;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.*;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.*;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.jhlabs.image.PinchFilter;
import com.jhlabs.math.ImageFunction2D;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * <p/>
 * This is the default captcha engine. It provides a sample gimpy challenge that
 * has no automated solution known. It is based on the Baffle SPARC Captcha.
 * <p/>
 * </p>
 * 
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class GmailEngine extends ListImageCaptchaEngine {

	/**
	 * this method should be implemented as folow :
	 * <ul>
	 * <li>First construct all the factories you want to initialize the gimpy
	 * with</li>
	 * <li>then call the this.addFactoriy method for each factory</li>
	 * </ul>
	 */
	protected void buildInitialFactories() {

		//word generator
		com.octo.captcha.component.word.wordgenerator.WordGenerator dictionnaryWords = //new ConstantWordGenerator("gefefi");
		new com.octo.captcha.component.word.wordgenerator.ComposeDictionaryWordGenerator(new com.octo.captcha.component.word.FileDictionary("toddlist"));
		//wordtoimage components
		TextPaster randomPaster = new GlyphsPaster(7, 7, new RandomListColorGenerator(

		new Color[] { //
				new Color(23, 170, 27),//
						new Color(220, 34, 11), //
						new Color(23, 67, 172) //
				}),

		new GlyphsVisitors[] {//
				new TranslateGlyphsVerticalRandomVisitor(1),//
						// new RotateGlyphsRandomVisitor(Math.PI/32),
						new OverlapGlyphsUsingShapeVisitor(3), //
						new TranslateAllToRandomPointVisitor() //
				});
		/*
		 * new TextVisitor[]{ new OverlapGlyphsTextVisitor(6) }, null
		 */
		BackgroundGenerator back = new UniColorBackgroundGenerator(200, 70, Color.white);

		FontGenerator shearedFont = new RandomFontGenerator(50, 50, //
				new Font[] { new Font("nyala", Font.BOLD, 50), //
						new Font("Bell MT", Font.PLAIN, 50), //
						new Font("Credit valley", Font.BOLD, 50) //
				}, false);

		PinchFilter pinch = new PinchFilter();

		pinch.setAmount(-.5f);
		pinch.setRadius(30);
		pinch.setAngle((float) (Math.PI / 16));
		pinch.setCentreX(0.5f);
		pinch.setCentreY(-0.01f);
		pinch.setEdgeAction(ImageFunction2D.CLAMP);

		PinchFilter pinch2 = new PinchFilter();
		pinch2.setAmount(-.6f);
		pinch2.setRadius(70);
		pinch2.setAngle((float) (Math.PI / 16));
		pinch2.setCentreX(0.3f);
		pinch2.setCentreY(1.01f);
		pinch2.setEdgeAction(ImageFunction2D.CLAMP);

		PinchFilter pinch3 = new PinchFilter();
		pinch3.setAmount(-.6f);
		pinch3.setRadius(70);
		pinch3.setAngle((float) (Math.PI / 16));
		pinch3.setCentreX(0.8f);
		pinch3.setCentreY(-0.01f);
		pinch3.setEdgeAction(ImageFunction2D.CLAMP);

		List<ImageDeformation> textDef = new ArrayList<ImageDeformation>();
		textDef.add(new ImageDeformationByBufferedImageOp(pinch));
		textDef.add(new ImageDeformationByBufferedImageOp(pinch2));
		textDef.add(new ImageDeformationByBufferedImageOp(pinch3));

		//word2image 1
		com.octo.captcha.component.image.wordtoimage.WordToImage word2image;
		word2image = new DeformedComposedWordToImage(false, shearedFont, back, randomPaster, new ArrayList<ImageDeformation>(), new ArrayList<ImageDeformation>(), textDef

		);

		this.addFactory(new com.octo.captcha.image.gimpy.GimpyFactory(dictionnaryWords, word2image, false));

	}
}
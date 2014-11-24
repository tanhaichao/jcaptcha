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

package com.octo.captcha.engine.image.utils;

import com.octo.captcha.engine.image.ImageCaptchaEngine;
import com.octo.captcha.engine.image.gimpy.DefaultGimpyEngine;
import com.octo.captcha.image.ImageCaptcha;
import com.octo.captcha.image.ImageCaptchaFactory;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <p>Description: </p>
 *
 * @author <a href="mailto:mga@octo.com">Mathieu Gandin</a>
 * @version 1.0
 */
public class SimpleImageCaptchaToJPEG {

    public static void main(String[] args)
            throws IOException {

       ImageCaptchaEngine               bge =
                new DefaultGimpyEngine();
        System.out.println("got gimpy");

        ImageCaptchaFactory factory = bge.getImageCaptchaFactory();
        System.out.println("got factory");

        ImageCaptcha pixCaptcha = factory.getImageCaptcha();
        System.out.println("got image");

        System.out.println(pixCaptcha.getQuestion());

        BufferedImage bi = pixCaptcha.getImageChallenge();

        File f = new File("foo.jpg");

       ImageToFile.serialize(bi,f);
    }

}

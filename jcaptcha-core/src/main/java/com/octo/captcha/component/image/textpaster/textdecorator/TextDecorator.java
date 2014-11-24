/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.component.image.textpaster.textdecorator;

import com.octo.captcha.component.image.textpaster.MutableAttributedString;

import java.awt.*;
import java.text.AttributedString;

/**
 * <p><ul><li></li></ul></p>
 *
 * @author MAG
 * @version 1.0
 * @deprecated
 */
public interface TextDecorator {
    void decorateAttributedString(Graphics2D g2,  MutableAttributedString mutableAttributedString);
}

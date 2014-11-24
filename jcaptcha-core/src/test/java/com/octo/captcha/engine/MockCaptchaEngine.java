package com.octo.captcha.engine;

import java.util.Locale;

import com.octo.captcha.Captcha;
import com.octo.captcha.CaptchaFactory;
import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.engine.CaptchaEngineException;

public class MockCaptchaEngine implements CaptchaEngine {

	@Override
	public Captcha getNextCaptcha() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Captcha getNextCaptcha(Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CaptchaFactory[] getFactories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFactories(CaptchaFactory[] factories) throws CaptchaEngineException {
		// TODO Auto-generated method stub

	}

}

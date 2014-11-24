package com.octo.captcha.service.captchastore;

import java.util.Collection;
import java.util.Locale;

import com.octo.captcha.Captcha;
import com.octo.captcha.service.CaptchaServiceException;

public class MockCaptchaStore implements CaptchaStore {
	
	private boolean isInitCalled = false;

	public void cleanAndShutdown() {
	}

	public void empty() {
	}

	public Captcha getCaptcha(String id) throws CaptchaServiceException {
		return null;
	}

	public Collection getKeys() {
		return null;
	}

	public Locale getLocale(String id) throws CaptchaServiceException {
		return null;
	}

	public int getSize() {
		return 0;
	}

	public boolean hasCaptcha(String id) {
		return false;
	}

	public void initAndStart() {
		isInitCalled = true;
	}

	public boolean removeCaptcha(String id) {
		return false;
	}

	public void storeCaptcha(String id, Captcha captcha)
			throws CaptchaServiceException {

	}

	public void storeCaptcha(String id, Captcha captcha, Locale locale)
			throws CaptchaServiceException {

	}

	/**
	 * @return the isInitCalled
	 */
	public boolean isInitCalled() {
		return isInitCalled;
	}

}

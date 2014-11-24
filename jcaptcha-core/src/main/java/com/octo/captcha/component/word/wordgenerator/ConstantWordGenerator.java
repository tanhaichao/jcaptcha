package com.octo.captcha.component.word.wordgenerator;

import com.octo.captcha.CaptchaException;

import java.util.Locale;

/**
 * @author mag
 * @Date 7 mars 2008
 */
public class ConstantWordGenerator implements WordGenerator{
    String constantString;

    public ConstantWordGenerator(String constantString) {
        this.constantString = constantString;
        if(constantString==null||constantString.isEmpty())throw new CaptchaException("ConstantWordGenerator must be built with a non empty string");
    }

    public String getWord(Integer length) {
        StringBuilder toCut= new StringBuilder(constantString);
        while(toCut.length()<length){
            toCut.append(constantString);
        }
        return toCut.substring(0,length);
    }

    public String getWord(Integer length, Locale locale) {
        return getWord(length);
    }
}

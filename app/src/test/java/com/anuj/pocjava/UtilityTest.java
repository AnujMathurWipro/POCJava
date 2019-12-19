package com.anuj.pocjava;

import com.anuj.pocjava.util.Utility;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class UtilityTest {

    private String[] validUrls = {"http://www.google.com",
            "http://www.google.com/abc/xyz",
            "http://www.google.com/abc/xyz/pqr.aaa",
            "http://www.google.com/asdasdsadsadasd",
            "http://www.google.com?a=a,b=b",
            "http://www.google.com/dsgafsh/sdgasjh?a=a"};

    private String[] invalidUrls = {"www.google.com",
            null,
            "",
            "http://",
            "dsdkjh sh dhs akdh sa",
            "http://www.hdhasg dhgsah gsh gdjahs djh",
            "http://www.hdhasg.com/ghg!@#$%^&*())(*&^%$#@!"};
    @Test
    public void testValidUrls() {
        for (String validUrl : validUrls) Assert.assertTrue("URL = " + validUrl, Utility.isValidURL(validUrl));
    }

    @Test
    public void testInvalidUrls() {
        for (String invalidUrl : invalidUrls) Assert.assertFalse("URL = " + invalidUrl, Utility.isValidURL(invalidUrl));
    }
}

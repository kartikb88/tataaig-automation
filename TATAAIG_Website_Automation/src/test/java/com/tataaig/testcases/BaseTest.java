package com.tataaig.testcases;

import org.testng.annotations.AfterSuite;

import com.tataaig.base.Page;

public class BaseTest {
	@AfterSuite
	public void tearDown() {
		Page.quitBrowser();	
		
	}

}

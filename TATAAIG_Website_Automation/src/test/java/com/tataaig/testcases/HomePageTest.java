package com.tataaig.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.tataaig.base.Page;
import com.tataaig.pages.HomePage;
import com.tataaig.utilities.Utilities;

public class HomePageTest extends BaseTest  {
	
	@Test(dataProviderClass=Utilities.class,dataProvider="dp")	
	public void HomePageTitleTest(Hashtable<String,String> data) throws IOException {
		if(data.get("runmode").equalsIgnoreCase("N")){			
			throw new SkipException("Skipping the test as the Run mode is NO");			
		}
		
		new HomePage();
		Page.insuranceTabs.selectCarInsuranceTab();			
		String tataaigHomePageTitle = Page.getHomePageTitle();	
		Page.verifyEquals(data.get("Expected_HomePageTitle"), tataaigHomePageTitle);
	}
	
}

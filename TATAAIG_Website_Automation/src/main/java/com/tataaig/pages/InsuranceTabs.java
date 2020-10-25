package com.tataaig.pages;

import org.openqa.selenium.WebDriver;


import com.tataaig.base.Page;

public class InsuranceTabs {
	
	WebDriver driver;
	public InsuranceTabs(WebDriver driver){
		this.driver = driver;	
	}
	
	public void selectCarInsuranceTab() {
		Page.click("carInsurancePolicyTab_CSS");
	}
		
	public void selectBikeInsuranceTab() {
		Page.click("bikeInsurancePolicyTab_CSS");
	}
	
	public void selectHealthInsuranceTab() {
		Page.click("healthInsurancePolicyTab_CSS");
	}
	
	public void selectTravelInsuranceTab() {
		Page.click("travelInsurancePolicyTab_CSS");
	}
	public HomePage navigateBacktoHomePage() {
		Page.click("tataaigLogo_XPATH");
		return new HomePage();
	}
	
}

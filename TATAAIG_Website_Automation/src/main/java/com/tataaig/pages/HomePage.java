package com.tataaig.pages;

import com.tataaig.base.Page;

public class HomePage extends Page{	
	
	public void getHomePageTitle() {
		String homePageTitle = driver.getTitle();
		System.out.println(homePageTitle);
		//Assert.assertEquals(carInsurancePageTitle, "General Insurance - Buy / Renew Car, Travel & Health Insurance Online");
	}	
		
	public VehicleTypePage submitVehicleNumber(String vehicleRegistartionNumber) {
		type("regNumber_CSS", vehicleRegistartionNumber);
		click("globalSubmitButton_CSS");	
		return new VehicleTypePage();		
	}
		
	
	/*	//_________________________________________________________________________________________________
		String message = driver.findElement(By.cssSelector("div.modal-body")).getText();
		System.out.println(message);
		driver.findElement(By.cssSelector("div.modal-body button")).click();
		driver.quit();
	*/

}

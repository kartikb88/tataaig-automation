package com.tataaig.pages;

import com.tataaig.base.Page;

public class VehicleDetailsPage extends Page{

	
	public void enterVehicalInsuranceDetails(String purchaseDate,String previousPolicyEndDate,String mobileNumber) {
		type("purchaseDate_NAME", purchaseDate);
		type("previousPolicyEndDate_NAME", previousPolicyEndDate);
		type("mobile_NAME", mobileNumber);
		click("globalSubmitButton_CSS");
	}
	
}

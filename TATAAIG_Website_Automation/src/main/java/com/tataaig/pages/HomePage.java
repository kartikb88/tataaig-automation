package com.tataaig.pages;

import com.tataaig.base.Page;

public class HomePage extends Page{			
	public VehicleTypePage submitVehicleNumber(String vehicleRegistartionNumber) {
		type("regNumber_CSS", vehicleRegistartionNumber);
		click("globalSubmitButton_CSS");	
		return new VehicleTypePage();		
	}
		


}

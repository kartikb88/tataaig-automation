package com.tataaig.testcases;

import org.testng.annotations.Test;

import com.tataaig.base.Page;
import com.tataaig.pages.HomePage;
import com.tataaig.pages.VehicleTypePage;

public class CarDetailsPageTest extends BaseTest{

	@Test
	public void  carDetailsPageTest() {
		HomePage homepage = new HomePage();
		Page.insuranceTabs.selectCarInsuranceTab();
		VehicleTypePage  vehicleTypePage = homepage.submitVehicleNumber("MH01AB1234");	
		
	}

}

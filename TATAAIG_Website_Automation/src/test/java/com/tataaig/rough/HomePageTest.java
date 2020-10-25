package com.tataaig.rough;

import com.tataaig.base.Page;
import com.tataaig.pages.HomePage;
import com.tataaig.pages.VehicleDetailsPage;
import com.tataaig.pages.VehicleTypePage;

public class HomePageTest extends Page{

	public static void main(String[] args) { 
			HomePage homepage = new HomePage();
			Page.insuranceTabs.selectCarInsuranceTab();
			VehicleTypePage  vehicleTypePage = homepage.submitVehicleNumber("MH01AB1234");		
			VehicleDetailsPage vehicleDetails =vehicleTypePage.selectVehicleTypeDetails("Ford","Figo","1.2 trend");
			vehicleDetails.enterVehicalInsuranceDetails("01/2012","01/01/2018","9527630419");

	}
	
}

package com.tataaig.testcases;


import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.tataaig.base.Page;
import com.tataaig.pages.HomePage;
import com.tataaig.pages.VehicleDetailsPage;
import com.tataaig.pages.VehicleTypePage;
import com.tataaig.utilities.Utilities;

public class PurchaseCarInsuranceTest extends BaseTest {
	
	@Test(dataProviderClass=Utilities.class,dataProvider="dp")
	public void purchaseCarInsurance(Hashtable<String,String> data)  {
		if(data.get("runmode").equalsIgnoreCase("N")){			
			throw new SkipException("Skipping the test as the Run mode is NO");			
		}
			
		HomePage homepage = new HomePage();
		Page.insuranceTabs.selectCarInsuranceTab();
		VehicleTypePage vehicleTypePage = homepage.submitVehicleNumber(data.get("carRegNumber"));			
		VehicleDetailsPage vehicleDetails = vehicleTypePage.selectVehicleTypeDetails(data.get("brandName"),data.get("brandModal"),data.get("variant"));
		vehicleDetails.enterVehicalInsuranceDetails(data.get("purchaseDate"),data.get("previousPolicyEndDate"),data.get("mobileNumber"));
	
	}
	

}

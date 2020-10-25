package com.tataaig.pages;

import com.tataaig.base.Page;

public class VehicleTypePage extends Page  {
		
	public VehicleDetailsPage enterVehicleTypeDetails(String brand,String Modal,String variant) {
		selectFromDropDown("brandName_CSS","brandNames_CSS",brand);
		selectFromDropDown("brandModal_CSS","brandModals_CSS",Modal);
		selectFromDropDown("variant_CSS","variants_CSS",variant);			
		click("globalSubmitButton_CSS");
		return new VehicleDetailsPage();
	}

}

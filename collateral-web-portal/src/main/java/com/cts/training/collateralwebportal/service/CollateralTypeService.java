package com.cts.training.collateralwebportal.service;

import org.springframework.stereotype.Service;

@Service
public class CollateralTypeService {

 

    public boolean checkCollateral(String collateralType) {
        // TODO Auto-generated method stub
        if(collateralType.equalsIgnoreCase("RealEstate"))
        {
            return true;
        }
        else {
            return false;
        }
    }
    

 

}


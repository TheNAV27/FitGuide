package org.example.fitguide.service;

import org.springframework.stereotype.Service;

@Service
public class tdeeCalculatorService {

    public double calculateTDEE(double BMR,String activityLevel){
        if(BMR == 0){
            throw new IllegalArgumentException("BMR cannot be zero");
        }
        if(activityLevel == null){
            throw new IllegalArgumentException("Activity level is required");
        }
        return switch(activityLevel.toLowerCase()){
            case "sedentary" -> BMR*1.2;
            case "lightly active" -> BMR*1.375;
            case "moderately active" -> BMR*1.55;
            case "very active" -> BMR*1.75;
            case "extremely active" ->BMR*1.9;

            default -> throw new IllegalArgumentException("Invalid activity level, please choose a proper one");
        };

    }
}

package org.example.fitguide.service;

import org.springframework.stereotype.Service;

@Service
public class BmrCalculatorService {
    public  double calculateBMR(String gender, double weight, double height, int age) {
        if (gender == null) {
            throw new NullPointerException("Gender cannot be null");
        }
        if(weight <=0 || height <=0 || age <=0) {
            throw new IllegalArgumentException("Weight, Height, age must be greater than 0");
        }
        return switch(gender.toLowerCase()){
            case "male" -> 10*weight + 6.25*height-5 * age +5;
            case "female" -> 10*weight + 6.25*height-5 * age + 161;
            default -> throw new IllegalArgumentException("Invalid gender,use male or female");
        };

    }
}

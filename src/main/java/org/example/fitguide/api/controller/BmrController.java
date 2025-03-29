package org.example.fitguide.api.controller;

import org.example.fitguide.service.BmrCalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BmrController {
    private final BmrCalculatorService bmrCalculatorService;

    public BmrController(BmrCalculatorService bmrCalculatorService) {
        this.bmrCalculatorService = bmrCalculatorService;
    }
    @GetMapping("users/calculate")
    public ResponseEntity<?> calculateBMR(
            @RequestParam (required = true) String gender,
            @RequestParam double weight,
            @RequestParam double height,
            @RequestParam int age

    ){
        if (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")) {
            return ResponseEntity.badRequest().body("Gender must be 'male' or 'female'");
        }
        double bmr = bmrCalculatorService.calculateBMR(gender, weight, height, age);
        BmrResponse response = new BmrResponse();
        response.setBmr(bmr);
        return ResponseEntity.ok(response);
    }
   public static class BmrResponse{
        private double bmr;

        public double getBmr(){return bmr;}
       public void setBmr(double bmr){this.bmr = bmr;}
   }
}

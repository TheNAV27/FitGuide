package org.example.fitguide.controller;

import org.example.fitguide.service.BmrCalculatorService;
import org.example.fitguide.service.tdeeCalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/calculate")
public class BmrController {
    private final BmrCalculatorService bmrCalculatorService;
    private final org.example.fitguide.service.tdeeCalculatorService tdeeCalculatorService;

    public BmrController(BmrCalculatorService bmrCalculatorService, tdeeCalculatorService tdeeCalculatorService) {
        this.bmrCalculatorService = bmrCalculatorService;
        this.tdeeCalculatorService = tdeeCalculatorService;
    }
    @GetMapping("/calculateBMR")
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
    @GetMapping("/calculateTDEE")
    public ResponseEntity<?> calculateTDEE(
            @RequestParam (required = true) double bmr,
            @RequestParam (required = true) String activityLevel
    ){
        double tdee = tdeeCalculatorService.calculateTDEE(bmr,activityLevel);
        TdeeResponse response = new TdeeResponse();
        response.setTdee(tdee);
        return ResponseEntity.ok(response);
    }
   public static class BmrResponse{
        private double bmr;

        public double getBmr(){return bmr;}
       public void setBmr(double bmr){this.bmr = bmr;}
   }
   public static class TdeeResponse{
        private double tdee;
        public double getTdee(){return tdee;}
       public void setTdee(double tdee){this.tdee = tdee;}
   }
}

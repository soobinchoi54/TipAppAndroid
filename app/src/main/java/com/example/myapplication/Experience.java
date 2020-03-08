package com.example.myapplication;


 /**************************************************************************************************
  *              Experience is a data structure to be stored in database
  ***************************************************************************************************/
public class Experience {

    // Preset Fields
    private String NAME;
    private String LOCATION;
    private String CATEGORY;
    private String PRICE;
    private String TOTAL_BILL;
    private String TIP_PERCENTAGE;
    private String TIME;
    private String SERVICE;
    private String TIMELINESS;
    private String FOOD;
    private String CUSTOM;
    private String CUSTOM_RATE;
    private String IMAGE;

    private Experience(ExperienceBuilder builder){
        this.NAME = builder.NAME;
        this.LOCATION = builder.LOCATION;
        this.CATEGORY = builder.CATEGORY;
        this.PRICE = builder.PRICE;
        this.TOTAL_BILL = builder.TOTAL_BILL;
        this.TIP_PERCENTAGE = builder.TIP_PERCENTAGE;
        this.TIME = builder.TIME;
        this.SERVICE = builder.SERVICE;
        this.TIMELINESS = builder.TIMELINESS;
        this.FOOD = builder.FOOD;
        this.CUSTOM = builder.CUSTOM;
        this.CUSTOM_RATE = builder.CUSTOM_RATE;
        this.IMAGE = builder.IMAGE;
    }

     public String getName() {
         return NAME;
     }

     public String getLocation() {
         return LOCATION;
     }

     public String getCategory() {
         return CATEGORY;
     }

     public String getPrice(){
        return PRICE;
     }

     public String getTotalBill() {
         return TOTAL_BILL;
     }

     public String getTipPercentage() {
         return TIP_PERCENTAGE;
     }

     public String getTime() {
         return TIME;
     }

     public String getService() {
         return SERVICE;
     }

     public String getTimeliness() {
         return TIMELINESS;
     }

     public String getFood() { return FOOD; }

     public String getCustom() {
         return CUSTOM;
     }

     public String getCUSTOM_RATE() {return CUSTOM_RATE;}

     public String getImage() {return IMAGE;}

     public static class ExperienceBuilder{
         private String NAME;
         private String LOCATION;
         private String CATEGORY;
         private String PRICE;
         private String TOTAL_BILL;
         private String TIP_PERCENTAGE;
         private String TIME;
         private String SERVICE;
         private String TIMELINESS;
         private String FOOD;
         private String CUSTOM;
         private String CUSTOM_RATE;
         private String IMAGE;
         public ExperienceBuilder(String name){
            NAME = name;
         }

         public ExperienceBuilder location(String location) {
             LOCATION = location;
             return this;
         }

         public ExperienceBuilder category(String category) {
             CATEGORY = category;
             return this;
         }

         public ExperienceBuilder price(String price){
             PRICE = price;
             return this;
         }

         public ExperienceBuilder totalBill(String totalBill) {
             TOTAL_BILL = totalBill;
             return this;
         }

         public ExperienceBuilder tipPercentage(String tipPercentage) {
             TIP_PERCENTAGE = tipPercentage;
             return this;
         }

         public ExperienceBuilder time(String time) {
             TIME = time;
             return this;
         }

         public ExperienceBuilder service(String service) {
             SERVICE = service;
             return this;
         }

         public ExperienceBuilder timeliness(String timeliness) {
             TIMELINESS = timeliness;
             return this;
         }

         public ExperienceBuilder food(String food) {
             FOOD = food;
             return this;
         }

         public ExperienceBuilder custom(String custom) {
             CUSTOM = custom;
             return this;
         }

         public ExperienceBuilder customRate(String customRate) {
             CUSTOM_RATE = customRate;
             return this;
         }

         public ExperienceBuilder image(String image) {
             IMAGE = image;
             return this;
         }

         public Experience build(){
             Experience experience = new Experience(this);
             if(validateExperienceObject(experience))
                return experience;
             else return null;
         }

         private boolean validateExperienceObject(Experience experience){
            if(!experience.getName().isEmpty()) return true;
            else return false;
         }
     }
 }

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
    private String CRITERIA1;
    private String CRITERIA2;
    private String CRITERIA3;
    private String CUSTOM;
     private String CUSTOM_RATE;

    public Experience(String NAME, String LOCATION, String CATEGORY, String PRICE, String TOTAL_BILL, String TIP_PERCENTAGE, String TIME, String CRITERIA1, String CRITERIA2, String CRITERIA3, String CUSTOM, String CUSTOM_RATE){
        this.NAME = NAME;
        this.LOCATION = LOCATION;
        this.CATEGORY = CATEGORY;
        this.PRICE = PRICE;
        this.TOTAL_BILL = TOTAL_BILL;
        this.TIP_PERCENTAGE = TIP_PERCENTAGE;
        this.TIME = TIME;
        this.CRITERIA1 = CRITERIA1;
        this.CRITERIA2 = CRITERIA2;
        this.CRITERIA3 = CRITERIA3;
        this.CUSTOM = CUSTOM;
        this.CUSTOM_RATE = CUSTOM_RATE;
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

     public String getCriteria1() {
         return CRITERIA1;
     }

     public String getCriteria2() {
         return CRITERIA2;
     }

     public String getCriteria3() { return CRITERIA3; }

     public String getCustom() {
         return CUSTOM;
     }

     public String getCUSTOM_RATE() {return CUSTOM_RATE;}
 }

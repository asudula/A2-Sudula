package edu.unc.comp210.a02mothership;

public class FoodModule extends AModule{
    int amountOfFood;
    int daysInFridge;

    public FoodModule(String name, int amountOfFood){
        super(name);
        this.amountOfFood = amountOfFood;
    }

    @Override
    public void statusReport(String moduleStatus, boolean isSuccessful) {
        if (amountOfFood <= 0) {
            System.out.println("Go get groceries in the inter-galaxy grocery store.");
        }
        else if (daysInFridge == 7) {
            System.out.println("You have food for the week.");
        }
        else {
            System.out.println("You have this much food: " + amountOfFood);
        }
        super.statusReport(moduleStatus, isSuccessful);
    }

    public void eatFood(int eatenFood) {
        this.amountOfFood = amountOfFood - eatenFood;
        System.out.println("You have this much food: " + amountOfFood);
    }

    public void groceryShopping() {
        this.amountOfFood += 10;
        System.out.println("You finally left your ship to go inter-galaxy grocery shopping. You have this much food " + amountOfFood + " .");
    }


}

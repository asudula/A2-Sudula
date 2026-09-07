package edu.unc.comp210.a02mothership;

public class FuelGenerator extends APowerGenerator {
    private int fuel;

    public FuelGenerator(int fuel, String name) {
        super(name);
        this.fuel = fuel;
    }

    @Override
    public void statusReport(String moduleStatus, boolean isSuccessful) {
        System.out.println("FuelGenerator: "+ this.fuel +" units of fuel remaining.");
        super.statusReport(moduleStatus, isSuccessful);
    }

    @Override
    public int generatePower(){
        if (fuel >= 10) {
            fuel -= 10;
            return 10;
        }
        else {
            fuel = 0;
            return fuel;
        }
    }
}


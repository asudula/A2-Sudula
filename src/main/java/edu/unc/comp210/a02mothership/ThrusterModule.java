package edu.unc.comp210.a02mothership;

public class ThrusterModule extends AModule {
    private int fuel;
    private boolean lastFired;
    //private String name;

    public ThrusterModule(String name){
        super(name);
        fuel = 100;
        lastFired = false;
    }

    @Override
    public void statusReport(String moduleStatus, boolean isSuccessful) {
        System.out.println("ThrusterModule: " + this.fuel + " units of fuel remaining. Last fired: " + this.lastFired);
        super.statusReport(moduleStatus, isSuccessful);
    }

    public boolean thrust(int availablePower) {
        if ((fuel >= 5) && (availablePower >= 5)) {
            fuel -= 5;
            lastFired = true;
            System.out.println("5 fuel used for propulsion maneuver.");
            return true;
        }
        else {
            lastFired = false;
            System.out.println("ThrusterModule: Not enough power or fuel to fire.");
            return false;
        }
    }




}

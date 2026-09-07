package edu.unc.comp210.a02mothership;

public class Main {
    public static void main(String[] args) {
        APowerGenerator generator = new SolarGenerator();
        // To swap use: APowerGenerator generator = new FuelGenerator(50);
        ThrusterModule thrusterOne = new ThrusterModule();
        ExperimentModule experimentOne = new ExperimentModule("experimentOne", new double[]{ 1.5, 3.0, 4.5});
        FoodModule foodModule = new FoodModule("foodModule", 7);

        Mothership motherShipTest = new Mothership(generator, thrusterOne, experimentOne, foodModule);

        int power = motherShipTest.requestPower();
        System.out.println("Power requested: " + power);

        boolean fired = motherShipTest.fireThruster(power);
        System.out.println("Has ship fired the thruster: "+ fired);

        motherShipTest.runExperiment();
        String summary = motherShipTest.getExperimentSummary();
        System.out.println(summary);

        motherShipTest.printStatusReports();

    }

}


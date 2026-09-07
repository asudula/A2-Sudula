package edu.unc.comp210.a02mothership;

import java.util.ArrayList;

public class Mothership {
    private APowerGenerator powerGenerator;
    private ThrusterModule thrusterModule;
    private ExperimentModule experimentModule;
    private ArrayList<AModule> modules;
    private AModule extraModule;

    public Mothership(APowerGenerator powerGenerator, ThrusterModule thrusterModule, ExperimentModule experimentModule) {
        this.powerGenerator = powerGenerator;
        this.thrusterModule = thrusterModule;
        this.experimentModule = experimentModule;

        this.modules = new ArrayList<>();

        this.modules.add(powerGenerator);
        this.modules.add(thrusterModule);
        this.modules.add(experimentModule);
    }

    public Mothership(APowerGenerator powerGenerator, ThrusterModule thrusterModule,
                      ExperimentModule experimentModule, AModule extraModule) {
        this(powerGenerator, thrusterModule, experimentModule);
        // now add extraModule to your list
        this.extraModule = extraModule;
        this.modules.add(extraModule);
    }

    public int requestPower() {
        return powerGenerator.generatePower();
    }

    public boolean fireThruster(int availablePower) {
        return thrusterModule.thrust(availablePower);
    }

    public void runExperiment() {
        experimentModule.runExperiment();
    }

    public String getExperimentSummary() {
        return experimentModule.getSummary();
    }

    public void printStatusReports() {
        for (AModule module : modules) {
            module.statusReport("Normal", true);
        }
    }


}

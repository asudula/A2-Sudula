package edu.unc.comp210.a02mothership;

public class ExperimentModule extends AModule {
    private String experimentName;
    private double[] parameters;
    private double result;
    private boolean hasRun;

    public ExperimentModule(String experimentName, double[] parameters) {
        super("ExperimentModule");
        this.experimentName = experimentName;
        this.parameters = parameters;
        this.hasRun = false;
        this.result = 0;
    }

    public void runExperiment(){
        for (double parameter: parameters){
            result += parameter * Math.random();
        }
        hasRun = true;
    }

    public String getSummary() {
        if (hasRun) {
            return "Experiment '" + experimentName + "' result: " + result;
        }

        else {
            return "Experiment not run yet.";
        }
    }

    @Override
    public void statusReport(String moduleStatus, boolean isSuccessful) {
        if (hasRun) {
            System.out.println("ExperimentModule : " + experimentName + " completed.");
        }

        else {
            System.out.println("ExperimentModule: " + experimentName + " pending.");
        }
        super.statusReport(moduleStatus, isSuccessful);
    }
}

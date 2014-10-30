package edu.cs.montclair;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CalculatorModel baseModel = new CalculatorModel(0.3, 0.44);
        CalculatorModel incSizeModel = new CalculatorModel(0.3*1.25, 0.44*1.25);
        CalculatorModel incDistModel = new CalculatorModel(0.3, 0.44*1.25);

        List<CalculatorModel> models = Arrays.asList(baseModel, incSizeModel, incDistModel);

        for (CalculatorModel model : models) {
            System.out.println("*******************");
            System.out.println("total time: "+calc(model)+" ms");
        }
    }

    private static double calc(CalculatorModel model) {
        double time = 0;
        double totalDistance = 0;

        for (Double dist : model.getPath()) {
            totalDistance += dist;
            time += fitts(dist, model.getKeySize());
        }

        System.out.println("total distance: " + totalDistance);
        return time + CalculatorModel.TRANSITIONS*CalculatorModel.WAIT_TIME;
    }

    private static double fitts(double D, double S) {
        final int C = 100;
        //System.out.println("D: " + D + " S: "+S);
        return C*(Math.log(D/S + 0.5)/Math.log(2));
    }
}

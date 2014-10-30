package edu.cs.montclair;

import java.util.ArrayList;
import java.util.List;

public class CalculatorModel {
    public static final int TRANSITIONS = 8;
    public static final int WAIT_TIME = 200;

    private final double keySize;
    private final double keyDistance;

    private final List<Double> path;

    public CalculatorModel(double keySize, double keyDistance) {
        this.keySize = keySize;
        this.keyDistance = keyDistance;

        path = new ArrayList<>();
        path.add(calcHypo(7, 1));
        path.add(calcHypo(2, 1));
        path.add(calcHypo(2, 1));
        path.add(calcHypo(2, 1));
        path.add(calcHypo(2, 2));
        path.add(calcHypo(2, 1));
        path.add(calcHypo(2, 1));
        path.add(calcHypo(9, 1));
    }

    public double getKeySize() {
        return keySize;
    }

    public double getKeyDistance() {
        return keyDistance;
    }

    public List<Double> getPath() {
        return path;
    }

    private double calcHypo(int length, int height) {
        return Math.sqrt(Math.pow(keyDistance,2)*length + Math.pow(keyDistance,2)*height);
    }
}

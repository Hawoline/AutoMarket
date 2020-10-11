package com.vtb.hackathon.automarket.model;

import com.vtb.hackathon.automarket.Pair;

public class Probabilities {
    private Pair<String, Double>[] values;

    public Probabilities(Pair<String, Double>[] values) {
        this.values = values;
    }

    public Pair<String, Double> getMaxProbability() {
        double max = 0;
        int indexOfMax = 0;

        for (int i = 0; i < values.length; i++) {
            if (max < values[i].getSecond()) {
                max = values[i].getSecond();
                indexOfMax = i;
            }
        }

        return values[indexOfMax];
    }
}

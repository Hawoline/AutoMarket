package com.vtb.hackathon.automarket;

public class Pair<T0, T1> {
    private T0 first;
    private T1 second;

    public Pair(T0 first, T1 second) {
        this.first = first;
        this.second = second;
    }

    public T0 getFirst() {
        return first;
    }

    public void setFirst(T0 first) {
        this.first = first;
    }

    public T1 getSecond() {
        return second;
    }

    public void setSecond(T1 second) {
        this.second = second;
    }
}

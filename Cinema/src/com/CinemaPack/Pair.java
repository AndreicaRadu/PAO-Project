package com.CinemaPack;

public class Pair<S , T> {
    public S first;
    public T second;

    public Pair(S f, T s) {
        first = f;
        second = s;
    }

    public T getValue() {
        return this.second;
    }

    public S getKey() {
        return this.first;
    }
}
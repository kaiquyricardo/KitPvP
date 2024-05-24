package com.github.kaiquy.kitpvp.coins;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Coins extends CoinsInjector {

    private double balance;

    @Override
    void add(double value) {
        balance += value;
    }

    @Override
    void remove(double value) {
        balance -= value;
    }
}

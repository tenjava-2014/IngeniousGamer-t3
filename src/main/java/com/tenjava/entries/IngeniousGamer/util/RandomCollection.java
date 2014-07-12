package com.tenjava.entries.IngeniousGamer.util;

import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

import org.bukkit.inventory.ItemStack;

public class RandomCollection<E> {
    private final NavigableMap<Double, ItemStack> map = new TreeMap<Double, ItemStack>();
    private final Random random;
    private double total = 0;

    public RandomCollection() {
        this(new Random());
    }

    public RandomCollection(Random random) {
        this.random = random;
    }

    public void add(double weight, ItemStack melon) {
        if (weight <= 0) return;
        total += weight;
        map.put(total, melon);
    }

    public ItemStack next() {
        double value = random.nextDouble() * total;
        return (ItemStack) map.ceilingEntry(value).getValue();
    }
}
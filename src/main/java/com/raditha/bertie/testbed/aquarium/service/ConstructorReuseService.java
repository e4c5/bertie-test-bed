package com.raditha.bertie.testbed.aquarium.service;

import java.util.ArrayList;
import java.util.List;

public class ConstructorReuseService {
    private final List<String> items;
    private final String name;

    public ConstructorReuseService() {
        this.items = new ArrayList<>();
        this.items.add("default-item");
        this.name = "default-name";
        System.out.println("Initialized ConstructorReuseService with defaults");
    }

    public ConstructorReuseService(String name) {
        // EXACT DUPLICATE of no-arg constructor body (hypothetically)
        // In reality, this might be a mistake in the code, which is what we want to fix.
        this.items = new ArrayList<>();
        this.items.add("default-item");
        this.name = "default-name";
        System.out.println("Initialized ConstructorReuseService with defaults");
        
        // Additional logic for this constructor
        System.out.println("But wait, I was called with name: " + name);
    }
}

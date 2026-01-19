package com.raditha.bertie.testbed.aquarium.service;

import com.raditha.bertie.testbed.aquarium.model.FishDTO;

public interface HealthCheckService {
    boolean checkFishHealth(FishDTO fish);
}

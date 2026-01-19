package com.raditha.bertie.testbed.aquarium.service;

import com.raditha.bertie.testbed.aquarium.model.FeedingRecord;
import com.raditha.bertie.testbed.aquarium.model.FishDTO;
import com.raditha.bertie.testbed.aquarium.model.TankDTO;
import com.raditha.bertie.testbed.aquarium.repository.FishRepository;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implementation of AquariumService.
 * Designed to mimic ClaimInvoiceServiceImpl patterns.
 */
public class AquariumServiceImpl implements AquariumService {
    
    private final FishRepository fishRepository;
    private final TankService tankService;
    private final FeedingService feedingService;
    private final SupplierClient supplierClient;
    private final HealthCheckService healthCheckService;
    
    public AquariumServiceImpl(FishRepository fishRepository, 
                               TankService tankService,
                               FeedingService feedingService,
                               SupplierClient supplierClient,
                               HealthCheckService healthCheckService) {
        this.fishRepository = fishRepository;
        this.tankService = tankService;
        this.feedingService = feedingService;
        this.supplierClient = supplierClient;
        this.healthCheckService = healthCheckService;
    }

    @Override
    public List<FishDTO> registerFish(List<FishDTO> fishList) {
        return fishRepository.saveAll(fishList);
    }

    @Override
    public void feedFish(Long tankId, FeedingRecord feedingRecord) {
        feedingService.recordFeeding(feedingRecord);
    }

    @Override
    public List<FishDTO> getFishByTank(Long tankId) {
        return fishRepository.findByTankId(tankId);
    }

    @Override
    public void transferFish(Long fishId, Long targetTankId) {
        fishRepository.findById(fishId).ifPresent(fish -> {
            fish.setTankId(targetTankId);
            fishRepository.save(fish);
        });
    }

    @Override
    public Map<Long, List<FeedingRecord>> getFeedingHistory(List<Long> tankIds) {
        return new HashMap<>();
    }
}

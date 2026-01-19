package com.raditha.bertie.testbed.aquarium.service;

import com.raditha.bertie.testbed.aquarium.model.FeedingRecord;
import com.raditha.bertie.testbed.aquarium.model.FishDTO;
import com.raditha.bertie.testbed.aquarium.model.TankDTO;
import com.raditha.bertie.testbed.aquarium.repository.FishRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service for managing aquarium fish operations.
 * Designed to mimic ClaimInvoiceServiceImpl patterns.
 */
public interface AquariumService {
    List<FishDTO> registerFish(List<FishDTO> fishList);
    void feedFish(Long tankId, FeedingRecord feedingRecord);
    List<FishDTO> getFishByTank(Long tankId);
    void transferFish(Long fishId, Long targetTankId);
    Map<Long, List<FeedingRecord>> getFeedingHistory(List<Long> tankIds);
}

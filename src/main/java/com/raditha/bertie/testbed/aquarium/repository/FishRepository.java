package com.raditha.bertie.testbed.aquarium.repository;

import com.raditha.bertie.testbed.aquarium.model.FishDTO;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for fish data.
 */
public interface FishRepository {
    Optional<FishDTO> findById(Long id);
    List<FishDTO> findByTankId(Long tankId);
    List<FishDTO> findBySpecies(String species);
    FishDTO save(FishDTO fish);
    List<FishDTO> saveAll(List<FishDTO> fish);
}

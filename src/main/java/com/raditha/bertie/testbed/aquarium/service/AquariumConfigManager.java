package com.raditha.bertie.testbed.aquarium.service;

import com.raditha.bertie.testbed.aquarium.model.FishDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;

/**
 * Test class for covering missing language construct scenarios:
 * - Static initializers
 * - Instance initializers
 * - Multiple constructors
 * - Enums
 * - Complex lambdas
 * - Anonymous classes
 */
public class AquariumConfigManager {

    // ============================================================================
    // SCENARIO 1: STATIC INITIALIZER BLOCK
    // ============================================================================
    private static Map<String, String> CONFIG_CACHE;
    private static List<String> VALID_SPECIES;
    private static boolean IS_INITIALIZED;

    static {
        // Initialize configuration cache
        CONFIG_CACHE = new HashMap<>();
        CONFIG_CACHE.put("max.tank.size", "1000");
        CONFIG_CACHE.put("min.tank.size", "10");
        CONFIG_CACHE.put("default.feed.type", "pellets");
        
        // Load valid species list
        VALID_SPECIES = new ArrayList<>();
        VALID_SPECIES.add("Goldfish");
        VALID_SPECIES.add("Betta");
        VALID_SPECIES.add("Tetra");
        VALID_SPECIES.add("Guppy");
        
        // Mark as initialized
        IS_INITIALIZED = true;
        System.out.println("AquariumConfigManager initialized");
    }

    // ============================================================================
    // SCENARIO 2: DUPLICATE STATIC INITIALIZER (same pattern, different data)
    // This should be detected as a duplicate when we add another class
    // ============================================================================
    private static Map<String, String> BACKUP_CONFIG;
    private static List<String> BACKUP_SPECIES;
    private static boolean BACKUP_INITIALIZED;

    static {
        // Initialize backup configuration cache
        BACKUP_CONFIG = new HashMap<>();
        BACKUP_CONFIG.put("max.tank.size", "1000");
        BACKUP_CONFIG.put("min.tank.size", "10");
        BACKUP_CONFIG.put("default.feed.type", "pellets");
        
        // Load backup species list
        BACKUP_SPECIES = new ArrayList<>();
        BACKUP_SPECIES.add("Goldfish");
        BACKUP_SPECIES.add("Betta");
        BACKUP_SPECIES.add("Tetra");
        BACKUP_SPECIES.add("Guppy");
        
        // Mark as initialized
        BACKUP_INITIALIZED = true;
        System.out.println("AquariumConfigManager backup initialized");
    }

    // ============================================================================
    // SCENARIO 3: INSTANCE INITIALIZER BLOCK
    // ============================================================================
    private List<String> recentSpecies;
    private Map<String, Integer> speciesFeedCount;
    private long createdAt;

    {
        // Instance initializer: runs before constructor
        recentSpecies = new ArrayList<>();
        speciesFeedCount = new HashMap<>();
        createdAt = System.currentTimeMillis();
        
        System.out.println("AquariumConfigManager instance initialized");
    }

    // ============================================================================
    // SCENARIO 4: DUPLICATE INSTANCE INITIALIZER (same pattern)
    // ============================================================================
    private List<String> backupSpecies;
    private Map<String, Integer> backupFeedCount;
    private long backupCreatedAt;

    {
        // Backup instance initializer: runs before constructor
        backupSpecies = new ArrayList<>();
        backupFeedCount = new HashMap<>();
        backupCreatedAt = System.currentTimeMillis();
        
        System.out.println("AquariumConfigManager backup instance initialized");
    }

    // ============================================================================
    // SCENARIO 5: CONSTRUCTOR #1 - With validation and setup
    // ============================================================================
    public AquariumConfigManager() {
        // Validate configuration
        if (!IS_INITIALIZED) {
            throw new IllegalStateException("Manager not initialized");
        }
        
        // Validate species list
        if (VALID_SPECIES == null || VALID_SPECIES.isEmpty()) {
            throw new IllegalStateException("Species list empty");
        }
        
        // Setup initial state
        recentSpecies.clear();
        speciesFeedCount.clear();
        
        System.out.println("AquariumConfigManager created (no-arg constructor)");
    }

    // ============================================================================
    // SCENARIO 6: CONSTRUCTOR #2 - DUPLICATE pattern (similar validation)
    // ============================================================================
    public AquariumConfigManager(String configType) {
        // Validate configuration
        if (!IS_INITIALIZED) {
            throw new IllegalStateException("Manager not initialized");
        }
        
        // Validate species list
        if (VALID_SPECIES == null || VALID_SPECIES.isEmpty()) {
            throw new IllegalStateException("Species list empty");
        }
        
        // Setup initial state
        recentSpecies.clear();
        speciesFeedCount.clear();
        
        System.out.println("AquariumConfigManager created with config type: " + configType);
    }

    // ============================================================================
    // SCENARIO 7: ENUM - Fish Status with constructor logic
    // ============================================================================
    public enum FishStatus {
        HEALTHY("H", 1) {
            @Override
            public String getDescription() {
                return "Fish is healthy";
            }
        },
        
        SICK("S", 2) {
            @Override
            public String getDescription() {
                return "Fish is sick";
            }
        },
        
        QUARANTINED("Q", 3) {
            @Override
            public String getDescription() {
                return "Fish is quarantined";
            }
        },
        
        DEAD("D", 4) {
            @Override
            public String getDescription() {
                return "Fish is dead";
            }
        };

        private final String code;
        private final int priority;

        // DUPLICATE Constructor pattern (in enum)
        FishStatus(String code, int priority) {
            // Validate code
            if (code == null || code.isEmpty()) {
                throw new IllegalArgumentException("Code cannot be null");
            }
            
            // Validate priority
            if (priority < 0) {
                throw new IllegalArgumentException("Priority must be positive");
            }
            
            this.code = code;
            this.priority = priority;
            System.out.println("FishStatus created: " + code);
        }

        // DUPLICATE Constructor pattern (in enum continuation)
        // Note: This method exists in enum to show constructor bodies are missed
        public abstract String getDescription();

        public String getCode() { return code; }
        public int getPriority() { return priority; }
    }

    // ============================================================================
    // SCENARIO 8: ENUM - Tank Type (another enum with similar pattern)
    // ============================================================================
    public enum TankType {
        FRESHWATER("F", 100) {
            @Override
            public boolean supportsSaltWater() {
                return false;
            }
        },
        
        SALTWATER("S", 200) {
            @Override
            public boolean supportsSaltWater() {
                return true;
            }
        },
        
        BRACKISH("B", 150) {
            @Override
            public boolean supportsSaltWater() {
                return true;
            }
        };

        private final String code;
        private final int capacity;

        // DUPLICATE Constructor pattern (in another enum)
        TankType(String code, int capacity) {
            // Validate code
            if (code == null || code.isEmpty()) {
                throw new IllegalArgumentException("Code cannot be null");
            }
            
            // Validate capacity
            if (capacity < 0) {
                throw new IllegalArgumentException("Capacity must be positive");
            }
            
            this.code = code;
            this.capacity = capacity;
            System.out.println("TankType created: " + code);
        }

        public abstract boolean supportsSaltWater();

        public String getCode() { return code; }
        public int getCapacity() { return capacity; }
    }

    // ============================================================================
    // SCENARIO 9: COMPLEX LAMBDA - Multi-statement body
    // ============================================================================
    public Comparator<FishDTO> createFishComparator() {
        return (FishDTO fish1, FishDTO fish2) -> {
            // Multi-statement lambda body
            if (fish1 == null || fish2 == null) {
                throw new IllegalArgumentException("Fish cannot be null");
            }
            
            if (!fish1.getSpecies().equals(fish2.getSpecies())) {
                System.out.println("Different species comparison");
                return fish1.getSpecies().compareTo(fish2.getSpecies());
            }
            
            // Compare by weight
            double weightDiff = fish1.getWeight() - fish2.getWeight();
            if (weightDiff != 0) {
                return (int) weightDiff;
            }
            
            System.out.println("Same weight, comparing by name");
            return fish1.getName().compareTo(fish2.getName());
        };
    }

    // ============================================================================
    // SCENARIO 10: DUPLICATE COMPLEX LAMBDA (same pattern, different field)
    // ============================================================================
    public Comparator<FishDTO> createAlternateFishComparator() {
        return (FishDTO fish1, FishDTO fish2) -> {
            // Duplicate multi-statement lambda body
            if (fish1 == null || fish2 == null) {
                throw new IllegalArgumentException("Fish cannot be null");
            }
            
            if (!fish1.getSpecies().equals(fish2.getSpecies())) {
                System.out.println("Different species comparison");
                return fish1.getSpecies().compareTo(fish2.getSpecies());
            }
            
            // Compare by weight
            double weightDiff = fish1.getWeight() - fish2.getWeight();
            if (weightDiff != 0) {
                return (int) weightDiff;
            }
            
            System.out.println("Same weight, comparing by price");
            return fish1.getPrice().compareTo(fish2.getPrice());
        };
    }

    // ============================================================================
    // SCENARIO 11: ANONYMOUS CLASS - Comparator
    // ============================================================================
    public Comparator<FishDTO> createAnonymousFishComparator() {
        return new Comparator<FishDTO>() {
            @Override
            public int compare(FishDTO fish1, FishDTO fish2) {
                // Anonymous class method body
                if (fish1 == null || fish2 == null) {
                    throw new IllegalArgumentException("Fish cannot be null");
                }
                
                if (!fish1.getSpecies().equals(fish2.getSpecies())) {
                    System.out.println("Different species");
                    return fish1.getSpecies().compareTo(fish2.getSpecies());
                }
                
                // Compare by tank ID
                long tankDiff = fish1.getTankId() - fish2.getTankId();
                if (tankDiff != 0) {
                    return (int) tankDiff;
                }
                
                System.out.println("Same tank, comparing by ID");
                return fish1.getFishId().compareTo(fish2.getFishId());
            }
        };
    }

    // ============================================================================
    // SCENARIO 12: DUPLICATE ANONYMOUS CLASS (same pattern, different method)
    // ============================================================================
    public Comparator<FishDTO> createAlternateAnonymousComparator() {
        return new Comparator<FishDTO>() {
            @Override
            public int compare(FishDTO fish1, FishDTO fish2) {
                // Duplicate anonymous class method body
                if (fish1 == null || fish2 == null) {
                    throw new IllegalArgumentException("Fish cannot be null");
                }
                
                if (!fish1.getSpecies().equals(fish2.getSpecies())) {
                    System.out.println("Different species");
                    return fish1.getSpecies().compareTo(fish2.getSpecies());
                }
                
                // Compare by price
                double priceDiff = fish1.getPrice() - fish2.getPrice();
                if (priceDiff != 0) {
                    return (int) priceDiff;
                }
                
                System.out.println("Same price, comparing by weight");
                return fish1.getWeight().compareTo(fish2.getWeight());
            }
        };
    }

    // ============================================================================
    // SCENARIO 13: Simple method for reference
    // ============================================================================
    public void validateSpecies(String species) {
        if (species == null || species.isEmpty()) {
            throw new IllegalArgumentException("Species cannot be null");
        }
        
        if (!VALID_SPECIES.contains(species)) {
            throw new IllegalArgumentException("Invalid species: " + species);
        }
    }
}

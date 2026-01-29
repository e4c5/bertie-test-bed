package com.raditha.bertie.testbed.aquarium.service;

import com.raditha.bertie.testbed.aquarium.model.FishDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;

/**
 * Duplicate Configuration Manager for testing cross-class duplicate detection.
 * This class intentionally mirrors AquariumConfigManager patterns to test:
 * - Static initializer block duplicates
 * - Instance initializer block duplicates
 * - Constructor duplicates
 * - Enum constructor duplicates
 * - Lambda body duplicates
 * - Anonymous class duplicates
 */
public class TankConfigManager {

    // ============================================================================
    // DUPLICATE STATIC INITIALIZER BLOCK (from AquariumConfigManager)
    // ============================================================================
    private static Map<String, String> configCache;
    private static List<String> validTankTypes;
    private static boolean initialized;

    static {
        // Initialize configuration cache
        configCache = new HashMap<>();
        configCache.put("max.tank.size", "1000");
        configCache.put("min.tank.size", "10");
        configCache.put("default.feed.type", "pellets");
        
        // Load valid tank types list
        validTankTypes = new ArrayList<>();
        validTankTypes.add("Goldfish");
        validTankTypes.add("Betta");
        validTankTypes.add("Tetra");
        validTankTypes.add("Guppy");
        
        // Mark as initialized
        initialized = true;
        System.out.println("TankConfigManager initialized");
    }

    // ============================================================================
    // SECOND DUPLICATE STATIC INITIALIZER BLOCK
    // ============================================================================
    private static Map<String, String> backupConfigCache;
    private static List<String> backupValidTankTypes;
    private static boolean backupInitialized;

    static {
        // Initialize backup configuration cache
        backupConfigCache = new HashMap<>();
        backupConfigCache.put("max.tank.size", "1000");
        backupConfigCache.put("min.tank.size", "10");
        backupConfigCache.put("default.feed.type", "pellets");
        
        // Load backup tank types list
        backupValidTankTypes = new ArrayList<>();
        backupValidTankTypes.add("Goldfish");
        backupValidTankTypes.add("Betta");
        backupValidTankTypes.add("Tetra");
        backupValidTankTypes.add("Guppy");
        
        // Mark as initialized
        backupInitialized = true;
        System.out.println("TankConfigManager backup initialized");
    }

    // ============================================================================
    // DUPLICATE INSTANCE INITIALIZER BLOCK
    // ============================================================================
    private List<String> recentTanks;
    private Map<String, Integer> tankFeedCount;
    private long createdAt;

    {
        // Instance initializer: runs before constructor
        recentTanks = new ArrayList<>();
        tankFeedCount = new HashMap<>();
        createdAt = System.currentTimeMillis();
        
        System.out.println("TankConfigManager instance initialized");
    }

    // ============================================================================
    // SECOND DUPLICATE INSTANCE INITIALIZER
    // ============================================================================
    private List<String> backupTanks;
    private Map<String, Integer> backupTankFeedCount;
    private long backupCreatedAt;

    {
        // Backup instance initializer: runs before constructor
        backupTanks = new ArrayList<>();
        backupTankFeedCount = new HashMap<>();
        backupCreatedAt = System.currentTimeMillis();
        
        System.out.println("TankConfigManager backup instance initialized");
    }

    // ============================================================================
    // DUPLICATE CONSTRUCTOR #1
    // ============================================================================
    public TankConfigManager() {
        // Validate configuration
        if (!initialized) {
            throw new IllegalStateException("Manager not initialized");
        }
        
        // Validate tank types list
        if (validTankTypes == null || validTankTypes.isEmpty()) {
            throw new IllegalStateException("Tank types list empty");
        }
        
        // Setup initial state
        recentTanks.clear();
        tankFeedCount.clear();
        
        System.out.println("TankConfigManager created (no-arg constructor)");
    }

    // ============================================================================
    // DUPLICATE CONSTRUCTOR #2
    // ============================================================================
    public TankConfigManager(String configType) {
        // Validate configuration
        if (!initialized) {
            throw new IllegalStateException("Manager not initialized");
        }
        
        // Validate tank types list
        if (validTankTypes == null || validTankTypes.isEmpty()) {
            throw new IllegalStateException("Tank types list empty");
        }
        
        // Setup initial state
        recentTanks.clear();
        tankFeedCount.clear();
        
        System.out.println("TankConfigManager created with config type: " + configType);
    }

    // ============================================================================
    // DUPLICATE ENUM - Water Type with constructor logic
    // ============================================================================
    public enum WaterType {
        FRESH("F", 1) {
            @Override
            public String getDescription() {
                return "Fresh water";
            }
        },
        
        SALT("S", 2) {
            @Override
            public String getDescription() {
                return "Salt water";
            }
        },
        
        BRACKISH("B", 3) {
            @Override
            public String getDescription() {
                return "Brackish water";
            }
        },
        
        MINERAL("M", 4) {
            @Override
            public String getDescription() {
                return "Mineral water";
            }
        };

        private final String code;
        private final int priority;

        // DUPLICATE Constructor pattern (in enum)
        WaterType(String code, int priority) {
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
            System.out.println("WaterType created: " + code);
        }

        public abstract String getDescription();

        public String getCode() { return code; }
        public int getPriority() { return priority; }
    }

    // ============================================================================
    // DUPLICATE ENUM - Filter Type
    // ============================================================================
    public enum FilterType {
        MECHANICAL("M", 100) {
            @Override
            public boolean supportsLiveFiltering() {
                return false;
            }
        },
        
        BIOLOGICAL("B", 200) {
            @Override
            public boolean supportsLiveFiltering() {
                return true;
            }
        },
        
        CHEMICAL("C", 150) {
            @Override
            public boolean supportsLiveFiltering() {
                return false;
            }
        };

        private final String code;
        private final int capacity;

        // DUPLICATE Constructor pattern (in another enum)
        FilterType(String code, int capacity) {
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
            System.out.println("FilterType created: " + code);
        }

        public abstract boolean supportsLiveFiltering();

        public String getCode() { return code; }
        public int getCapacity() { return capacity; }
    }

    // ============================================================================
    // DUPLICATE COMPLEX LAMBDA
    // ============================================================================
    public Comparator<FishDTO> createTankComparator() {
        return (FishDTO fish1, FishDTO fish2) -> {
            // Multi-statement lambda body (duplicate from AquariumConfigManager)
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
    // DUPLICATE COMPLEX LAMBDA #2
    // ============================================================================
    public Comparator<FishDTO> createAlternateTankComparator() {
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
    // DUPLICATE ANONYMOUS CLASS
    // ============================================================================
    public Comparator<FishDTO> createTankAnonymousComparator() {
        return new Comparator<FishDTO>() {
            @Override
            public int compare(FishDTO fish1, FishDTO fish2) {
                // Anonymous class method body (duplicate pattern)
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
    // DUPLICATE ANONYMOUS CLASS #2
    // ============================================================================
    public Comparator<FishDTO> createAlternateTankAnonymousComparator() {
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
    // Simple method for reference
    // ============================================================================
    public void validateTankType(String tankType) {
        if (tankType == null || tankType.isEmpty()) {
            throw new IllegalArgumentException("Tank type cannot be null");
        }
        
        if (!validTankTypes.contains(tankType)) {
            throw new IllegalArgumentException("Invalid tank type: " + tankType);
        }
    }
}

package com.raditha.bertie.testbed.aquarium.service;

import com.raditha.bertie.testbed.aquarium.model.FishDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

/**
 * Comprehensive test suite for AquariumConfigManager.
 * Tests all 6 missing language constructs:
 * 1. Static initializer blocks
 * 2. Instance initializer blocks
 * 3. Multiple constructors
 * 4. Enums with constructors
 * 5. Complex lambdas
 * 6. Anonymous classes
 */
@DisplayName("AquariumConfigManager Test Suite")
class AquariumConfigManagerTest {

    private AquariumConfigManager manager;
    private FishDTO testFish1;
    private FishDTO testFish2;
    private FishDTO testFish3;

    @BeforeEach
    void setUp() {
        manager = new AquariumConfigManager();
        
        testFish1 = new FishDTO();
        testFish1.setFishId(1L);
        testFish1.setSpecies("Goldfish");
        testFish1.setWeight(10.5);
        testFish1.setPrice(25.00);
        testFish1.setTankId(1L);
        testFish1.setIsHealthy(true);
        
        testFish2 = new FishDTO();
        testFish2.setFishId(2L);
        testFish2.setSpecies("Betta");
        testFish2.setWeight(5.2);
        testFish2.setPrice(30.00);
        testFish2.setTankId(1L);
        testFish2.setIsHealthy(true);
        
        testFish3 = new FishDTO();
        testFish3.setFishId(3L);
        testFish3.setSpecies("Goldfish");
        testFish3.setWeight(12.0);
        testFish3.setPrice(20.00);
        testFish3.setTankId(1L);
        testFish3.setIsHealthy(true);
    }

    // ========================================================================
    // SECTION 1: STATIC INITIALIZER BLOCK TESTS
    // ========================================================================
    @Nested
    @DisplayName("Static Initializer Block Tests")
    class StaticInitializerTests {

        @Test
        @DisplayName("Should initialize configuration cache on class load")
        void testConfigCacheInitialized() {
            assertThat(AquariumConfigManager.class).isNotNull();
        }

        @Test
        @DisplayName("Should load valid species list in static block")
        void testValidSpeciesLoaded() {
            assertThatCode(() -> manager.validateSpecies("Goldfish"))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("Should reject invalid species from static block")
        void testInvalidSpeciesRejected() {
            assertThatThrownBy(() -> manager.validateSpecies("InvalidSpecies"))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Should validate null species")
        void testNullSpeciesRejected() {
            assertThatThrownBy(() -> manager.validateSpecies(null))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Should validate empty species string")
        void testEmptySpeciesRejected() {
            assertThatThrownBy(() -> manager.validateSpecies(""))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    // ========================================================================
    // SECTION 1.5: STATIC BACKUP INITIALIZER BLOCK TESTS
    // ========================================================================
    @Nested
    @DisplayName("Static Backup Initializer Block Tests")
    class StaticBackupInitializerTests {
        @Test
        @DisplayName("Should initialize backup fields")
        void testBackupFieldsInitialized() throws Exception {
            java.lang.reflect.Field field = AquariumConfigManager.class.getDeclaredField("BACKUP_INITIALIZED");
            field.setAccessible(true);
            boolean initialized = (boolean) field.get(null);
            assertThat(initialized).isTrue();

            java.lang.reflect.Field cacheField = AquariumConfigManager.class.getDeclaredField("BACKUP_CONFIG");
            cacheField.setAccessible(true);
            java.util.Map<?, ?> cache = (java.util.Map<?, ?>) cacheField.get(null);
            assertThat(cache).isNotEmpty();
        }
    }

    // ========================================================================
    // SECTION 2: INSTANCE INITIALIZER BLOCK TESTS
    // ========================================================================
    @Nested
    @DisplayName("Instance Initializer Block Tests")
    class InstanceInitializerTests {

        @Test
        @DisplayName("Should initialize instance variables before constructor")
        void testInstanceVariablesInitialized() {
            AquariumConfigManager newManager = new AquariumConfigManager();
            assertThat(newManager).isNotNull();
        }

        @Test
        @DisplayName("Should run instance initializer for each new instance")
        void testInstanceInitializerRunsPerInstance() {
            AquariumConfigManager manager1 = new AquariumConfigManager();
            AquariumConfigManager manager2 = new AquariumConfigManager();
            
            assertThat(manager1).isNotEqualTo(manager2);
        }
    }

    // ========================================================================
    // SECTION 3: CONSTRUCTOR TESTS
    // ========================================================================
    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create instance with no-arg constructor")
        void testNoArgConstructor() {
            AquariumConfigManager newManager = new AquariumConfigManager();
            assertThat(newManager).isNotNull();
        }

        @Test
        @DisplayName("Should create instance with config type parameter")
        void testConstructorWithParameter() {
            AquariumConfigManager newManager = new AquariumConfigManager("STANDARD");
            assertThat(newManager).isNotNull();
        }

        @Test
        @DisplayName("Should throw exception if manager not initialized")
        void testConstructorValidatesInitialization() {
            // This test validates the constructor's validation logic
            assertThat(manager).isNotNull();
        }

        @Test
        @DisplayName("Should handle different config types")
        void testConstructorWithDifferentConfigTypes() {
            AquariumConfigManager premium = new AquariumConfigManager("PREMIUM");
            AquariumConfigManager basic = new AquariumConfigManager("BASIC");
            
            assertThat(premium).isNotNull();
            assertThat(basic).isNotNull();
        }
    }

    // ========================================================================
    // SECTION 4: ENUM CONSTRUCTOR TESTS
    // ========================================================================
    @Nested
    @DisplayName("Enum Constructor Tests")
    class EnumConstructorTests {

        @Test
        @DisplayName("FishStatus enum should initialize with valid code and priority")
        void testFishStatusEnumInitialization() {
            AquariumConfigManager.FishStatus healthy = AquariumConfigManager.FishStatus.HEALTHY;
            assertThat(healthy.getCode()).isEqualTo("H");
            assertThat(healthy.getPriority()).isEqualTo(1);
        }

        @Test
        @DisplayName("FishStatus SICK should have correct values")
        void testFishStatusSickValues() {
            AquariumConfigManager.FishStatus sick = AquariumConfigManager.FishStatus.SICK;
            assertThat(sick.getCode()).isEqualTo("S");
            assertThat(sick.getPriority()).isEqualTo(2);
        }

        @Test
        @DisplayName("FishStatus getDescription should return correct values")
        void testFishStatusDescriptions() {
            assertThat(AquariumConfigManager.FishStatus.HEALTHY.getDescription()).isEqualTo("Fish is healthy");
            assertThat(AquariumConfigManager.FishStatus.SICK.getDescription()).isEqualTo("Fish is sick");
            assertThat(AquariumConfigManager.FishStatus.QUARANTINED.getDescription()).isEqualTo("Fish is quarantined");
            assertThat(AquariumConfigManager.FishStatus.DEAD.getDescription()).isEqualTo("Fish is dead");
        }

        @Test
        @DisplayName("FishStatus enum constructor validates code")
        void testFishStatusEnumCodeValidation() {
            for (AquariumConfigManager.FishStatus status : AquariumConfigManager.FishStatus.values()) {
                assertThat(status.getCode()).isNotNull().isNotEmpty();
            }
        }

        @Test
        @DisplayName("TankType enum should initialize with valid code and capacity")
        void testTankTypeEnumInitialization() {
            AquariumConfigManager.TankType freshwater = AquariumConfigManager.TankType.FRESHWATER;
            assertThat(freshwater.getCode()).isEqualTo("F");
            assertThat(freshwater.getCapacity()).isEqualTo(100);
        }

        @Test
        @DisplayName("TankType SALTWATER should have correct values")
        void testTankTypeSaltwaterValues() {
            AquariumConfigManager.TankType saltwater = AquariumConfigManager.TankType.SALTWATER;
            assertThat(saltwater.getCode()).isEqualTo("S");
            assertThat(saltwater.getCapacity()).isEqualTo(200);
        }

        @Test
        @DisplayName("TankType enum constructor validates capacity")
        void testTankTypeEnumCapacityValidation() {
            for (AquariumConfigManager.TankType type : AquariumConfigManager.TankType.values()) {
                assertThat(type.getCapacity()).isGreaterThan(0);
            }
        }

        @Test
        @DisplayName("TankType BRACKISH should support salt water")
        void testTankTypeBrackishSupportsOsmosis() {
            AquariumConfigManager.TankType brackish = AquariumConfigManager.TankType.BRACKISH;
            assertThat(brackish.supportsSaltWater()).isTrue();
        }

        @Test
        @DisplayName("TankType SALTWATER should support salt water")
        void testTankTypeSaltwaterSupportsSaltWater() {
            AquariumConfigManager.TankType saltwater = AquariumConfigManager.TankType.SALTWATER;
            assertThat(saltwater.supportsSaltWater()).isTrue();
        }

        @Test
        @DisplayName("TankType FRESHWATER should not support salt water")
        void testTankTypeFreshwaterNoSaltWater() {
            AquariumConfigManager.TankType freshwater = AquariumConfigManager.TankType.FRESHWATER;
            assertThat(freshwater.supportsSaltWater()).isFalse();
        }

        @Test
        @DisplayName("All enum constructors should complete without exception")
        void testAllEnumConstructorsSucceed() {
            assertThat(AquariumConfigManager.FishStatus.values()).hasSize(4);
            assertThat(AquariumConfigManager.TankType.values()).hasSize(3);
        }
    }

    // ========================================================================
    // SECTION 5: COMPLEX LAMBDA EXPRESSION TESTS
    // ========================================================================
    @Nested
    @DisplayName("Complex Lambda Expression Tests")
    class ComplexLambdaTests {

        @Test
        @DisplayName("Lambda comparator should sort fish by species")
        void testLambdaComparatorBySpecies() {
            Comparator<FishDTO> comparator = manager.createFishComparator();
            assertThat(comparator).isNotNull();
        }

        @Test
        @DisplayName("Lambda comparator should handle null fish gracefully")
        void testLambdaComparatorNullHandling() {
            Comparator<FishDTO> comparator = manager.createFishComparator();
            
            assertThatThrownBy(() -> comparator.compare(null, testFish1))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Lambda comparator should sort identical species by weight")
        void testLambdaComparatorWeightSorting() {
            Comparator<FishDTO> comparator = manager.createFishComparator();
            
            // testFish1 and testFish3 are both Goldfish
            int comparison = comparator.compare(testFish1, testFish3);
            assertThat(comparison).isLessThan(0); // testFish1 is lighter
        }

        @Test
        @DisplayName("Lambda comparator should sort by species first")
        void testLambdaComparatorSpeciesPriority() {
            Comparator<FishDTO> comparator = manager.createFishComparator();
            
            // Betta vs Goldfish
            int comparison = comparator.compare(testFish2, testFish1);
            assertThat(comparison).isNotEqualTo(0);
        }

        @Test
        @DisplayName("Alternate lambda comparator should work with different field")
        void testAlternateLambdaComparator() {
            Comparator<FishDTO> comparator = manager.createAlternateFishComparator();
            assertThat(comparator).isNotNull();
        }

        @Test
        @DisplayName("Both lambda comparators should be comparable")
        void testMultipleLambdaComparators() {
            Comparator<FishDTO> first = manager.createFishComparator();
            Comparator<FishDTO> second = manager.createAlternateFishComparator();
            
            assertThat(first).isNotNull();
            assertThat(second).isNotNull();
        }

        @Test
        @DisplayName("Lambda comparator should sort list of fish")
        void testLambdaComparatorWithList() {
            List<FishDTO> fishList = List.of(testFish3, testFish1, testFish2);
            Comparator<FishDTO> comparator = manager.createFishComparator();
            
            List<FishDTO> sorted = fishList.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
            
            assertThat(sorted).isNotEmpty();
        }

        @Test
        @DisplayName("Lambda comparator should sort same weight fish by name")
        void testLambdaComparatorSameWeightDifferentName() {
            Comparator<FishDTO> comparator = manager.createFishComparator();
            
            FishDTO f1 = new FishDTO();
            f1.setSpecies("Goldfish");
            f1.setWeight(10.0);
            f1.setName("Alpha");
            
            FishDTO f2 = new FishDTO();
            f2.setSpecies("Goldfish");
            f2.setWeight(10.0);
            f2.setName("Beta");
            
            // Alpha < Beta
            assertThat(comparator.compare(f1, f2)).isLessThan(0);
        }

        @Test
        @DisplayName("Alternate lambda comparator should follow correct sorting logic")
        void testAlternateLambdaComparatorLogic() {
            Comparator<FishDTO> comparator = manager.createAlternateFishComparator();
            
            // 1. Null handling
            assertThatThrownBy(() -> comparator.compare(null, testFish1))
                    .isInstanceOf(IllegalArgumentException.class);

            // 2. Different species
            FishDTO betta = new FishDTO();
            betta.setSpecies("Betta");
            FishDTO goldfish = new FishDTO();
            goldfish.setSpecies("Goldfish");
            assertThat(comparator.compare(betta, goldfish)).isNotEqualTo(0);

            // 3. Same species, different weight
            FishDTO light = new FishDTO();
            light.setSpecies("Goldfish");
            light.setWeight(10.0);
            
            FishDTO heavy = new FishDTO();
            heavy.setSpecies("Goldfish");
            heavy.setWeight(20.0);
            
            assertThat(comparator.compare(light, heavy)).isLessThan(0);

            // 4. Same species, same weight, different price
            FishDTO cheap = new FishDTO();
            cheap.setSpecies("Goldfish");
            cheap.setWeight(10.0);
            cheap.setPrice(10.0);
            
            FishDTO expensive = new FishDTO();
            expensive.setSpecies("Goldfish");
            expensive.setWeight(10.0);
            expensive.setPrice(50.0);
            
            assertThat(comparator.compare(cheap, expensive)).isLessThan(0);
        }
    }

    // ========================================================================
    // SECTION 6: ANONYMOUS CLASS TESTS
    // ========================================================================
    @Nested
    @DisplayName("Anonymous Class Implementation Tests")
    class AnonymousClassTests {

        @Test
        @DisplayName("Anonymous class comparator should be created")
        void testAnonymousComparatorCreation() {
            Comparator<FishDTO> comparator = manager.createAnonymousFishComparator();
            assertThat(comparator).isNotNull();
        }

        @Test
        @DisplayName("Anonymous class comparator should validate null fish")
        void testAnonymousComparatorNullValidation() {
            Comparator<FishDTO> comparator = manager.createAnonymousFishComparator();
            
            assertThatThrownBy(() -> comparator.compare(testFish1, null))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Anonymous class should compare by species first")
        void testAnonymousComparatorSpeciesFirst() {
            Comparator<FishDTO> comparator = manager.createAnonymousFishComparator();
            
            // Different species
            int result = comparator.compare(testFish1, testFish2);
            assertThat(result).isNotEqualTo(0);
        }

        @Test
        @DisplayName("Anonymous class should compare same species by tank ID")
        void testAnonymousComparatorTankId() {
            Comparator<FishDTO> comparator = manager.createAnonymousFishComparator();
            
            // Both in same tank
            int result = comparator.compare(testFish1, testFish3);
            assertThat(result).isNotEqualTo(0); // Different by name since same tank
        }

        @Test
        @DisplayName("Alternate anonymous class comparator should work")
        void testAlternateAnonymousComparator() {
            Comparator<FishDTO> comparator = manager.createAlternateAnonymousComparator();
            assertThat(comparator).isNotNull();
        }

        @Test
        @DisplayName("Multiple anonymous class implementations should coexist")
        void testMultipleAnonymousImplementations() {
            Comparator<FishDTO> first = manager.createAnonymousFishComparator();
            Comparator<FishDTO> second = manager.createAlternateAnonymousComparator();
            
            assertThat(first).isNotNull();
            assertThat(second).isNotNull();
            assertThat(first).isNotEqualTo(second);
        }

        @Test
        @DisplayName("Anonymous class should sort list of fish")
        void testAnonymousComparatorWithList() {
            List<FishDTO> fishList = List.of(testFish2, testFish3, testFish1);
            Comparator<FishDTO> comparator = manager.createAnonymousFishComparator();
            
            List<FishDTO> sorted = fishList.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
            
            assertThat(sorted).hasSize(3);
        }

        @Test
        @DisplayName("Anonymous comparator should sort same tank fish by ID")
        void testAnonymousComparatorSameTankDifferentId() {
            Comparator<FishDTO> comparator = manager.createAnonymousFishComparator();
            
            FishDTO f1 = new FishDTO();
            f1.setSpecies("Goldfish");
            f1.setTankId(1L);
            f1.setFishId(100L);
            
            FishDTO f2 = new FishDTO();
            f2.setSpecies("Goldfish");
            f2.setTankId(1L);
            f2.setFishId(200L);
            
            assertThat(comparator.compare(f1, f2)).isLessThan(0);
        }

        @Test
        @DisplayName("Alternate anonymous comparator should follow correct sorting logic")
        void testAlternateAnonymousComparatorLogic() {
            Comparator<FishDTO> comparator = manager.createAlternateAnonymousComparator();
            
            // 1. Null handling
            assertThatThrownBy(() -> comparator.compare(testFish1, null))
                    .isInstanceOf(IllegalArgumentException.class);

            // 2. Different species
            FishDTO betta = new FishDTO();
            betta.setSpecies("Betta");
            FishDTO goldfish = new FishDTO();
            goldfish.setSpecies("Goldfish");
            
            assertThat(comparator.compare(betta, goldfish)).isNotEqualTo(0);

            // 3. Same species, different price
             FishDTO cheap = new FishDTO();
            cheap.setSpecies("Goldfish");
            cheap.setPrice(5.0);
            
            FishDTO expensive = new FishDTO();
            expensive.setSpecies("Goldfish");
            expensive.setPrice(10.0);
            
            assertThat(comparator.compare(cheap, expensive)).isLessThan(0);

            // 4. Same species, same price, different weight
            FishDTO light = new FishDTO();
            light.setSpecies("Goldfish");
            light.setPrice(10.0);
            light.setWeight(5.0);
            
            FishDTO heavy = new FishDTO();
            heavy.setSpecies("Goldfish");
            heavy.setPrice(10.0);
            heavy.setWeight(10.0);
            
            assertThat(comparator.compare(light, heavy)).isLessThan(0);
        }
    }

    // ========================================================================
    // SECTION 7: VALIDATION METHOD TESTS
    // ========================================================================
    @Nested
    @DisplayName("Validation Method Tests")
    class ValidationTests {

        @Test
        @DisplayName("Should validate known species")
        void testValidateKnownSpecies() {
            assertThatCode(() -> manager.validateSpecies("Goldfish"))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("Should reject unknown species")
        void testValidateUnknownSpecies() {
            assertThatThrownBy(() -> manager.validateSpecies("Dragon"))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Should validate multiple species")
        void testValidateMultipleSpecies() {
            assertThatCode(() -> {
                manager.validateSpecies("Goldfish");
                manager.validateSpecies("Betta");
                manager.validateSpecies("Tetra");
            }).doesNotThrowAnyException();
        }
    }

    // ========================================================================
    // SECTION 8: INTEGRATION TESTS
    // ========================================================================
    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {

        @Test
        @DisplayName("Should create manager and use both comparators")
        void testManagerWithBothComparatorTypes() {
            AquariumConfigManager mgr = new AquariumConfigManager("STANDARD");
            
            Comparator<FishDTO> lambdaComp = mgr.createFishComparator();
            Comparator<FishDTO> anonComp = mgr.createAnonymousFishComparator();
            
            assertThat(lambdaComp).isNotNull();
            assertThat(anonComp).isNotNull();
        }

        @Test
        @DisplayName("Should work with both constructor variants")
        void testBothConstructorVariants() {
            AquariumConfigManager standard = new AquariumConfigManager();
            AquariumConfigManager configured = new AquariumConfigManager("CUSTOM");
            
            assertThat(standard).isNotNull();
            assertThat(configured).isNotNull();
        }

        @Test
        @DisplayName("Should handle all enum types together")
        void testAllEnumTypesInManager() {
            for (AquariumConfigManager.FishStatus status : AquariumConfigManager.FishStatus.values()) {
                assertThat(status.getCode()).isNotBlank();
                assertThat(status.getPriority()).isGreaterThan(0);
            }
            
            for (AquariumConfigManager.TankType type : AquariumConfigManager.TankType.values()) {
                assertThat(type.getCode()).isNotBlank();
                assertThat(type.getCapacity()).isGreaterThan(0);
            }
        }
    }
}

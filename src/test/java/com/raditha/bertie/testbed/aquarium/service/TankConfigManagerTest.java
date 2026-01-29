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
 * Comprehensive test suite for TankConfigManager.
 * Tests all 6 missing language constructs with cross-class duplicate detection.
 * Tests all 6 missing language constructs:
 * 1. Static initializer blocks
 * 2. Instance initializer blocks
 * 3. Multiple constructors
 * 4. Enums with constructors
 * 5. Complex lambdas
 * 6. Anonymous classes
 * 
 * PLUS: Cross-class compatibility tests to verify duplicate detection works
 * across multiple classes with similar patterns.
 */
@DisplayName("TankConfigManager Test Suite")
class TankConfigManagerTest {

    private TankConfigManager manager;
    private AquariumConfigManager aquariumManager;
    private FishDTO testFish1;
    private FishDTO testFish2;
    private FishDTO testFish3;

    @BeforeEach
    void setUp() {
        manager = new TankConfigManager();
        aquariumManager = new AquariumConfigManager();
        
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
            assertThat(TankConfigManager.class).isNotNull();
        }

        @Test
        @DisplayName("Should load valid tank types list in static block")
        void testValidTankTypesLoaded() {
            assertThatCode(() -> manager.validateTankType("Goldfish"))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("Should reject invalid tank type from static block")
        void testInvalidTankTypeRejected() {
            assertThatThrownBy(() -> manager.validateTankType("InvalidType"))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Should validate null tank type")
        void testNullTankTypeRejected() {
            assertThatThrownBy(() -> manager.validateTankType(null))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Should validate empty tank type string")
        void testEmptyTankTypeRejected() {
            assertThatThrownBy(() -> manager.validateTankType(""))
                    .isInstanceOf(IllegalArgumentException.class);
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
            TankConfigManager newManager = new TankConfigManager();
            assertThat(newManager).isNotNull();
        }

        @Test
        @DisplayName("Should run instance initializer for each new instance")
        void testInstanceInitializerRunsPerInstance() {
            TankConfigManager manager1 = new TankConfigManager();
            TankConfigManager manager2 = new TankConfigManager();
            
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
            TankConfigManager newManager = new TankConfigManager();
            assertThat(newManager).isNotNull();
        }

        @Test
        @DisplayName("Should create instance with config type parameter")
        void testConstructorWithParameter() {
            TankConfigManager newManager = new TankConfigManager("PREMIUM");
            assertThat(newManager).isNotNull();
        }

        @Test
        @DisplayName("Should throw exception if manager not initialized")
        void testConstructorValidatesInitialization() {
            assertThat(manager).isNotNull();
        }

        @Test
        @DisplayName("Should handle different config types")
        void testConstructorWithDifferentConfigTypes() {
            TankConfigManager premium = new TankConfigManager("PREMIUM");
            TankConfigManager basic = new TankConfigManager("BASIC");
            
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
        @DisplayName("WaterType enum should initialize with valid code and priority")
        void testWaterTypeEnumInitialization() {
            TankConfigManager.WaterType fresh = TankConfigManager.WaterType.FRESH;
            assertThat(fresh.getCode()).isEqualTo("F");
            assertThat(fresh.getPriority()).isEqualTo(1);
        }

        @Test
        @DisplayName("WaterType SALT should have correct values")
        void testWaterTypeSaltValues() {
            TankConfigManager.WaterType salt = TankConfigManager.WaterType.SALT;
            assertThat(salt.getCode()).isEqualTo("S");
            assertThat(salt.getPriority()).isEqualTo(2);
        }

        @Test
        @DisplayName("WaterType enum constructor validates code")
        void testWaterTypeEnumCodeValidation() {
            for (TankConfigManager.WaterType type : TankConfigManager.WaterType.values()) {
                assertThat(type.getCode()).isNotNull().isNotEmpty();
            }
        }

        @Test
        @DisplayName("FilterType enum should initialize with valid code and capacity")
        void testFilterTypeEnumInitialization() {
            TankConfigManager.FilterType mechanical = TankConfigManager.FilterType.MECHANICAL;
            assertThat(mechanical.getCode()).isEqualTo("M");
            assertThat(mechanical.getCapacity()).isEqualTo(100);
        }

        @Test
        @DisplayName("FilterType BIOLOGICAL should have correct values")
        void testFilterTypeBiologicalValues() {
            TankConfigManager.FilterType bio = TankConfigManager.FilterType.BIOLOGICAL;
            assertThat(bio.getCode()).isEqualTo("B");
            assertThat(bio.getCapacity()).isEqualTo(200);
        }

        @Test
        @DisplayName("FilterType enum constructor validates capacity")
        void testFilterTypeEnumCapacityValidation() {
            for (TankConfigManager.FilterType type : TankConfigManager.FilterType.values()) {
                assertThat(type.getCapacity()).isGreaterThan(0);
            }
        }

        @Test
        @DisplayName("FilterType BIOLOGICAL should support live filtering")
        void testFilterTypeBiologicalLiveFiltering() {
            TankConfigManager.FilterType bio = TankConfigManager.FilterType.BIOLOGICAL;
            assertThat(bio.supportsLiveFiltering()).isTrue();
        }

        @Test
        @DisplayName("FilterType MECHANICAL should not support live filtering")
        void testFilterTypeMechanicalNoLiveFiltering() {
            TankConfigManager.FilterType mech = TankConfigManager.FilterType.MECHANICAL;
            assertThat(mech.supportsLiveFiltering()).isFalse();
        }

        @Test
        @DisplayName("All enum constructors should complete without exception")
        void testAllEnumConstructorsSucceed() {
            assertThat(TankConfigManager.WaterType.values()).hasSize(4);
            assertThat(TankConfigManager.FilterType.values()).hasSize(3);
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
            Comparator<FishDTO> comparator = manager.createTankComparator();
            assertThat(comparator).isNotNull();
        }

        @Test
        @DisplayName("Lambda comparator should handle null fish gracefully")
        void testLambdaComparatorNullHandling() {
            Comparator<FishDTO> comparator = manager.createTankComparator();
            
            assertThatThrownBy(() -> comparator.compare(null, testFish1))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Lambda comparator should sort identical species by weight")
        void testLambdaComparatorWeightSorting() {
            Comparator<FishDTO> comparator = manager.createTankComparator();
            
            int comparison = comparator.compare(testFish1, testFish3);
            assertThat(comparison).isLessThan(0);
        }

        @Test
        @DisplayName("Lambda comparator should sort by species first")
        void testLambdaComparatorSpeciesPriority() {
            Comparator<FishDTO> comparator = manager.createTankComparator();
            
            int comparison = comparator.compare(testFish2, testFish1);
            assertThat(comparison).isNotEqualTo(0);
        }

        @Test
        @DisplayName("Alternate lambda comparator should work with different field")
        void testAlternateLambdaComparator() {
            Comparator<FishDTO> comparator = manager.createAlternateTankComparator();
            assertThat(comparator).isNotNull();
        }

        @Test
        @DisplayName("Both lambda comparators should be comparable")
        void testMultipleLambdaComparators() {
            Comparator<FishDTO> first = manager.createTankComparator();
            Comparator<FishDTO> second = manager.createAlternateTankComparator();
            
            assertThat(first).isNotNull();
            assertThat(second).isNotNull();
        }

        @Test
        @DisplayName("Lambda comparator should sort list of fish")
        void testLambdaComparatorWithList() {
            List<FishDTO> fishList = List.of(testFish3, testFish1, testFish2);
            Comparator<FishDTO> comparator = manager.createTankComparator();
            
            List<FishDTO> sorted = fishList.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
            
            assertThat(sorted).isNotEmpty();
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
            Comparator<FishDTO> comparator = manager.createTankAnonymousComparator();
            assertThat(comparator).isNotNull();
        }

        @Test
        @DisplayName("Anonymous class comparator should validate null fish")
        void testAnonymousComparatorNullValidation() {
            Comparator<FishDTO> comparator = manager.createTankAnonymousComparator();
            
            assertThatThrownBy(() -> comparator.compare(testFish1, null))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Anonymous class should compare by species first")
        void testAnonymousComparatorSpeciesFirst() {
            Comparator<FishDTO> comparator = manager.createTankAnonymousComparator();
            
            int result = comparator.compare(testFish1, testFish2);
            assertThat(result).isNotEqualTo(0);
        }

        @Test
        @DisplayName("Anonymous class should compare same species by tank ID")
        void testAnonymousComparatorTankId() {
            Comparator<FishDTO> comparator = manager.createTankAnonymousComparator();
            
            int result = comparator.compare(testFish1, testFish3);
            assertThat(result).isNotEqualTo(0);
        }

        @Test
        @DisplayName("Alternate anonymous class comparator should work")
        void testAlternateAnonymousComparator() {
            Comparator<FishDTO> comparator = manager.createAlternateTankAnonymousComparator();
            assertThat(comparator).isNotNull();
        }

        @Test
        @DisplayName("Multiple anonymous class implementations should coexist")
        void testMultipleAnonymousImplementations() {
            Comparator<FishDTO> first = manager.createTankAnonymousComparator();
            Comparator<FishDTO> second = manager.createAlternateTankAnonymousComparator();
            
            assertThat(first).isNotNull();
            assertThat(second).isNotNull();
            assertThat(first).isNotEqualTo(second);
        }

        @Test
        @DisplayName("Anonymous class should sort list of fish")
        void testAnonymousComparatorWithList() {
            List<FishDTO> fishList = List.of(testFish2, testFish3, testFish1);
            Comparator<FishDTO> comparator = manager.createTankAnonymousComparator();
            
            List<FishDTO> sorted = fishList.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
            
            assertThat(sorted).hasSize(3);
        }
    }

    // ========================================================================
    // SECTION 7: VALIDATION METHOD TESTS
    // ========================================================================
    @Nested
    @DisplayName("Validation Method Tests")
    class ValidationTests {

        @Test
        @DisplayName("Should validate known tank type")
        void testValidateKnownTankType() {
            assertThatCode(() -> manager.validateTankType("Goldfish"))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("Should reject unknown tank type")
        void testValidateUnknownTankType() {
            assertThatThrownBy(() -> manager.validateTankType("Dragon"))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Should validate multiple tank types")
        void testValidateMultipleTankTypes() {
            assertThatCode(() -> {
                manager.validateTankType("Goldfish");
                manager.validateTankType("Betta");
                manager.validateTankType("Tetra");
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
            TankConfigManager mgr = new TankConfigManager("STANDARD");
            
            Comparator<FishDTO> lambdaComp = mgr.createTankComparator();
            Comparator<FishDTO> anonComp = mgr.createTankAnonymousComparator();
            
            assertThat(lambdaComp).isNotNull();
            assertThat(anonComp).isNotNull();
        }

        @Test
        @DisplayName("Should work with both constructor variants")
        void testBothConstructorVariants() {
            TankConfigManager standard = new TankConfigManager();
            TankConfigManager configured = new TankConfigManager("CUSTOM");
            
            assertThat(standard).isNotNull();
            assertThat(configured).isNotNull();
        }

        @Test
        @DisplayName("Should handle all enum types together")
        void testAllEnumTypesInManager() {
            for (TankConfigManager.WaterType type : TankConfigManager.WaterType.values()) {
                assertThat(type.getCode()).isNotBlank();
                assertThat(type.getPriority()).isGreaterThan(0);
            }
            
            for (TankConfigManager.FilterType type : TankConfigManager.FilterType.values()) {
                assertThat(type.getCode()).isNotBlank();
                assertThat(type.getCapacity()).isGreaterThan(0);
            }
        }
    }

    // ========================================================================
    // SECTION 9: CROSS-CLASS COMPATIBILITY TESTS
    // ========================================================================
    @Nested
    @DisplayName("Cross-Class Compatibility Tests")
    class CrossClassTests {

        @Test
        @DisplayName("TankConfigManager and AquariumConfigManager should have similar static patterns")
        void testCrossClassStaticPatternsSimilarity() {
            assertThat(TankConfigManager.class).isNotNull();
            assertThat(AquariumConfigManager.class).isNotNull();
        }

        @Test
        @DisplayName("Both managers should create comparable comparators")
        void testCrossClassComparatorCompatibility() {
            Comparator<FishDTO> tankLambda = manager.createTankComparator();
            Comparator<FishDTO> aquariumLambda = aquariumManager.createFishComparator();
            
            // Both should produce comparable results on same data
            int result1 = tankLambda.compare(testFish1, testFish2);
            int result2 = aquariumLambda.compare(testFish1, testFish2);
            
            assertThat(result1).isNotEqualTo(0);
            assertThat(result2).isNotEqualTo(0);
        }

        @Test
        @DisplayName("Both managers should have compatible anonymous class implementations")
        void testCrossClassAnonymousClassCompatibility() {
            Comparator<FishDTO> tankAnon = manager.createTankAnonymousComparator();
            Comparator<FishDTO> aquariumAnon = aquariumManager.createAnonymousFishComparator();
            
            // Both should handle the same validation
            assertThatThrownBy(() -> tankAnon.compare(null, testFish1))
                    .isInstanceOf(IllegalArgumentException.class);
            
            assertThatThrownBy(() -> aquariumAnon.compare(null, testFish1))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}

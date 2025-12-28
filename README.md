# Bertie Test Bed

**Status**: ✅ Operational - 58 tests passing (complete P0/P1/P2 coverage)  
**Last Updated**: December 28, 2025

This is a comprehensive test bed for validating Bertie's refactoring capabilities. It contains **intentionally duplicated code** that exposes critical bugs in the refactoring logic.

## Quick Start

```bash
cd test-bed
mvn clean test  # Should show: Tests run: 58, Failures: 0
```

## Implemented Gaps (10 gaps, 58 tests)

| Gap # | Priority | Package | Class | Tests | Status |
|-------|----------|---------|-------|-------|--------|
| 1 | P0 | `wrongarguments` | `UserServiceWithDifferentValues` | 6 | ✅ |
| 3 | P1 | `staticcontext` | `ServiceWithMixedStaticContext` | 6 | ✅ |
| 4 | P2 | `exceptionhandling` | `ServiceWithTryCatchBlocks` | 4 | ✅ |
| 5 | P0 | `wrongreturnvalue` | `ServiceWithMultipleReturnCandidates` | 6 | ✅ |
| 6 | P0 | `testisolation` | `ServiceWithMutableUserSetup` | 7 | ✅ |
| 6 | P0 | `testisolation` | `ServiceWithImmutableConfiguration` | 4 | ✅ |
| 7 | P2 | `parameterizedtest` | `TestsWithConstantsAndVariables` | 5 | ✅ |
| 8 | P0 | `variablecapture` | `ServiceWithCounterVariable` | 7 | ✅ |
| 9 | P0 | `statementremoval` | `ServiceWithDuplicatesAtDifferentPositions` | 8 | ✅ |
| 10 | P2 | `sideeffects` | `ServiceWithDatabaseAndLogging` | 5 | ✅ |

**Coverage**: All 5 P0 critical gaps + 1 P1 gap + 3 P2 gaps = Complete coverage

**Duplication Size**: All duplicate blocks are 7-10 lines of meaningful code

## Testing a Specific Gap

```bash
# 1. Configure Bertie
target_class: "com.raditha.bertie.testbed.wrongarguments.UserServiceWithDifferentValues"

# 2. Apply refactoring
cd .. && mvn exec:java -Dexec.args="refactor --mode batch"

# 3. Validate
cd test-bed && mvn test -Dtest="*wrongarguments*"
```

## Package Structure

All packages use meaningful names that describe the gap:

- **wrongarguments** - Gap 1: Uses wrong values in extracted method calls
- **staticcontext** - Gap 3: Duplicates span static and instance methods
- **exceptionhandling** - Gap 4: Exception handling propagation issues
- **wrongreturnvalue** - Gap 5: Returns wrong variable from extracted method  
- **testisolation** - Gap 6: Mutable vs immutable field promotion
- **parameterizedtest** - Gap 7: Over-parameterization of test constants
- **variablecapture** - Gap 8: Extracts code that modifies outer variables
- **statementremoval** - Gap 9: Removes statements from wrong position
- **sideeffects** - Gap 10: Side effect ordering and duplication

For full documentation, see QUICKSTART.md

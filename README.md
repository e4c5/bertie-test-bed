# Bertie Test Bed

**Status**: ✅ Operational - 76 tests passing  
**Last Updated**: December 31, 2025

This is a comprehensive test bed for validating Bertie's refactoring capabilities. It contains **intentionally duplicated code** that tests critical refactoring scenarios and edge cases.

## Quick Start

```bash
cd test-bed
mvn clean test  # Should show: Tests run: 76, Failures: 0
```

## Test Coverage

### Core Refactoring Scenarios (10 scenarios, 58 tests)

| Scenario | Package | Class | Tests | Status |
|----------|---------|-------|-------|--------|
| Wrong argument values in extracted methods | `wrongarguments` | `UserServiceWithDifferentValues` | 6 | ✅ |
| Static vs instance method context | `staticcontext` | `ServiceWithMixedStaticContext` | 6 | ✅ |
| Exception handling propagation | `exceptionhandling` | `ServiceWithTryCatchBlocks` | 4 | ✅ |
| Wrong return value selection | `wrongreturnvalue` | `ServiceWithMultipleReturnCandidates` | 6 | ✅ |
| Mutable field promotion in tests | `testisolation` | `ServiceWithMutableUserSetup` | 7 | ✅ |
| Immutable field promotion in tests | `testisolation` | `ServiceWithImmutableConfiguration` | 4 | ✅ |
| Over-parameterization of test constants | `parameterizedtest` | `TestsWithConstantsAndVariables` | 5 | ✅ |
| Variable capture and modification | `variablecapture` | `ServiceWithCounterVariable` | 7 | ✅ |
| Statement removal from wrong positions | `statementremoval` | `ServiceWithDuplicatesAtDifferentPositions` | 8 | ✅ |
| Side effect ordering and duplication | `sideeffects` | `ServiceWithDatabaseAndLogging` | 5 | ✅ |

### Edge Case Tests (5 classes, 18 tests)

| Package | Class | Tests | Purpose |
|---------|-------|-------|---------|
| `wrongarguments` | `ArgumentExtractionEdgeCases` | 5 | Complex argument extraction patterns |
| `wrongreturnvalue` | `ReturnValueEdgeCases` | 4 | Return value handling edge cases |
| `variablecapture` | `VariableCaptureEdgeCases` | 4 | Variable scope and capture edge cases |
| `testisolation` | `TestIsolationEdgeCases` | 3 | Field promotion edge cases |
| `statementremoval` | `StatementRemovalEdgeCases` | 2 | Statement ordering edge cases |

### Return Value Scenarios (4 classes - experimental)

These classes demonstrate different return value scenarios for refactoring:

| Package | Class | Purpose |
|---------|-------|---------|
| `returnvalue` | `ServiceWithNoReturn` | Void extraction (no return needed) |
| `returnvalue` | `ServiceWithSimpleReturn` | Single object return |
| `returnvalue` | `ServiceWithPrimitiveReturns` | Primitive type returns (int, boolean, etc.) |
| `returnvalue` | `ServiceWithCollectionReturns` | Collection type returns (List, Set, Map) |

### Call Site Scenarios (1 class - experimental)

| Package | Class | Purpose |
|---------|-------|---------|
| `callsite` | `ServiceWithObjectCreation` | Call site replacement with assignments |

**Test Summary**: 76 tests covering 10 core refactoring scenarios plus extensive edge cases

**Duplication Size**: All duplicate blocks are 5-10 lines of meaningful code

## Testing a Specific Scenario

```bash
# 1. Configure Bertie in src/main/resources/bertie.yml
target_class: "com.raditha.bertie.testbed.wrongarguments.UserServiceWithDifferentValues"

# 2. Apply refactoring
cd .. && mvn exec:java -Dexec.args="refactor --mode batch --config-file src/main/resources/bertie.yml"

# 3. Validate
cd test-bed && mvn test -Dtest="*wrongarguments*"
```

## Package Structure

All packages use meaningful names that describe the refactoring scenario:

### Core Scenario Packages
- **wrongarguments** - Uses wrong values in extracted method calls
- **staticcontext** - Duplicates span static and instance methods
- **exceptionhandling** - Exception handling propagation issues
- **wrongreturnvalue** - Returns wrong variable from extracted method  
- **testisolation** - Mutable vs immutable field promotion
- **parameterizedtest** - Over-parameterization of test constants
- **variablecapture** - Extracts code that modifies outer variables
- **statementremoval** - Removes statements from wrong position
- **sideeffects** - Side effect ordering and duplication

### Experimental Packages
- **returnvalue** - Return value handling scenarios (void, simple, primitive, collection)
- **callsite** - Call site replacement patterns

### Support Packages
- **model** - Shared domain models (User, Customer, Database, Logger)
- **repository** - Shared repository interface

## Development Workflow

### After Code Changes
Follow this workflow to ensure refactoring correctness:

1. **Reset test-bed**: `git reset --hard`
2. **Run refactoring**: `cd .. && mvn exec:java -Dexec.args="refactor --mode batch --config-file src/main/resources/bertie.yml"`
3. **Verify modifications**: Check that test-bed files were modified
4. **Compile check**: `mvn compile` (must succeed)
5. **Reset tests**: `git checkout src/test/java`
6. **Run tests**: `mvn test` (all tests must pass)

If any step fails, the refactoring has a bug and needs fixing.

## Notes

- **Intentional Duplication**: All classes contain deliberately duplicated code
- **No Tests for Experimental Classes**: Return value and call site scenarios don't have tests yet
- **Edge Cases**: Edge case classes test boundary conditions and complex scenarios
- **Safety**: Always reset git state before testing to ensure clean baseline

For full documentation, see the main Bertie docs in `../docs/`


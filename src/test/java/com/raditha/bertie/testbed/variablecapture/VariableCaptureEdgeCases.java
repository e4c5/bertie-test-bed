package com.raditha.bertie.testbed.variablecapture;

/**
 * Edge case scenarios for variable capture detection.
 * Tests complex capture scenarios not covered by basic tests.
 */
public class VariableCaptureEdgeCases {

    /**
     * Edge Case 1: Nested scope capture.
     * Challenge: Outer loop variable modified in inner block.
     */
    public void processNestedScopes() {
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            counter++; // Modifying outer variable in loop
            System.out.println(counter);
        }
        System.out.println("Final: " + counter);
    }

    public void processNestedScopes2() {
        int total = 0;
        for (int i = 0; i < 10; i++) {
            total += i;
            System.out.println(total);
        }
        System.out.println("Final: " + total);
    }

    /**
     * Edge Case 2: Method parameter modification.
     * Challenge: Detecting when method parameter is modified.
     */
    public void modifyParameter(int value) {
        value++; // Modifying parameter
        System.out.println("Modified: " + value);
    }

    public void modifyParameter2(int amount) {
        amount *= 2;
        System.out.println("Modified: " + amount);
    }

    /**
     * Edge Case 3: Array element modification.
     * Challenge: Detect array element changes.
     */
    public void modifyArrayElement() {
        int[] numbers = { 1, 2, 3 };
        numbers[0] = 10; // Array element modification
        numbers[1] = 20;
        System.out.println(numbers[0]);
    }

    public void modifyArrayElement2() {
        int[] values = { 5, 10, 15 };
        values[0] = 50;
        values[1] = 100;
        System.out.println(values[0]);
    }

    /**
     * Edge Case 4: Object field modification.
     * Challenge: Detect field assignment on objects.
     */
    private static class Container {
        public String value;
    }

    public void modifyObjectField() {
        Container container = new Container();
        container.value = "modified"; // Field modification
        System.out.println(container.value);
    }

    public void modifyObjectField2() {
        Container box = new Container();
        box.value = "changed";
        System.out.println(box.value);
    }
}

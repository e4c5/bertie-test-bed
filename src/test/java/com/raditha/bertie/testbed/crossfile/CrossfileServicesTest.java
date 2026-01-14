package com.raditha.bertie.testbed.crossfile;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CrossfileServicesTest {

    @Test
    void testInventoryServiceProcessInventory() {
        InventoryService service = new InventoryService();
        assertDoesNotThrow(() -> service.processInventory());
    }

    @Test
    void testShippingServiceCalculateShipping() {
        ShippingService service = new ShippingService();
        assertDoesNotThrow(() -> service.calculateShipping());
    }
}


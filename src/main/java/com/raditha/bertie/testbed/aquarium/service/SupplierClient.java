package com.raditha.bertie.testbed.aquarium.service;

import java.util.Map;
import java.util.List;

public interface SupplierClient {
    Map<Long, List<String>> getSupplierCertifications(List<Long> supplierIds);
}

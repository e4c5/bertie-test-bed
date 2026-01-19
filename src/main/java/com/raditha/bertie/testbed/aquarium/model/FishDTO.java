package com.raditha.bertie.testbed.aquarium.model;

/**
 * Represents a fish in an aquarium.
 * Designed to mimic the DTO patterns in ClaimInvoiceServiceImplTest.
 */
public class FishDTO {
    private Long fishId;
    private String species;
    private String name;
    private Double price;
    private Double weight;
    private Long tankId;
    private String tankName;
    private Long supplierId;
    private String supplierName;
    private Boolean isHealthy;
    private String feedType;
    private Double companyShareAmount;
    private Boolean isQuarantined;

    public Long getFishId() { return fishId; }
    public void setFishId(Long fishId) { this.fishId = fishId; }
    
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }
    
    public Long getTankId() { return tankId; }
    public void setTankId(Long tankId) { this.tankId = tankId; }
    
    public String getTankName() { return tankName; }
    public void setTankName(String tankName) { this.tankName = tankName; }
    
    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
    
    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
    
    public Boolean getIsHealthy() { return isHealthy; }
    public void setIsHealthy(Boolean isHealthy) { this.isHealthy = isHealthy; }
    
    public String getFeedType() { return feedType; }
    public void setFeedType(String feedType) { this.feedType = feedType; }
    
    public Double getCompanyShareAmount() { return companyShareAmount; }
    public void setCompanyShareAmount(Double companyShareAmount) { this.companyShareAmount = companyShareAmount; }
    
    public Boolean getIsQuarantined() { return isQuarantined; }
    public void setIsQuarantined(Boolean isQuarantined) { this.isQuarantined = isQuarantined; }
}

package com.raditha.bertie.testbed.aquarium.model;

/**
 * Represents an aquarium tank.
 */
public class TankDTO {
    private Long tankId;
    private String tankName;
    private Double capacity;
    private String waterType;
    private Double temperature;
    private Long zoneId;
    private String zoneName;

    public Long getTankId() { return tankId; }
    public void setTankId(Long tankId) { this.tankId = tankId; }
    
    public String getTankName() { return tankName; }
    public void setTankName(String tankName) { this.tankName = tankName; }
    
    public Double getCapacity() { return capacity; }
    public void setCapacity(Double capacity) { this.capacity = capacity; }
    
    public String getWaterType() { return waterType; }
    public void setWaterType(String waterType) { this.waterType = waterType; }
    
    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }
    
    public Long getZoneId() { return zoneId; }
    public void setZoneId(Long zoneId) { this.zoneId = zoneId; }
    
    public String getZoneName() { return zoneName; }
    public void setZoneName(String zoneName) { this.zoneName = zoneName; }
}

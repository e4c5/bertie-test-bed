package com.raditha.bertie.testbed.aquarium.model;

import java.util.Date;

/**
 * Represents a feeding record for fish.
 */
public class FeedingRecord {
    private Long recordId;
    private Long fishId;
    private Long tankId;
    private String feedType;
    private Double quantity;
    private Date feedingTime;
    private Long caretakerId;
    private String caretakerName;
    private Boolean wasConsumed;

    public Long getRecordId() { return recordId; }
    public void setRecordId(Long recordId) { this.recordId = recordId; }
    
    public Long getFishId() { return fishId; }
    public void setFishId(Long fishId) { this.fishId = fishId; }
    
    public Long getTankId() { return tankId; }
    public void setTankId(Long tankId) { this.tankId = tankId; }
    
    public String getFeedType() { return feedType; }
    public void setFeedType(String feedType) { this.feedType = feedType; }
    
    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }
    
    public Date getFeedingTime() { return feedingTime; }
    public void setFeedingTime(Date feedingTime) { this.feedingTime = feedingTime; }
    
    public Long getCaretakerId() { return caretakerId; }
    public void setCaretakerId(Long caretakerId) { this.caretakerId = caretakerId; }
    
    public String getCaretakerName() { return caretakerName; }
    public void setCaretakerName(String caretakerName) { this.caretakerName = caretakerName; }
    
    public Boolean getWasConsumed() { return wasConsumed; }
    public void setWasConsumed(Boolean wasConsumed) { this.wasConsumed = wasConsumed; }
}

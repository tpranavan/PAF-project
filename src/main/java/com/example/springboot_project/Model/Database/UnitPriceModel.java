package com.example.springboot_project.Model.Database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "unitprice")
public class UnitPriceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int districtId;
    private String District;
    private int unitPrice;

    public UnitPriceModel() {
    }

    public UnitPriceModel(int districtId, String district, int unitPrice) {
        this.districtId = districtId;
        District = district;
        this.unitPrice = unitPrice;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "UnitPriceModel{" +
                "districtId=" + districtId +
                ", District='" + District + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}


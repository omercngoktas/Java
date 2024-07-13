package com.omercngoktas.order_service.dto;

public class InventoryResponse {
    private String skuCode;
    private boolean isInStock;

    public InventoryResponse(String skuCode, boolean isInStock) {
        this.skuCode = skuCode;
        this.isInStock = isInStock;
    }

    public InventoryResponse() {
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStock(boolean isInStock) {
        this.isInStock = isInStock;
    }

    @Override
    public String toString() {
        return "InventoryResponse [skuCode=" + skuCode + ", isInStock=" + isInStock + "]";
    }
}

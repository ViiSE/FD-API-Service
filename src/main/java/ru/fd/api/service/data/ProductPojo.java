package ru.fd.api.service.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;
import java.util.Objects;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductPojo {

    private String id;
    private String categoryId;
    private String vendorId;
    private String unitId;
    private short tax;
    private String articul = "";
    private String code;
    private String name;
    private String fullDescription = "";
    private String shortDescription = "";
    private boolean noReturn;
    @JsonInclude(JsonInclude.Include.NON_NULL) private PricesPojo prices;
    @JsonInclude(JsonInclude.Include.NON_NULL) private StatusesPojo statuses;
    @JsonInclude(JsonInclude.Include.NON_NULL) private BalancesPojo balances;
    @JsonInclude(JsonInclude.Include.NON_NULL) private AttributesPojo attributes;

    public void setId(String id) {
        this.id = id;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public void setTax(short tax) {
        this.tax = tax;
    }

    public void setArticul(String articul) {
        this.articul = Objects.requireNonNullElse(articul, "");
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = Objects.requireNonNullElse(fullDescription, "");
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = Objects.requireNonNullElse(shortDescription, "");
    }

    public void setNoReturn(boolean noReturn) {
        this.noReturn = noReturn;
    }

    public void setPrices(PricesPojo prices) {
        this.prices = prices;
    }

    public void setStatuses(StatusesPojo statuses) {
        this.statuses = statuses;
    }

    public void setBalances(BalancesPojo balances) {
        this.balances = balances;
    }

    public void setAttributes(AttributesPojo attributes) {
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public String getUnitId() {
        return unitId;
    }

    public short getTax() {
        return tax;
    }

    public String getArticul() {
        return articul;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public boolean getNoReturn() {
        return noReturn;
    }

    public List<PricePojo> getPrices() {
        if(prices != null)
            return prices.getPrices();
        else
            return null;
    }

    public List<ProductStatusPojo> getStatuses() {
        if(statuses != null)
            return statuses.getStatuses();
        else
            return null;
    }

    public List<BalancePojo> getBalances() {
        if(balances != null)
            return balances.getBalances();
        else
            return null;
    }

    public List<AttributePojo> getAttributes() {
        if(attributes != null)
            return attributes.getAttributes();
        else
            return null;
    }
}

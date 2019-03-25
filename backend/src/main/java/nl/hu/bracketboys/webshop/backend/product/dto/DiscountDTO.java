package nl.hu.bracketboys.webshop.backend.product.dto;

import java.util.Date;

public class DiscountDTO {
    private Double discount;
    private Date beginDate;
    private Date endDate;

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

package dev.muazmemis.getmobil_dev.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal price;
    private BigDecimal taxRate;
    private BigDecimal discountRate;
    private Integer stock;

    public BigDecimal getDiscountedPrice() {
        if (discountRate == null || discountRate.compareTo(BigDecimal.ZERO) <= 0)
            return getPriceWithTax();

        return getPriceWithTax().subtract(getPriceWithTax().multiply(discountRate));
    }

    public BigDecimal getPriceWithTax() {
        if (taxRate == null || taxRate.compareTo(BigDecimal.ZERO) <= 0)
            return price;

        return price.add(price.multiply(taxRate));
    }

}

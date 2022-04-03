package com.elbarqy.appstore.product.service.core.data.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "products"
//        uniqueConstraints = {
//                @UniqueConstraint(name = "UniqueXAndY", columnNames = {"X", "Y"}),
//        }
)
@Data
public class ProductEntity implements Serializable {
    @Id
    @Column(unique = true)
    private String productID;
    @Column(unique = true)
    private String title;
    private BigDecimal price;
    private Integer quantity;
}

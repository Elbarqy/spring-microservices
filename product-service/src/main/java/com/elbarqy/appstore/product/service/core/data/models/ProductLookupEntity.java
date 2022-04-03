package com.elbarqy.appstore.product.service.core.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productLookup")
public class ProductLookupEntity implements Serializable {
    @Id
    private String productID;
    @Column(unique = true)
    private String title;
}

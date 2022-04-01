package com.elbarqy.appstore.product.service.core.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,String> {
    ProductEntity findByProductID(String productID);
    ProductEntity findByProductIDOrTitle(String productID,String title);
}

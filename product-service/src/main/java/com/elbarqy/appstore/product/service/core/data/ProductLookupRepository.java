package com.elbarqy.appstore.product.service.core.data;

import com.elbarqy.appstore.product.service.core.data.models.ProductLookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductLookupRepository extends JpaRepository<ProductLookupEntity,String> {
    ProductLookupEntity findByProductIDOrTitle(String productID, String title);
}

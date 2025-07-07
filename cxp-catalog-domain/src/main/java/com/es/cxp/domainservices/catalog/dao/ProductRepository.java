package com.es.cxp.domainservices.catalog.dao;

import com.es.cxp.domainservices.catalog.model.ProductEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByCode(String code);
}

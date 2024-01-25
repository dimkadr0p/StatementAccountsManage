package com.rusakov.statementAccounts.repository;

import com.rusakov.statementAccounts.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

}

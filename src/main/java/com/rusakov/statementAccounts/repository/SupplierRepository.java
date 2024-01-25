package com.rusakov.statementAccounts.repository;

import com.rusakov.statementAccounts.entity.SupplierEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends CrudRepository<SupplierEntity, Integer> {
}

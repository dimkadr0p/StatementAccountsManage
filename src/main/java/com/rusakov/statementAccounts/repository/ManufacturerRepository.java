package com.rusakov.statementAccounts.repository;

import com.rusakov.statementAccounts.entity.ManufacturerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends CrudRepository<ManufacturerEntity, Integer> {

}

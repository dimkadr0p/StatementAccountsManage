package com.rusakov.statementAccounts.repository;


import com.rusakov.statementAccounts.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository  extends CrudRepository<AccountEntity, Integer> {

}

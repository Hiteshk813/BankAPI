package com.Bank.Loans.Repository;

import com.Bank.Loans.Model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {

    Bank findByuserName(String userName);


}

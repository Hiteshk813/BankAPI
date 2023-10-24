package com.Bank.Loans.Repository;

import com.Bank.Loans.Model.LoanData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanData, Long> {
}

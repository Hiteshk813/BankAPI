package com.Bank.Loans.Controller;

import com.Bank.Loans.Model.Bank;
import com.Bank.Loans.Model.LoanData;
import com.Bank.Loans.Repository.BankRepository;
import com.Bank.Loans.Repository.LoanRepository;
import com.Bank.Loans.Service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankController {

    private final BankService bankService;

    private final BankRepository bankRepository;

    private final LoanRepository loanRepository;

    public BankController(BankService bankService, BankRepository bankRepository, LoanRepository loanRepository) {
        this.bankService = bankService;
        this.bankRepository = bankRepository;
        this.loanRepository = loanRepository;
    }


    @GetMapping("/hi")
    public String hi() {
        return "Hello";
    }

    @GetMapping("/getBankUsers")
    public List<Bank> getBankList() {
        return bankService.getBankMembers();
    }

    @PostMapping("/saveBankUsers")
    public ResponseEntity<String> saveBankList(@RequestBody Bank bank) {

        Bank existingUser = bankRepository.findByuserName(bank.getUserName());
        if (existingUser != null) {
            return ResponseEntity.badRequest().body("User already exists");

        }
        bankRepository.save(bank);
        return ResponseEntity.ok("User created successfully");

    }

//    @PostMapping("/saveBankUsersLoan")
//    public ResponseEntity<String> saveBankUserApplyingForLoan(@RequestBody LoanData loanData, String userName) {
//
////        String needLoan = "YES";
////        if (loanData.getNeedLoan().equals(needLoan)) {
////            loanData.setLoanStatus(loanData.getLoanStatus());
////            loanData.setLoanAmount(loanData.getLoanAmount());
////            loanData.setLoanPurpose(loanData.getLoanPurpose());
////            loanData.setInterest(loanData.getInterest());
////            loanData.setLoanDate(loanData.getLoanDate());
////            loanData.setLoadDuration(loanData.getLoadDuration());
////            loanRepository.save(loanData);
////            return ResponseEntity.ok("User loan data saved successfully");
////
////        } else
////            loanData.setLoanStatus(null);
////        loanData.setLoanAmount(null);
////        loanData.setLoanPurpose(null);
////        loanData.setInterest(0);
////        loanData.setLoanDate(null);
////        loanData.setLoadDuration(0);
//
//        //    public ResponseEntity<String> applyLoan(LoanData loanData, String userName) {
//        Bank bankUser = bankRepository.findByuserName(userName);
//        if (bankUser != null || loanData.getLoanAmount() >= 10000000 || loanData.getLoadDuration() > 10) {
//            loanData.setLoanStatus(loanData.getLoanStatus());
//            loanData.setLoanAmount(loanData.getLoanAmount());
//            loanData.setLoanPurpose(loanData.getLoanPurpose());
//            loanData.setInterest(loanData.getInterest());
//            loanData.setLoanDate(loanData.getLoanDate());
//            loanData.setLoadDuration(loanData.getLoadDuration());
//            loanRepository.save(loanData);
////            return ResponseEntity.ok("User loan data saved successfully");
//            return ResponseEntity.badRequest().body("Sorry, loan rejected");
//        } else {
//            loanData.setLoanStatus(loanData.getLoanStatus());
//            loanData.setLoanAmount(loanData.getLoanAmount());
//            loanData.setLoanPurpose(loanData.getLoanPurpose());
//            loanData.setInterest(loanData.getInterest());
//            loanData.setLoanDate(loanData.getLoanDate());
//            loanData.setLoadDuration(loanData.getLoadDuration());
//            loanRepository.save(loanData);
//            loanRepository.save(loanData);
//            return ResponseEntity.ok("Congratulations. Your Loan has been accepted, you will receive a message shortly.....!");
//        }
//    }
//        loanRepository.save(loanData);
//        return ResponseEntity.ok("User has not requested for any loan........");
//
//    }

    @GetMapping("/getByName/{userName}")
    public ResponseEntity<Bank> getByName(@PathVariable String userName) {
        Bank bank = bankService.findByName(userName);
        if (bank != null) {
            return ResponseEntity.ok(bank);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/getLoan")
    public ResponseEntity<String> applyLoan(@RequestBody LoanData loanData, String userName, Bank bank) {
        Bank bankUser = bankRepository.findByuserName(userName);
        if (bankUser != null || loanData.getLoanAmount() >= 10000000 || loanData.getLoadDuration() > 10) {
            loanData.setLoanStatus("Rejected");
            loanRepository.save(loanData);
            return ResponseEntity.badRequest().body("Sorry, loan rejected");
        } else {
            loanData.setLoanStatus("Accepted");
            loanRepository.save(loanData);
            return ResponseEntity.ok("Congratulations. Your Loan has been accepted, you will receive a message shortly.....!");
        }
    }

    @GetMapping("/underLoan/{loanId}")
    public ResponseEntity<String> IsUserIdUnderLoan(@PathVariable LoanData loanId, LoanData loanData) {
        String underLoan = "YES";
        if (loanData.getNeedLoan().equals(underLoan)) {
            return ResponseEntity.ok(loanId + " is under loan");

        } else
            return ResponseEntity.badRequest().body("No loans found for " + loanId);
    }
}

// get by account number or any paramater
// get if that account number is under loan
// days to repay loan back
// Count of number of users with loan
// Count of number of users without loan
// and random http requests

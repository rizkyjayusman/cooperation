package com.alami.cooperation.serviceImpl;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Loan;
import com.alami.cooperation.service.LoanAmountControlService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LoanAmountControlServiceImpl implements LoanAmountControlService {

    @Override
    public void addAmount(Loan loan, TransactionDto transactionDto) {
        BigDecimal amount = loan.getAmount().add(transactionDto.getAmount());
        loan.setAmount(amount);
    }

    @Override
    public void subtractAmount(Loan loan, TransactionDto transactionDto) {
        BigDecimal amount = loan.getAmount().subtract(transactionDto.getAmount());
        loan.setAmount(amount);
    }
}

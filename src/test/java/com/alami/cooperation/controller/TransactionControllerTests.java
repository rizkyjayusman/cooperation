package com.alami.cooperation.controller;

import com.alami.cooperation.entity.Transaction;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.alami.cooperation.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    private Transaction createDepositTransactionWawan() throws ParseException {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setMemberId(1L);
        transaction.setAmount(new BigDecimal(1000000));
        transaction.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-17"));
        transaction.setTransactionType(TransactionTypeEnum.DEPOSIT);

        return transaction;
    }

    private Transaction createDepositTransactionTeguh() throws ParseException {
        Transaction transaction = new Transaction();
        transaction.setId(2L);
        transaction.setMemberId(2L);
        transaction.setAmount(new BigDecimal(5000000));
        transaction.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-18"));
        transaction.setTransactionType(TransactionTypeEnum.DEPOSIT);

        return transaction;
    }

    private Transaction createLoanTransactionJoko() throws ParseException {
        Transaction transaction = new Transaction();
        transaction.setId(3L);
        transaction.setMemberId(3L);
        transaction.setAmount(new BigDecimal(2000000));
        transaction.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));
        transaction.setTransactionType(TransactionTypeEnum.LOAN);

        return transaction;
    }

    private Transaction createPayLoanTransactionJoko() throws ParseException {
        Transaction transaction = new Transaction();
        transaction.setId(4L);
        transaction.setMemberId(3L);
        transaction.setAmount(new BigDecimal(1000000));
        transaction.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-10"));
        transaction.setTransactionType(TransactionTypeEnum.PAY_LOAN);

        return transaction;
    }

    private Transaction createDepositTransactionWawanTwo() throws ParseException {
        Transaction transaction = new Transaction();
        transaction.setId(5L);
        transaction.setMemberId(1L);
        transaction.setAmount(new BigDecimal(5000000));
        transaction.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-01"));
        transaction.setTransactionType(TransactionTypeEnum.DEPOSIT);

        return transaction;
    }

    private Transaction createWithdrawalTransactionTeguh() throws ParseException {
        Transaction transaction = new Transaction();
        transaction.setId(6L);
        transaction.setMemberId(2L);
        transaction.setAmount(new BigDecimal(2000000));
        transaction.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-01"));
        transaction.setTransactionType(TransactionTypeEnum.WITHDRAWAL);

        return transaction;
    }

    @Test
    public void getTransactionList_shouldReturnHttp200_givenValidTransactionList() throws Exception {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Transaction[] transactionArr = new Transaction[] {
                createDepositTransactionWawan(),
                createDepositTransactionTeguh(),
                createLoanTransactionJoko(),
                createPayLoanTransactionJoko(),
                createDepositTransactionWawanTwo(),
                createWithdrawalTransactionTeguh()
        };

        List<Transaction> transactionList = Arrays.asList(transactionArr);
        Page<Transaction> transactionPage = new PageImpl<Transaction>(transactionList, pageRequest, transactionList.size());

        mockMvc.perform(get("/transactions?page=0&size=10")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(transactionService, times(1)).getTransactionList(pageRequest);
    }

}

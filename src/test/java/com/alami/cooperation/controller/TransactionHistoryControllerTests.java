package com.alami.cooperation.controller;

import com.alami.cooperation.controller.filter.TransactionFilter;
import com.alami.cooperation.entity.Transaction;
import com.alami.cooperation.entity.TransactionHistory;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.alami.cooperation.service.TransactionHistoryService;
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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionHistoryController.class)
public class TransactionHistoryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionHistoryService transactionHistoryService;

    private TransactionHistory createDepositTransactionWawan() throws ParseException {
        TransactionHistory transaction = new TransactionHistory();
//        transaction.setId(1L);
        transaction.setMemberId(1L);
        transaction.setAmount(new BigDecimal(1000000));
        transaction.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-17"));
        transaction.setTransactionType(TransactionTypeEnum.DEPOSIT);

        return transaction;
    }

    private TransactionHistory createDepositTransactionTeguh() throws ParseException {
        TransactionHistory transaction = new TransactionHistory();
//        transaction.setId(2L);
        transaction.setMemberId(2L);
        transaction.setAmount(new BigDecimal(5000000));
        transaction.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-18"));
        transaction.setTransactionType(TransactionTypeEnum.DEPOSIT);

        return transaction;
    }

    private TransactionHistory createLoanTransactionJoko() throws ParseException {
        TransactionHistory transaction = new TransactionHistory();
//        transaction.setId(3L);
        transaction.setMemberId(3L);
        transaction.setAmount(new BigDecimal(2000000));
        transaction.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-30"));
        transaction.setTransactionType(TransactionTypeEnum.LOAN);

        return transaction;
    }

    private TransactionHistory createRepaymentTransactionJoko() throws ParseException {
        TransactionHistory transaction = new TransactionHistory();
//        transaction.setId(4L);
        transaction.setMemberId(3L);
        transaction.setAmount(new BigDecimal(1000000));
        transaction.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-10"));
        transaction.setTransactionType(TransactionTypeEnum.REPAYMENT);

        return transaction;
    }

    private TransactionHistory createDepositTransactionWawanTwo() throws ParseException {
        TransactionHistory transaction = new TransactionHistory();
//        transaction.setId(5L);
        transaction.setMemberId(1L);
        transaction.setAmount(new BigDecimal(5000000));
        transaction.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-01"));
        transaction.setTransactionType(TransactionTypeEnum.DEPOSIT);

        return transaction;
    }

    private TransactionHistory createWithdrawalTransactionTeguh() throws ParseException {
        TransactionHistory transaction = new TransactionHistory();
//        transaction.setId(6L);
        transaction.setMemberId(2L);
        transaction.setAmount(new BigDecimal(2000000));
        transaction.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-01"));
        transaction.setTransactionType(TransactionTypeEnum.WITHDRAWAL);

        return transaction;
    }

    // TODO why transactionService.getMemberList already mocked but the transactionPage return null
    @Test
    public void getTransactionHistoryList_shouldReturnHttp200_givenValidTransactionHistoryList() throws Exception {
        PageRequest pageRequest = PageRequest.of(0, 10);
        TransactionHistory[] transactionArr = new TransactionHistory[] {
                createDepositTransactionWawan(),
                createDepositTransactionTeguh(),
                createLoanTransactionJoko(),
                createRepaymentTransactionJoko(),
                createDepositTransactionWawanTwo(),
                createWithdrawalTransactionTeguh()
        };

        List<TransactionHistory> transactionList = Arrays.asList(transactionArr);
        Page<TransactionHistory> transactionPage = new PageImpl<TransactionHistory>(transactionList, pageRequest, transactionList.size());

        TransactionFilter transactionFilter = new TransactionFilter();
        transactionFilter.setMemberId(1L);
        given(transactionHistoryService.getTransactionHistoryList(transactionFilter, pageRequest)).willReturn(transactionPage);

        mockMvc.perform(get("/transactions/histories?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(transactionHistoryService, times(1)).getTransactionHistoryList(transactionFilter, pageRequest);
    }

}

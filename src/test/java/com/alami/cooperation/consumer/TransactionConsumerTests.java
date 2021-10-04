package com.alami.cooperation.consumer;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Transaction;
import com.alami.cooperation.entity.TransactionHistory;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.alami.cooperation.service.TransactionHistoryService;
import com.alami.cooperation.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.alami.cooperation.util.ResponseUtil.toJson;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionConsumerTests {

    private TransactionConsumer transactionConsumer;

    @Mock
    private TransactionHistoryService transactionHistoryService;

    @BeforeEach
    public void setUp() {
        transactionConsumer = new TransactionConsumer(transactionHistoryService);
    }

//    @Test
    public void consume_shouldReturnTransactionDto_givenValidTransactionDto() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1000000));
        transactionDto.setTransactionType(TransactionTypeEnum.DEPOSIT);
        transactionDto.setTransactionDate(new Date());

        TransactionHistory transaction = new TransactionHistory();
//        transaction.setId(1L);
        transaction.setMemberId(1L);
        transaction.setAmount(new BigDecimal(1000000));
        transaction.setTransactionType(TransactionTypeEnum.DEPOSIT);
        transaction.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-01"));
        transaction.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-01"));
        when(transactionHistoryService.createTransactionHistory(transactionDto)).thenReturn(refEq(transaction));

        transactionConsumer.consume(toJson(transactionDto));
    }


}

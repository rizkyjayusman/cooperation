package com.alami.cooperation.controller;

import com.alami.cooperation.controller.request.TransactionRequest;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.service.SavingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import static com.alami.cooperation.util.ResponseUtil.toJson;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SavingController.class)
public class SavingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SavingService savingService;

    @Test
    public void createSaving_shouldReturnHttp200_givenValidSaving() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1000000));
        transactionDto.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));
        given(savingService.createSavingTransaction(transactionDto)).willReturn(transactionDto);

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setMemberId(1L);
        transactionRequest.setAmount(new BigDecimal(1000000));
        transactionRequest.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));

        mockMvc.perform(post("/savings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(transactionRequest))
            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

        verify(savingService, times(1)).createSavingTransaction(refEq(transactionDto));
    }

    @Test
    public void createSavingWithoutMemberId_shouldReturnHttp400_givenFailureMemberNotFound() throws Exception {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(new BigDecimal(1000000));
        transactionRequest.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));

        mockMvc.perform(post("/savings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(transactionRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createSavingWithoutAmount_shouldReturnHttp400_givenFailureAmountNotFound() throws Exception {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setMemberId(1L);
        transactionRequest.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));

        mockMvc.perform(post("/savings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(transactionRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createSavingWithoutTransactionDate_shouldReturnHttp400_givenFailureTransactionNotFound() throws Exception {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setMemberId(1L);
        transactionRequest.setAmount(new BigDecimal(1000000));

        mockMvc.perform(post("/savings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(transactionRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createDebitTransaction_shouldReturnHttp200_givenValidDebit() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1000000));
        transactionDto.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));
        given(savingService.createDebitTransaction(transactionDto)).willReturn(transactionDto);

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setMemberId(1L);
        transactionRequest.setAmount(new BigDecimal(1000000));
        transactionRequest.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));

        mockMvc.perform(post("/savings/debit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(transactionRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(savingService, times(1)).createDebitTransaction(refEq(transactionDto));
    }

    @Test
    public void createDebitTransactionWithoutMemberId_shouldReturnHttp400_givenFailureMemberIdNotFound() throws Exception {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(new BigDecimal(1000000));
        transactionRequest.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));

        mockMvc.perform(post("/savings/debit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(transactionRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createDebitTransactionWithoutAmount_shouldReturnHttp400_givenFailureAmountNotFound() throws Exception {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setMemberId(1L);
        transactionRequest.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));

        mockMvc.perform(post("/savings/debit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(transactionRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createDebitTransactionWithoutTransactionDate_shouldReturnHttp400_givenFailureTransactionDateNotFound() throws Exception {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setMemberId(1L);
        transactionRequest.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));

        mockMvc.perform(post("/savings/debit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(transactionRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}

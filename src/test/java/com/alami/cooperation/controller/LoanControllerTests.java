package com.alami.cooperation.controller;

import com.alami.cooperation.controller.request.TransactionRequest;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.service.LoanService;
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

@WebMvcTest(LoanController.class)
public class LoanControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    @Test
    public void createLoan_shouldReturnHttp200_givenValidLoan() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1000000));
        transactionDto.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));
        given(loanService.createLoanTransaction(transactionDto)).willReturn(transactionDto);

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setMemberId(1L);
        transactionRequest.setAmount(new BigDecimal(1000000));
        transactionRequest.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));

        mockMvc.perform(post("/loans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(transactionRequest))
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(loanService, times(1)).createLoanTransaction(refEq(transactionDto));
    }

    @Test
    public void createPayLoanTransaction_shouldReturnHttp200_givenValidPayLoan() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1000000));
        transactionDto.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));
        given(loanService.createPayLoanTransaction(transactionDto)).willReturn(transactionDto);

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setMemberId(1L);
        transactionRequest.setAmount(new BigDecimal(1000000));
        transactionRequest.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));

        mockMvc.perform(post("/loans/pay")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(transactionRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(loanService, times(1)).createPayLoanTransaction(refEq(transactionDto));
    }


}

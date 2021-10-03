package com.alami.cooperation.controller;

import com.alami.cooperation.controller.request.TransactionRequest;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.service.RepaymentService;
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

@WebMvcTest(RepaymentController.class)
public class RepaymentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepaymentService repaymentService;

    @Test
    public void createRepaymentTransaction_shouldReturnHttp200_givenValidRepayment() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1000000));
        transactionDto.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));
        given(repaymentService.createRepaymentTransaction(transactionDto)).willReturn(transactionDto);

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setMemberId(1L);
        transactionRequest.setAmount(new BigDecimal(1000000));
        transactionRequest.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));

        mockMvc.perform(post("/repayments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(transactionRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(repaymentService, times(1)).createRepaymentTransaction(refEq(transactionDto));
    }

    @Test
    public void createRepaymentTransaction_shouldReturnHttp400_givenFailureMemberIdNotFound() throws Exception {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(new BigDecimal(1000000));
        transactionRequest.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));

        mockMvc.perform(post("/repayments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(transactionRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createRepaymentTransaction_shouldReturnHttp400_givenFailureAmountNotFound() throws Exception {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setMemberId(1L);
        transactionRequest.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));

        mockMvc.perform(post("/repayments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(transactionRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void createRepaymentTransaction_shouldReturnHttp400_givenFailureTransactionDateNotFound() throws Exception {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setMemberId(1L);
        transactionRequest.setAmount(new BigDecimal(1000000));

        mockMvc.perform(post("/repayments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(transactionRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}

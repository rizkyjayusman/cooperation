package com.alami.cooperation.controller;

import com.alami.cooperation.controller.request.TransactionRequest;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Deposit;
import com.alami.cooperation.service.DepositService;
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

import static com.alami.cooperation.util.ResponseUtil.toJson;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepositController.class)
public class DepositControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepositService depositService;

    private Deposit createDepositWawan() throws ParseException {
        Deposit deposit = new Deposit();
        deposit.setId(1L);
        deposit.setMemberId(1L);
        deposit.setBalance(new BigDecimal(1000000));
        deposit.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-01"));
        deposit.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-01"));

        return deposit;
    }

    private Deposit createDepositTeguh() throws ParseException {
        Deposit deposit = new Deposit();
        deposit.setId(2L);
        deposit.setMemberId(2L);
        deposit.setBalance(new BigDecimal(1250000));
        deposit.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-01"));
        deposit.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-01"));

        return deposit;
    }

    private Deposit createDepositJoko() throws ParseException {
        Deposit deposit = new Deposit();
        deposit.setId(3L);
        deposit.setMemberId(3L);
        deposit.setBalance(new BigDecimal(2000000));
        deposit.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-02"));
        deposit.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-02"));

        return deposit;
    }

    // TODO why depositService.getDepositList already mocked but the depositPage return null
    @Test
    public void getDepositList_shouldReturnHttp200_givenValidDepositList() throws Exception {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Deposit[] depositArr = new Deposit[] {createDepositWawan(), createDepositTeguh(), createDepositJoko()};
        List<Deposit> depositList = Arrays.asList(depositArr);
        Page<Deposit> depositPage = new PageImpl<>(depositList, pageRequest, depositList.size());

        given(depositService.getDepositList(pageRequest)).willReturn(depositPage);

        mockMvc.perform(get("/deposits?page=0&size=10")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(depositService, times(1)).getDepositList(pageRequest);
    }

    @Test
    public void createDeposit_shouldReturnHttp200_givenValidDeposit() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1000000));
        transactionDto.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));
        given(depositService.createDepositTransaction(transactionDto)).willReturn(transactionDto);

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setMemberId(1L);
        transactionRequest.setAmount(new BigDecimal(1000000));
        transactionRequest.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));

        mockMvc.perform(post("/deposits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(transactionRequest))
            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

        verify(depositService, times(1)).createDepositTransaction(refEq(transactionDto));
    }

    @Test
    public void createDepositWithoutMemberId_shouldReturnHttp400_givenFailureMemberNotFound() throws Exception {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(new BigDecimal(1000000));
        transactionRequest.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));

        mockMvc.perform(post("/deposits")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(transactionRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createDepositWithoutBalance_shouldReturnHttp400_givenFailureAmountNotFound() throws Exception {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setMemberId(1L);
        transactionRequest.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));

        mockMvc.perform(post("/deposits")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(transactionRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createDepositWithoutTransactionDate_shouldReturnHttp400_givenFailureTransactionNotFound() throws Exception {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setMemberId(1L);
        transactionRequest.setAmount(new BigDecimal(1000000));

        mockMvc.perform(post("/deposits")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(transactionRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}

package com.alami.cooperation.controller;

import com.alami.cooperation.controller.request.TransactionRequest;
import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Loan;
import com.alami.cooperation.service.LoanService;
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

@WebMvcTest(LoanController.class)
public class LoanControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    private Loan createLoanWawan() throws ParseException {
        Loan loan = new Loan();
        loan.setId(1L);
        loan.setMemberId(1L);
        loan.setAmount(new BigDecimal(1000000));
        loan.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-01"));
        loan.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-01"));

        return loan;
    }

    private Loan createLoanJoko() throws ParseException {
        Loan loan = new Loan();
        loan.setId(2L);
        loan.setMemberId(2L);
        loan.setAmount(new BigDecimal(1000000));
        loan.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-02"));
        loan.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-02"));

        return loan;
    }

    // TODO why loanService.getLoanList already mocked but the loanPage return null
//    @Test
    public void getLoanList_shouldReturnHttp200_givenValidLoanList() throws Exception {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Loan[] loan = new Loan[] {createLoanWawan(), createLoanJoko()};
        List<Loan> loanList = Arrays.asList(loan);
        Page<Loan> loanPage = new PageImpl<Loan>(loanList, pageRequest, loanList.size());

        given(loanService.getLoanList(pageRequest)).willReturn(loanPage);

        mockMvc.perform(get("/loans?page=0&size=10")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(loanService, times(1)).getLoanList(pageRequest);
    }

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
    public void createLoanTransaction_shouldReturnHttp400_givenFailureMemberNotFound() throws Exception {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(new BigDecimal(1000000));
        transactionRequest.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));
        mockMvc.perform(post("/loans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(transactionRequest))
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createLoanTransaction_shouldReturnHttp400_givenFailureAmountNotFound() throws Exception {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setMemberId(1L);
        transactionRequest.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10"));
        mockMvc.perform(post("/loans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(transactionRequest))
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createLoanTransaction_shouldReturnHttp400_givenFailureTransactionDateNotFound() throws Exception {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setMemberId(1L);
        transactionRequest.setAmount(new BigDecimal(1000000));
        mockMvc.perform(post("/loans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(transactionRequest))
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}

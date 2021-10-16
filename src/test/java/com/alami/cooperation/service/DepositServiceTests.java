package com.alami.cooperation.service;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Deposit;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.alami.cooperation.repository.DepositRepository;
import com.alami.cooperation.serviceImpl.DepositServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DepositServiceTests {

    private DepositServiceImpl depositService;

    private DepositBalanceControlService depositBalanceControlService;

    @Mock
    private DepositRepository depositRepository;

    @BeforeEach
    public void setUp() {
        depositService = new DepositServiceImpl(depositRepository);
    }

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

    @Test
    public void getDepositList_shouldReturnDepositList_givenValidDepositList() throws Exception {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Deposit[] mockDepositArr = new Deposit[] {createDepositWawan(), createDepositTeguh(), createDepositJoko()};
        List<Deposit> mockDepositList = Arrays.asList(mockDepositArr);
        Page<Deposit> mockDepositPage = new PageImpl<>(mockDepositList, pageRequest, mockDepositList.size());
        given(depositService.getDepositList(pageRequest)).willReturn(mockDepositPage);

        Page<Deposit> depositPage = depositService.getDepositList(pageRequest);
        assertThat(depositPage.getNumber()).isEqualTo(pageRequest.getPageNumber());
        assertThat(depositPage.getSize()).isEqualTo(pageRequest.getPageSize());
        assertThat(depositPage.getContent().size()).isEqualTo(mockDepositList.size());
        // TODO compare element each other
    }

    @Test
    public void addDepositBalanceWithBalanceZero_shouldReturnBalanceEqualTransactionAmount_givenValidBalance() {
        Deposit deposit = new Deposit();
        deposit.setId(1L);
        deposit.setMemberId(1L);
        deposit.setBalance(new BigDecimal(0));
        deposit.setCreatedDate(new Date());
        deposit.setUpdatedDate(new Date());

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1000000));
        transactionDto.setTransactionType(TransactionTypeEnum.DEPOSIT);
        transactionDto.setTransactionDate(new Date());
        depositBalanceControlService.addBalance(deposit, transactionDto);

        assertThat(new BigDecimal(1000000)).isEqualTo(deposit.getBalance());
    }

    @Test
    public void addBalanceWithBalanceOneMillion_shouldReturnBalanceEqualTwoMillion_givenValidBalance() {
        Deposit deposit = new Deposit();
        deposit.setId(1L);
        deposit.setMemberId(1L);
        deposit.setBalance(new BigDecimal(1000000));
        deposit.setCreatedDate(new Date());
        deposit.setUpdatedDate(new Date());

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1000000));
        transactionDto.setTransactionType(TransactionTypeEnum.DEPOSIT);
        transactionDto.setTransactionDate(new Date());
        depositBalanceControlService.addBalance(deposit, transactionDto);

        assertThat(new BigDecimal(2000000)).isEqualTo(deposit.getBalance());
    }

    @Test
    public void subtractBalanceWithBalanceOneMillion_shouldReturnBalanceEqualZero_givenValidBalance() {
        Deposit deposit = new Deposit();
        deposit.setId(1L);
        deposit.setMemberId(1L);
        deposit.setBalance(new BigDecimal(1000000));
        deposit.setCreatedDate(new Date());
        deposit.setUpdatedDate(new Date());

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1000000));
        transactionDto.setTransactionType(TransactionTypeEnum.DEPOSIT);
        transactionDto.setTransactionDate(new Date());
        depositBalanceControlService.subtractBalance(deposit, transactionDto);

        assertThat(new BigDecimal(0)).isEqualTo(deposit.getBalance());
    }

    @Test
    public void subtractBalanceWithBalanceOneMillion_shouldReturnBalanceEqualFiveHundredThousand_givenValidBalance() {
        Deposit deposit = new Deposit();
        deposit.setId(1L);
        deposit.setMemberId(1L);
        deposit.setBalance(new BigDecimal(1000000));
        deposit.setCreatedDate(new Date());
        deposit.setUpdatedDate(new Date());

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(500000));
        transactionDto.setTransactionType(TransactionTypeEnum.DEPOSIT);
        transactionDto.setTransactionDate(new Date());
        depositBalanceControlService.subtractBalance(deposit, transactionDto);

        assertThat(new BigDecimal(500000)).isEqualTo(deposit.getBalance());
    }

    @Test
    public void getTotalBalance_shouldReturnTotalBalance_givenValidTotalBalance() {
        given(depositRepository.getTotalBalance()).willReturn(new BigDecimal(1000000));
        BigDecimal totalBalance = depositService.getTotalDeposit();
        assertThat(totalBalance).isEqualTo(new BigDecimal(1000000));
    }

}

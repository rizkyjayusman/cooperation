package com.alami.cooperation.service;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.entity.Saving;
import com.alami.cooperation.enumtype.TransactionTypeEnum;
import com.alami.cooperation.repository.SavingRepository;
import com.alami.cooperation.serviceImpl.SavingServiceImpl;
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
public class SavingServiceTests {

    private SavingServiceImpl savingService;

    @Mock
    private SavingRepository savingRepository;

    @BeforeEach
    public void setUp() {
        savingService = new SavingServiceImpl(savingRepository);
    }

    private Saving createSavingWawan() throws ParseException {
        Saving saving = new Saving();
        saving.setId(1L);
        saving.setMemberId(1L);
        saving.setAmount(new BigDecimal(1000000));
        saving.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-01"));
        saving.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-01"));

        return saving;
    }

    private Saving createSavingTeguh() throws ParseException {
        Saving saving = new Saving();
        saving.setId(2L);
        saving.setMemberId(2L);
        saving.setAmount(new BigDecimal(1250000));
        saving.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-01"));
        saving.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-01"));

        return saving;
    }

    private Saving createSavingJoko() throws ParseException {
        Saving saving = new Saving();
        saving.setId(3L);
        saving.setMemberId(3L);
        saving.setAmount(new BigDecimal(2000000));
        saving.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-02"));
        saving.setUpdatedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-02"));

        return saving;
    }

    @Test
    public void getSavingList_shouldReturnSavingList_givenValidSavingList() throws Exception {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Saving[] mockSavingArr = new Saving[] {createSavingWawan(), createSavingTeguh(), createSavingJoko()};
        List<Saving> mockSavingList = Arrays.asList(mockSavingArr);
        Page<Saving> mockSavingPage = new PageImpl<>(mockSavingList, pageRequest, mockSavingList.size());
        given(savingService.getSavingList(pageRequest)).willReturn(mockSavingPage);

        Page<Saving> savingPage = savingService.getSavingList(pageRequest);
        assertThat(savingPage.getNumber()).isEqualTo(pageRequest.getPageNumber());
        assertThat(savingPage.getSize()).isEqualTo(pageRequest.getPageSize());
        assertThat(savingPage.getContent().size()).isEqualTo(mockSavingList.size());
        // TODO compare element each other
    }

    @Test
    public void addSavingAmountWithSavingAmountZero_shouldReturnSavingAmountEqualTransactionAmount_givenValidSavingAmount() {
        Saving saving = new Saving();
        saving.setId(1L);
        saving.setMemberId(1L);
        saving.setAmount(new BigDecimal(0));
        saving.setCreatedDate(new Date());
        saving.setUpdatedDate(new Date());

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1000000));
        transactionDto.setTransactionType(TransactionTypeEnum.SAVING);
        transactionDto.setTransactionDate(new Date());
        savingService.addSavingAmount(saving, transactionDto);

        assertThat(new BigDecimal(1000000)).isEqualTo(saving.getAmount());
    }

    @Test
    public void addSavingAmountWithSavingAmountOneMillion_shouldReturnSavingAmountEqualTwoMillion_givenValidSavingAmount() {
        Saving saving = new Saving();
        saving.setId(1L);
        saving.setMemberId(1L);
        saving.setAmount(new BigDecimal(1000000));
        saving.setCreatedDate(new Date());
        saving.setUpdatedDate(new Date());

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1000000));
        transactionDto.setTransactionType(TransactionTypeEnum.SAVING);
        transactionDto.setTransactionDate(new Date());
        savingService.addSavingAmount(saving, transactionDto);

        assertThat(new BigDecimal(2000000)).isEqualTo(saving.getAmount());
    }

    @Test
    public void subtractSavingAmountWithSavingAmountOneMillion_shouldReturnSavingAmountEqualZero_givenValidSavingAmount() {
        Saving saving = new Saving();
        saving.setId(1L);
        saving.setMemberId(1L);
        saving.setAmount(new BigDecimal(1000000));
        saving.setCreatedDate(new Date());
        saving.setUpdatedDate(new Date());

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(1000000));
        transactionDto.setTransactionType(TransactionTypeEnum.SAVING);
        transactionDto.setTransactionDate(new Date());
        savingService.subtractSavingAmount(saving, transactionDto);

        assertThat(new BigDecimal(0)).isEqualTo(saving.getAmount());
    }

    @Test
    public void subtractSavingAmountWithSavingAmountOneMillion_shouldReturnSavingAmountEqualFiveHundredThousand_givenValidSavingAmount() {
        Saving saving = new Saving();
        saving.setId(1L);
        saving.setMemberId(1L);
        saving.setAmount(new BigDecimal(1000000));
        saving.setCreatedDate(new Date());
        saving.setUpdatedDate(new Date());

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setMemberId(1L);
        transactionDto.setAmount(new BigDecimal(500000));
        transactionDto.setTransactionType(TransactionTypeEnum.SAVING);
        transactionDto.setTransactionDate(new Date());
        savingService.subtractSavingAmount(saving, transactionDto);

        assertThat(new BigDecimal(500000)).isEqualTo(saving.getAmount());
    }

    @Test
    public void getTotalSaving_shouldReturnTotalSaving_givenValidTotalSaving() {
        given(savingRepository.getTotalSaving()).willReturn(new BigDecimal(1000000));
        BigDecimal totalSaving = savingService.getTotalSaving();
        assertThat(totalSaving).isEqualTo(new BigDecimal(1000000));
    }

}

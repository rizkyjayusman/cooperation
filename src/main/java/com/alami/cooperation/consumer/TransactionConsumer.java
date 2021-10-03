package com.alami.cooperation.consumer;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.alami.cooperation.util.ResponseUtil.toJson;

@Slf4j
@Component
public class TransactionConsumer {

    private final TransactionService transactionService;

    public TransactionConsumer(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @KafkaListener(topics = "transaction_detail", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info("consume transaction dto from event: {}", message);
        try {
            ObjectMapper mapper = new ObjectMapper();
            TransactionDto transactionDto = mapper.readValue(message, TransactionDto.class);
            transactionService.createTransaction(transactionDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

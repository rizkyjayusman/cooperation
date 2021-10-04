package com.alami.cooperation.publisher;

import com.alami.cooperation.dto.TransactionDto;
import com.alami.cooperation.util.KafkaPublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.alami.cooperation.util.ResponseUtil.toJson;

@Slf4j
@Component
public class TransactionPublisher {

    @Autowired
    private KafkaPublisherService kafkaPublisherService;

    public void publish(TransactionDto transactionDto) {
        log.info("publish transaction dto into event: {}", toJson(transactionDto));
        kafkaPublisherService.sendMessage("transaction_detail", toJson(transactionDto));
    }
}

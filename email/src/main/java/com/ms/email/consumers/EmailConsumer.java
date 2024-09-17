package com.ms.email.consumers;

import com.ms.email.domain.dto.EmailDTO;
import com.ms.email.domain.models.EmailModel;
import com.ms.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailDTO dto) {
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(dto,emailModel);

        emailService.sendEmail(emailModel);
    }
}

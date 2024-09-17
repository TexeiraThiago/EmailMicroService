package com.ms.user.producers;

import com.ms.user.domain.dto.EmailDTO;
import com.ms.user.domain.dto.UserDTO;
import com.ms.user.domain.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routeKey;

    public void publishedMessageEmail(UserModel userModel) {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setUserId(userModel.getUserId());
        emailDTO.setEmailTo(userModel.getEmail());
        emailDTO.setSubject("Cadastro realizado com sucesso!"); //title
        emailDTO.setText(userModel.getName()+ " seja bem vindo(a)!\nAgradecemos a seu cadastro aproveite agora todos os recursos da nossa plataforma ");

        rabbitTemplate.convertAndSend("", routeKey, emailDTO);

    }
}

package com.algaworks.brewer.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.algaworks.brewer.model.Venda;

@Component
public class Mailer {
	
	@Autowired
	private JavaMailSender mailSender;

	@Async
	public void enviar(Venda venda) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("daniel.armando@live.com");
		message.setTo(venda.getCliente().getEmail());
		message.setSubject("Venda Efetuada");
		message.setText("Obrigado, sua venda foi processada");
		
		mailSender.send(message);
	}	
}

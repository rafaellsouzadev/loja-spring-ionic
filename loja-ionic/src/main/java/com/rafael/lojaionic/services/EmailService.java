package com.rafael.lojaionic.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.rafael.lojaionic.domain.Cliente;
import com.rafael.lojaionic.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendEmailHtml(MimeMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass);
}

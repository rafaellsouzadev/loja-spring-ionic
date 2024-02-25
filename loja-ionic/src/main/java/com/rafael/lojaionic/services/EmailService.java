package com.rafael.lojaionic.services;

import org.springframework.mail.SimpleMailMessage;

import com.rafael.lojaionic.domain.Pedido;

import jakarta.mail.internet.MimeMessage;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendEmailHtml(MimeMessage msg);
}

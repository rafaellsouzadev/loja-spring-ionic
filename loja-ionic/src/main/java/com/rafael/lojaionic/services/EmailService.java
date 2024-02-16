package com.rafael.lojaionic.services;

import org.springframework.mail.SimpleMailMessage;

import com.rafael.lojaionic.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
}

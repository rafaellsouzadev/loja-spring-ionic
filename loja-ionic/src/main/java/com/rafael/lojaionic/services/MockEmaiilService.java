package com.rafael.lojaionic.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import jakarta.mail.internet.MimeMessage;

public class MockEmaiilService extends AbstractEmailService {
	
	private static final Logger LOG = LoggerFactory.getLogger(MockEmaiilService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {
	
		LOG.info("Simulando envio de e-mail...");
		LOG.info(msg.toString());
		LOG.info("E-mail enviado");
	}

	@Override
	public void sendEmailHtml(MimeMessage msg) {
		LOG.info("Simulando envio de e-mail HTML...");
		LOG.info(msg.toString());
		LOG.info("E-mail enviado");
	}

}

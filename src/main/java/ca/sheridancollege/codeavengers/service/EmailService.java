package ca.sheridancollege.codeavengers.service;


	
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static javax.mail.Message.RecipientType.CC;
import static javax.mail.Message.RecipientType.TO;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;
import static ca.sheridancollege.codeavengers.constants.EmailConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.sheridancollege.codeavengers.repositories.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private UserRepository userRepository;
	public void sendNewPasswordEmail(String firstName, String password, String email) throws MessagingException {
		Message message = createEmail(firstName, password, email);
		SMTPTransport smtpTransport = (SMTPTransport) getEmailSession()
				.getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
		smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
		smtpTransport.sendMessage(message, message.getAllRecipients());
		smtpTransport.close();
	}

	private Message createEmail(String name, String password, String email) throws MessagingException {
		Message message = new MimeMessage(getEmailSession());
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(TO, InternetAddress.parse(email, false));
		message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
		message.setSubject(EMAIL_SUBJECT);
		message.setText("Hello " + name + ", \n \n Your new account password is: " + password
				+ "\n \n Online-BloodBank.ca");
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}

	// not finished
	public void sendDonationRequest(String username, String email)
			throws MessagingException {
		Message message = createRequest(username, email, FROM_EMAIL);
		SMTPTransport smtpTransport = (SMTPTransport) getEmailSession()
				.getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
		smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
		smtpTransport.sendMessage(message, message.getAllRecipients());
		smtpTransport.close();
	}

	// not finished
	private Message createRequest(String username, String email, String institutionName) throws MessagingException {
		Message message = new MimeMessage(getEmailSession());
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(TO, InternetAddress.parse(email, false));
		message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
		message.setSubject(EMAIL_SUBJECT);
		message.setText("Hello " + username + ", \n \n You received A donation Request From: " + institutionName
				+ "\n \n Online-BloodBank.ca");
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}

	private Message createEmergencyRequest(String username, String email, String institutionName) throws MessagingException {
		Message message = new MimeMessage(getEmailSession());
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(TO, InternetAddress.parse(email, false));
		message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
		message.setSubject(EMAIL_SUBJECT);
		message.setText("Hello " + username + ", \n \n You received An Urgent donation Request From: " + institutionName
				+ "\n \n Online-BloodBank.ca\n \n " +
				"If you are available and fit to make blood donation, please contact use at inititute_1@email.ca, or Call us at 1800080000\n\n"+
				"Thank you!");
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}

		public void sendEmergencyRequest()
			throws MessagingException {
		List<User> userList = userRepositoy.findAll();
		Message message = createRequest(username, email, FROM_EMAIL);
		SMTPTransport smtpTransport = (SMTPTransport) getEmailSession()
				.getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
		smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
		smtpTransport.sendMessage(message, message.getAllRecipients());
		smtpTransport.close();
	}
	private Session getEmailSession() {
		Properties properties = System.getProperties();
		properties.put(SMTP_HOST, GMAIL_SMTP_SERVER);
		properties.put(SMTP_AUTH, true);
		properties.put(SMTP_PORT, DEFAULT_PORT);
		properties.put(SMTP_STARTTLS_ENABLE, true);
		properties.put(SMTP_STARTTLS_REQUIRED, true);
		return Session.getInstance(properties, null);
	}
}
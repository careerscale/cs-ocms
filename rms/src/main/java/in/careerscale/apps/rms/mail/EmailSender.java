package in.careerscale.apps.ocms.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;



/**
 * refer for gmail authentication details.
 * http://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/
 * 
 * @author harinath
 * 
 */

@Service("emailService")
public class EmailSender {

	private static Log logger = LogFactory.getLog(EmailSender.class);
	private final String host;// = "smtp.gmail.com";
	private  final int SSLport;// = 465;
	private  final int smtpPort;// = 587;
	private  final String username;// = "webdemo2012@gmail.com";
	private  final String password;// = "webdemo2012@123";
	private  final String from;// = "webdemo2012@gmail.com";
	private String useSSL="true";
	
	
	public  EmailSender(String host, int sslPort, int smtpPort, String userName, String password, String from){
		this.host=host;
		this.SSLport=sslPort;
		this.smtpPort=smtpPort;
		this.username=userName;
		this.password=password;
		this.from=from;
		
		
	}
	
	
	
	public  EmailSender(String host, int sslPort, int smtpPort, String userName, String password, String from, String userSSL){
		this.host=host;
		this.SSLport=sslPort;
		this.smtpPort=smtpPort;
		this.username=userName;
		this.password=password;
		this.from=from;
		this.useSSL = useSSL;
		
		
	}
	
	
	//TODO update with OCMS/CAREERSCALE email id.

	/**
	 * Send mail with TLS enabled mode
	 * 
	 * @param template
	 * @param email
	 */
	public void sendEmail(String template, String email) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", useSSL);
		props.put("mail.smtp.port", smtpPort);
		Session session = Session.getInstance(props,new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
 
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"
					+ "\n\n No spam to my email, please!");

			Transport transport = session.getTransport("smtp");
     		transport.connect(host, smtpPort, username, password);

			Transport.send(message);

			logger.info("DONE sending email with TLS"); 
			

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * To send email with GMAIL account using SSL.
	 * @param template the template name to be used.
	 * @param email the email id to which the mail need to be sent.
	 */
	public void sendMailWithSSL(String subject, String body, String email) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", SSLport);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(from, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject(subject);
			message.setText(body);

			Transport transport = session.getTransport("smtp");
			transport.connect(host,SSLport,username, password);
			
			Transport.send(message);
			logger.info("DONE sending email with SSL"); 
			

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Main method, describes how to invoke the email sending methods.
	 * @param args
	 */
	public static void main(String[] args) {
		EmailSender sender = new EmailSender("smtp.gmail.com",465,587,"webdemo2012@gmail.com","webdemo2012@123","webdemo2012@gmail.com");
		//commented next lines to skip the erring code.
		//TODO when bandwidth is available fix it.
	/*	try {
			sender.sendEmail("dummy", "webdemo2012@gmail.com");
		} catch (Exception e) {
			logger.error("Error while sending email with TLS", e);
			
		}
	*/	try {
			sender.sendMailWithSSL("subject ","Message body", "webdemo2012@gmail.com");
		} catch (Exception e) {
			logger.error("Error while sending email with TLS", e);
		}  
	}
}

package delivery.management.controller.othersControllers;

import delivery.management.model.dto.Others.Mail;
import delivery.management.model.dto.Others.MailUser;
import delivery.management.services.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MailController {

	private MailService mailService;

	@GetMapping("/")
	public String mail() {
		return "home";
	}

	@GetMapping("/send")
	public String send() {
		MailUser user = new MailUser(1L, "Emmanuel", "emmadexboy4real@gmail.com");

		// Sending verification mail
		mailService.sendVerificationToken(UUID.randomUUID().toString(), user);

		// Sending password reset mail
		mailService.resetPasswordToken(UUID.randomUUID().toString(), user);


		// Sending contact mail
		Mail mail = new Mail();
		mail.setFromName(user.getDisplayName());
		mail.setFrom(user.getEmail());
		mail.setSubject("Spring Boot - Email with FreeMarker template");
		mail.setBody("Message body goes here");
		mailService.sendContactMail(mail);
		System.out.println("Done!");
		return "mail";
	}


}
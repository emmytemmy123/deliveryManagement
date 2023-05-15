package delivery.management.services.mail;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MailMessageService {

	@Resource
	private MessageSource messageSource;

	public String getMessage(String code) {

		return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
	}
}

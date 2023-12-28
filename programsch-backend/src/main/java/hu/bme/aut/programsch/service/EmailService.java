package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.domain.EmailType;
import hu.bme.aut.programsch.domain.Mail;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.ISpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Service
public class EmailService  {

    private final JavaMailSender mailSender;
    private final ISpringTemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender, ISpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Transactional
    public void sendMail(Mail message) throws MessagingException, UnsupportedEncodingException {
        MimeMessage emailMessage = mailSender.createMimeMessage();
        MimeMessageHelper mailBuilder = new MimeMessageHelper(emailMessage, true);

        mailBuilder.setFrom(message.getMailFrom(), "Program.sch");
        mailBuilder.setTo(message.getMailTo());
        mailBuilder.setSubject(message.getMailSubject());
        mailBuilder.setText(message.getMailContent(), true);
        mailSender.send(emailMessage);
    }

    @Transactional
    public Mail createMail(String to, EmailType emailType, Map<String, Object> variables) {
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(variables);

        StringWriter stringWriter = new StringWriter();
        templateEngine.process(emailType.getEmailTemplate(), thymeleafContext, stringWriter);

        Mail result = new Mail();
        result.setMailTo(to);
        result.setMailFrom("boros.gergo@hotmail.com");
        result.setMailContent(stringWriter.toString());
        result.setMailSubject(emailType.getEmailSubject());

        return result;
    }

}

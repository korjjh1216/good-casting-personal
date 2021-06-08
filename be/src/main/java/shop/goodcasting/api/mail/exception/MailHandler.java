package shop.goodcasting.api.mail.exception;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

public class MailHandler {

    private JavaMailSender sender;
    private MimeMessage message;
    private MimeMessageHelper helper;

    public MailHandler(JavaMailSender sender) throws MessagingException{
        message = sender.createMimeMessage();
        helper = new MimeMessageHelper(message, true, "UTF-8");
    }

    public void setFrom(String fromAddress) throws MessagingException{
        helper.setFrom(fromAddress);
    }

    public void setTo(String email) throws MessagingException{
        helper.setTo(email);
    }

    public void setSubject(String subject) throws MessagingException{
        helper.setSubject(subject);
    }

    public void setText(String text, Boolean useHtml) throws MessagingException{
        helper.setText(text, useHtml);
    }

    public void setAttach(String displayFileName, MultipartFile file) throws MessagingException {
        helper.addAttachment(displayFileName, file);
    }
    public void setInline(String contentId, MultipartFile file) throws MessagingException, IOException {
        helper.addInline(contentId, new ByteArrayResource(file.getBytes()), "image/jpeg");
    }

    public void send(){
        try{
            sender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

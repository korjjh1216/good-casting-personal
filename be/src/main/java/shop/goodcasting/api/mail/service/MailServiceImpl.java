package shop.goodcasting.api.mail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import shop.goodcasting.api.mail.domain.MailDTO;
import shop.goodcasting.api.mail.exception.MailHandler;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{

    private final JavaMailSender javaMailSender;
    private final String FROM_ADDRESS = "goodcastingoffical@gmail.com";

    public MailDTO txtMailSend(MailDTO mailDTO){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mailDTO.getAddress());
        msg.setSubject(mailDTO.getTitle());
        msg.setText(mailDTO.getMsg());
        javaMailSender.send(msg);
        return null;
    }

    public void FileMailSend(MailDTO mailDTO){
        try{
            String htmlContent = "<p>" + mailDTO.getMsg() + "님 <p> <img src = 'cid:파일경로'>";

            MailHandler mailHandler = new MailHandler(javaMailSender);
            mailHandler.setTo(mailDTO.getAddress());
            mailHandler.setSubject(mailDTO.getTitle());
            mailHandler.setText(htmlContent, true);
            mailHandler.setAttach(mailDTO.getFile().getOriginalFilename(), mailDTO.getFile());
            mailHandler.setInline("보내는 파일명", mailDTO.getFile());
            mailHandler.send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
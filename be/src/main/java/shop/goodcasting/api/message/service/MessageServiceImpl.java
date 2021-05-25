package shop.goodcasting.api.message.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop.goodcasting.api.message.domain.Message;
import shop.goodcasting.api.message.domain.MessageActionType;
import shop.goodcasting.api.message.domain.MessageDTO;
import shop.goodcasting.api.message.repository.MessageRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepo;


    @Override
    @Transactional
    public List<MessageDTO> findAllByReceiverId(Long receiverId){
        return messageRepo.findAllByReceiverId(receiverId).stream().map(message -> {
            System.out.println("enter loop");
            return entity2DtoAll(message);
        }).collect(Collectors.toList());

    }

    @Override
    public MessageDTO send(MessageDTO messageDTO) {
        Message message = dto2EntityAll(messageDTO);
        messageRepo.save(message);
        return null;
    }

    @Transactional
    public Long deleteById(Long messageId) {
        messageRepo.deleteById(messageId);
        return 1L;
    }

    @Override
    public List<MessageDTO> findByType(MessageActionType actionType) {
        List<Message> messages = messageRepo.findByActionType(actionType);

        List<MessageDTO> messageDTOList = new ArrayList<>();

        messages.forEach(message -> {
            messageDTOList.add(entity2DtoAll(message));
        });
        return messageDTOList;
    }

}

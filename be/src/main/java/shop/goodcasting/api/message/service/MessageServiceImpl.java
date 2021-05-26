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
import java.util.Optional;
import java.util.stream.Collectors;

@Log
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepo;

    @Transactional
    @Override
    public List<MessageDTO> findAllByReceiverId(Long receiver) {
        return messageRepo.findAllByReceiverId(receiver).stream().map(message -> {
            return entity2DtoAll(message);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<MessageDTO> findById(Long messageId){
        Message message = messageRepo.findById(messageId).get();
        MessageDTO messageDTO = entity2DtoAll(message);

        messageDTO.setReadMessage(true);

        Message msg = dto2EntityAll(messageDTO);
        messageRepo.save(msg);

        MessageDTO msgDto = entity2DtoAll(msg);
        return Optional.of(msgDto);
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

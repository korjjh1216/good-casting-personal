package shop.goodcasting.api.message.service;

import shop.goodcasting.api.message.domain.Message;
import shop.goodcasting.api.message.domain.MessageActionType;
import shop.goodcasting.api.message.domain.MessageDTO;
import shop.goodcasting.api.user.login.domain.UserDTO;
import shop.goodcasting.api.user.login.domain.UserVO;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    MessageDTO send(MessageDTO messageDTO);
    List<MessageDTO> findByType(MessageActionType actionType);
    Optional<MessageDTO> findById(Long messageId);
    List<MessageDTO> findAllByReceiverId(Long receiver);

    default Message dto2Entity(MessageDTO messageDTO){
        return Message.builder()
                .messageId(messageDTO.getMessageId())
                .readMessage(messageDTO.isReadMessage())
                .messageActionType(messageDTO.getMessageActionType())
                .build();
    }

    default Message dto2EntityAll(MessageDTO messageDTO){
        return Message.builder()
                .messageId(messageDTO.getMessageId())
                .readMessage(messageDTO.isReadMessage())
                .messageActionType(messageDTO.getMessageActionType())
                .sender(UserVO.builder()
                        .userId(messageDTO.getSender().getUserId())
                        .username(messageDTO.getSender().getUsername())
                        .build())
                .receiver(UserVO.builder()
                        .userId(messageDTO.getReceiver().getUserId())
                        .build())
                .build();
    }

    default MessageDTO entity2Dto(Message message){
        return MessageDTO.builder()
                .messageId(message.getMessageId())
                .readMessage(message.isReadMessage())
                .messageActionType(message.getMessageActionType())
                .build();
    }

    default MessageDTO entity2DtoAll(Message message){
        return MessageDTO.builder()
                .messageId(message.getMessageId())
                .readMessage(message.isReadMessage())
                .messageActionType(message.getMessageActionType())
                .sender(UserDTO.builder()
                        .userId(message.getSender().getUserId())
                        .username(message.getSender().getUsername())
                        .build())
                .receiver(UserDTO.builder()
                        .userId(message.getReceiver().getUserId())
                        .build())
                .build();
    }
}

package shop.goodcasting.api.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import shop.goodcasting.api.message.domain.Message;
import shop.goodcasting.api.message.domain.MessageActionType;
import shop.goodcasting.api.user.login.domain.UserVO;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select m.readMessage from Message m where m.readMessage = :readMessage")
    boolean findByUnread(@Param("readMessage") boolean readMessage);

    @Query("select m from Message m where m.messageActionType = :messageActionType")
    List<Message> findByActionType(@Param("messageActionType") MessageActionType messageActionType);

    @Query("select m from Message m where m.receiver.userId = :receiver")
    List<Message> findAllByReceiverId(@Param("receiver") Long receiverId);
}
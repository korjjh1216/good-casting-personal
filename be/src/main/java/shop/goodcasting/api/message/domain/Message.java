package shop.goodcasting.api.message.domain;

import lombok.*;
import shop.goodcasting.api.common.domain.BaseEntity;
import shop.goodcasting.api.user.login.domain.UserVO;


import javax.persistence.*;

@ToString(exclude = {"sender", "receiver"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name="messages")
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_sender")
    private UserVO sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_receiver")
    private UserVO receiver;

    @Column(name="action_type")
    private MessageActionType messageActionType;

    @Column(name="read_message")
    private boolean readMessage;
}

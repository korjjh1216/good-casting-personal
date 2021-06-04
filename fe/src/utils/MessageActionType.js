const userInfo = typeof window !== `undefined` ? JSON.parse(localStorage.getItem('USER')) : null;

const MessageActionType = {
    // CONTACT: `${userInfo[1].name}님이 지원한`,
    // PASS: `축하합니다 ${userInfo[1].name}님 :) \n ${userInfo[1].actorId}님의 ${userInfo[1].actorId} 역할에 합격했습니다.`,
    // SUBMISSION: `submission`,
    // READING: `${userInfo[1].actorId}님이 메시지를 확인했습니다.`,
    // SUPPORT: `${userInfo[1].name}님이 ${userInfo[1].actorId}님의 공고에 지원했습니다.`,
};

export default MessageActionType;

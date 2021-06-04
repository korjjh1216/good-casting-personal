const { default: axios } = require('axios');

const SERVER = 'http://localhost:8080';

const userInfo =
    typeof window !== `undefined`
        ? JSON.parse(localStorage.getItem('USER'))
        : null;

const messageList = () => {
    return axios({
        url: `${SERVER}/messages/list/${userInfo[1].actorId}`,
        method: 'get',
        headers: { Authorization: localStorage.getItem('TOKEN') },
    });
};

export default { messageList };

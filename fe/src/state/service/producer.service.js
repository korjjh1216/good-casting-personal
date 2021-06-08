const { default: axios } = require('axios');

const SERVER = 'http://localhost:8080';

const userInfo =
    typeof window !== `undefined`
        ? JSON.parse(localStorage.getItem('USER'))
        : null;

const getProducerInfo = () => {
    return axios({
        url: `${SERVER}/producers/myPage/${userInfo[1].producerId}`,
        method: 'get',
        headers: { Authorization: localStorage.getItem('TOKEN') },
    });
};

const updateProducerInfo = (arg) => {
    return axios({
        url: `${SERVER}/producers/info`,
        method: 'post',
        data: arg,
        headers: { Authorization: localStorage.getItem('TOKEN') },
    });
};

const unRegister = (arg) => {
    return axios({
        url: `${SERVER}/producers/delete`,
        method: 'delete',
        data: arg,
        headers: { Authorization: localStorage.getItem('TOKEN') },
    });
};

export default { getProducerInfo, updateProducerInfo, unRegister };

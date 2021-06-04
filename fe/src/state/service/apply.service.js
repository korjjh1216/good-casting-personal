const { default: axios } = require('axios');

const SERVER = 'http://localhost:8080';

const userInfo = typeof window !== `undefined` ? JSON.parse(localStorage.getItem('USER')) : null;

const hireApply = (apply) => {
    console.log('service hireApply : ' + JSON.stringify(apply));
    return axios({
        url: `${SERVER}/applies/doApply`,
        method: 'post',
        data: apply,
        headers: {
            Authorization: localStorage.getItem('TOKEN'),
        },
    });
};

const applicantist = (pageRequest) => {
    console.log('service applicantist pageRequest: ' + JSON.stringify(pageRequest));
    return axios({
        url: `${SERVER}/applies/list`,
        method: 'post',
        data: pageRequest,
        headers: { Authorization: localStorage.getItem('TOKEN') },
    });
};

export default { applicantist, hireApply };

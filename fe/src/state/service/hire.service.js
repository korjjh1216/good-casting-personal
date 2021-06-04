const { default: axios } = require('axios');

const SERVER = 'http://localhost:8080';

const userInfo = typeof window !== `undefined` ? JSON.parse(localStorage.getItem('USER')) : null;

const hireList = (pageRequest) => {
    console.log('service hireList pageRequest: ' + JSON.stringify(pageRequest));
    return axios({
        url: `${SERVER}/hires/list`,
        method: 'post',
        data: pageRequest,
        headers: { Authorization: localStorage.getItem('TOKEN') },
    });
};

const hireDetail = (id) => {
    return axios({
        url: `${SERVER}/hires/detail`,
        method: 'get',
        params: {
            hireId: id,
        },
        headers: { Authorization: 'JWT fefege..' },
    });
};

const hireDelete = (id) => {
    return axios({
        url: `${SERVER}/hires/delete`,
        method: 'delete',
        params: {
            hireId: id,
        },
        headers: { Authorization: 'JWT fefege..' },
    });
};

export default { hireList, hireDetail };

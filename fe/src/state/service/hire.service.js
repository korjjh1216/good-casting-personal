const { default: axios } = require('axios');

const SERVER = 'http://localhost:8080';

const userInfo = typeof window !== `undefined` ? JSON.parse(localStorage.getItem('USER')) : null;

const hireRegister = (arg) => {
    console.log('service hireRegister :' + JSON.stringify(arg));
    return axios({
        url: `${SERVER}/hires/register`,
        method: 'post',
        data: arg,
        headers: {
            // "Content-Type": "multipart/form-data",
            Authorization: localStorage.getItem('TOKEN'),
        },
    });
};

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
        url: `${SERVER}/hires/detail/${id}`,
        method: 'get',
        headers: { Authorization: 'JWT fefege..' },
    });
};

const hireDelete = (id) => {
    return axios({
        url: `${SERVER}/hires/delete/${id}`,
        method: 'delete',
        headers: { Authorization: 'JWT fefege..' },
    });
};

export default { hireRegister, hireList, hireDetail, hireDelete };

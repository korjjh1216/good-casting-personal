const { default: axios } = require('axios');

const SERVER = 'http://localhost:8080';

const hireList = (pageRequest) => {
    return axios({
        url: `${SERVER}/hires/list`,
        method: 'post',
        data: pageRequest,
        headers: { Authorization: 'JWT fefege..' },
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
        Authorization: localStorage.getItem('TOKEN'),
    });
};

const hireRegister = (arg) => {
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
export default { hireList, hireDetail, hireDelete, hireRegister };

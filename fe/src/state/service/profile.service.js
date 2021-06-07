const { default: axios } = require('axios');

const SERVER = 'http://localhost:8080';

const userInfo = typeof window !== `undefined` ? JSON.parse(localStorage.getItem('USER')) : null;

const profileRegister = (arg) => {
    return axios({
        url: `${SERVER}/profiles/register`,
        method: 'post',
        data: arg,
        headers: {
            // 'Content-Type': 'multipart/form-data',
            Authorization: localStorage.getItem('TOKEN'),
        },
    });
};

const profileList = (pageRequest) => {
    console.log('service profileList pageRequest: ' + JSON.stringify(pageRequest));
    return axios({
        url: `${SERVER}/profiles/list`,
        method: 'post',
        data: pageRequest,
        headers: { Authorization: 'JWT fefege..' },
    });
};
const profileDetail = (id) => {
    return axios({
        url: `${SERVER}/profiles/detail/${id}`,
        method: 'get',
        headers: { Authorization: 'JWT fefege..' },
    });
};

const profileDelete = (id) => {
    return axios({
        url: `${SERVER}/profiles/delete/${id}`,
        method: 'delete',
        headers: { Authorization: 'JWT fefege..' },
    });
};

const delcheck = (id) => {
    return axios({
        url: `${SERVER}/profiles/delcheck/${id}`,
        method: 'post',
        headers: { Authorization: 'JWT fefege..' },
    });
};
export default { profileRegister, profileList, profileDetail, profileDelete, delcheck };

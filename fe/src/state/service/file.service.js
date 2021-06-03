const { default: axios } = require('axios');

const SERVER = 'http://localhost:8080';

const userInfo = typeof window !== `undefined` ? JSON.parse(localStorage.getItem('USER')) : null;

const fileRegister = (formData) => {
    return axios({
        url: `${SERVER}/files/register`,
        method: 'post',
        data: formData,
        headers: {
            Authorization: localStorage.getItem('TOKEN'),
            'Content-Type': 'multipart/form-data',
        },
    });
};

export default { fileRegister };

const { default: axios } = require('axios');

const SERVER = 'http://localhost:8080';

const signup = (arg) => {
    return axios({
        url: `${SERVER}/users/signup`,
        method: 'post',
        data: arg,
        headers: { Authorization: 'JWT fefege..' },
    });
};

const signin = (arg) => {
    return axios({
        url: `${SERVER}/users/signin`,
        method: 'post',
        data: arg,
        headers: { Authorization: 'JWT fefege..' },
    });
};

const update = (user) => {
    return axios({
        url: `${SERVER}/users/update`,
        method: 'post',
        data: user,
        headers: { Authorization: 'JWT fefege..' },
    });
};

export default { signup, signin, update };

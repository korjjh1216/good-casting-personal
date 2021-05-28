const { default: axios } = require('axios');

const SERVER = 'http://localhost:8080';

const signup = (arg) => {
    console.log('service signup arg: ' + JSON.stringify(arg));
    return axios({
        url: `${SERVER}/users/signup`,
        method: 'post',
        data: arg,
        headers: { Authorization: 'JWT fefege..' },
    });
};

const signin = (arg) => {
    console.log('service signin arg: ' + JSON.stringify(arg));
    return axios({
        url: `${SERVER}/users/signin`,
        method: 'post',
        data: arg,
        headers: { Authorization: 'JWT fefege..' },
    });
};

export default { signup, signin };

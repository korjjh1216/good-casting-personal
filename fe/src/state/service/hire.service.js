const { default: axios } = require('axios');

const SERVER = 'http://localhost:8080';

const hireList = (pageRequest) => {
    console.log('service hireList pageRequest: ' + JSON.stringify(pageRequest));
    return axios({
        url: `${SERVER}/hires/list`,
        method: 'post',
        data: {
            page: pageRequest.page,
            size: pageRequest.size,
            sort: pageRequest.sort,
        },
        headers: { Authorization: 'JWT fefege..' },
    });
};

export default { hireList };

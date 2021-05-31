const { default: axios } = require('axios')

const SERVER = 'http://localhost:8080'

const userInfo = typeof window !== `undefined` ? JSON.parse(localStorage.getItem('USER')) : null

const profileRegister = (arg) => {
    console.log('service hireList pageRequest: ' + JSON.stringify(arg))
    return axios({
        url: `${SERVER}/profiles/register`,
        method: 'post',
        data: arg,
        headers: {
            'Content-Type': 'multipart/form-data',
            Authorization: localStorage.getItem('TOKEN'),
        },
    })
}

const profileList = (pageRequest) => {
    console.log('service profileList pageRequest: ' + JSON.stringify(pageRequest))
    return axios({
        url: `${SERVER}/profiles/list`,
        method: 'post',
        data: {
            page: pageRequest.page,
            size: pageRequest.size,
            sort: pageRequest.sort,
        },

        headers: { Authorization: localStorage.getItem('TOKEN') },
    })
}

const profileRead = () => {
    return axios({
        url: `${SERVER}/profiles/detail/${userInfo[1].profileId}`,
        method: 'get',
        headers: { Authorization: localStorage.getItem('TOKEN') },
    })
}
export default { profileRegister, profileList, profileRead }

const { default: axios } = require('axios')

const SERVER = 'http://localhost:8080'

const userInfo = typeof window !== `undefined` ? JSON.parse(localStorage.getItem('USER')) : null

const profileRegister = (arg) => {
    console.log('service profileList pageRequest: ' + JSON.stringify(arg))
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
const fileRegister = (formData) => {
    return axios({
        url: `${SERVER}/files/register`,
        method: 'post',
        data: formData,
        headers: {
            Authorization: 'JWT fefege..',
            'Content-Type': 'multipart/form-data',
        },
    })
}

const profileList = (pageRequest) => {
    console.log('service profileList pageRequest: ' + JSON.stringify(pageRequest))
    return axios({
        url: `${SERVER}/profiles/list`,
        method: 'post',
        data: pageRequest,
        headers: { Authorization: 'JWT fefege..' },
    })
}

const profileRead = () => {
    return axios({
        url: `${SERVER}/profiles/detail/${userInfo[1].profileId}`,
        method: 'get',
        headers: { Authorization: localStorage.getItem('TOKEN') },
    })
}
export default { profileRegister, fileRegister, profileList, profileRead }

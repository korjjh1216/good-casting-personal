const { default: axios } = require('axios')

const SERVER = 'http://localhost:8080'

const userInfo = typeof window !== `undefined` ? JSON.parse(localStorage.getItem('USER')) : null

const getActorInfo = () => {
    return axios({
        url: `${SERVER}/actors/myPage/${userInfo[1].actorId}`,
        method: 'get',
        headers: { Authorization: 'JWT fefege..' },
    })
}

const updateactorInfo = (arg) => {
    console.log('service actorInfo arg: ' + JSON.stringify(arg))
    return axios({
        url: `${SERVER}/actors/info`,
        method: 'post',
        data: arg,
        headers: { Authorization: localStorage.getItem('TOKEN') },
    })
}

const unRegister = (arg) => {
    console.log(arg)
    return axios({
        url: `${SERVER}/actors/delete/${userInfo[1].actorId}`,
        method: 'delete',
        data: arg,
        headers: { Authorization: localStorage.getItem('TOKEN') },
    })
}

export default { getActorInfo, updateactorInfo, unRegister }

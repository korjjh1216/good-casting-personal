const { default: axios } = require('axios')

const SERVER = 'http://localhost:8080'

const userInfo = typeof window !== `undefined` ? JSON.parse(localStorage.getItem('USER')) : null

const getProducerInfo = () => {
    return axios({
        url: `${SERVER}/producers/myPage/${userInfo[1].producerId}`,
        method: 'get',
        headers: { Authorization: 'JWT fefege..' },
    })
}

const updateProducerInfo = (arg) => {
    console.log('service ProducerInfo arg: ' + JSON.stringify(arg))
    return axios({
        url: `${SERVER}/producers/info`,
        method: 'post',
        data: arg,
        headers: { Authorization: localStorage.getItem('TOKEN') },
    })
}

export default { getProducerInfo, updateProducerInfo }

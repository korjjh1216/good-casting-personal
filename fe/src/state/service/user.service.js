const { default: axios } = require("axios");

const signup = (arg) => {
    console.log("service signup arg: " + JSON.stringify(arg))
    return axios({
        url: "",
        method: "post",
        data: arg,
        headers: {'Authorization': 'JWT fefege..'}
    })
}

const signin = (arg) => {
    console.log("service signin arg: " + JSON.stringify(arg))
    return axios({
        url: "",
        method: "post",
        data: arg,
        headers: {'Authorization': 'JWT fefege..'}
    })
}


export default { signup, signin }
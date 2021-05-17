import { userService } from "../service/index"
const { createSlice, createAsyncThunk } = require("@reduxjs/toolkit");

export const signup = createAsyncThunk("SIGN_UP", async (arg) => {
    console.log("reducer signup() arg: " + JSON.stringify(arg))
    const response = await userService.signup(arg)

    return response.data
})

export const signin = createAsyncThunk("SIGN_IN", async (arg) => {
    console.log("reducer signin() arg: " + JSON.stringify(arg))
    const response = await userService.signin(arg)

    return response.data
})

const userSlice = createSlice({
    name: "user",
    initialState: {
        actor: {},
        producer: {},
    },
    reducers: {

    },
    extraReducers: (builder) => {
        builder
            .addCase(signup.fulfilled, (state, { payload }) => {
                console.log("회원가입() payload" + JSON.stringify(payload))
            })
            .addCase(signin.fulfilled, (state, { payload }) => {
                console.log("로그인() payload: " + JSON.stringify(payload))
                return {
                    ...state.actor,
                    payload
                }
            })

    }
})


export default userSlice.reducer
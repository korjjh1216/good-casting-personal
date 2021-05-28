import { userService } from '../service/index'
const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const signup = createAsyncThunk('SIGN_UP', async (arg) => {
    console.log('reducer signup() arg: ' + JSON.stringify(arg))
    const response = await userService.signup(arg)
    return response.data
})

export const signin = createAsyncThunk('SIGN_IN', async (arg) => {
    console.log('reducer signin() arg: ' + JSON.stringify(arg))
    const response = await userService.signin(arg)

    if (response.data[0].token === 'Wrong password') {
        alert('비밀번호를 다시 입력해주세요')
    } else {
        localStorage.setItem('token', 'Bearer ' + response.data[0].token)
        localStorage.setItem('USER', JSON.stringify(response.data))
        return response.data
    }
})

const userSlice = createSlice({
    name: 'user',
    initialState: {
        actor: {},
        producer: {},
        loggedIn: false,
    },
    reducers: {
        isUserLoggendIn(state, { payload }) {
            state.loggedIn = !state.loggedIn
            payload = state.loggedIn
        },
    },
    extraReducers: (builder) => {
        builder
            .addCase(signup.fulfilled, (state, { payload }) => {
                console.log('회원가입() payload' + JSON.stringify(payload))
            })
            .addCase(signin.fulfilled, (state, { payload }) => {
                console.log('로그인() payload: ' + JSON.stringify(payload))
            })
    },
})
export const userSelctor = (state) => state.userReducer

export const { isUserLoggendIn } = userSlice.actions
export default userSlice.reducer

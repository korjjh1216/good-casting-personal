import { userService } from '../service/index'
import Swal from 'sweetalert2'

const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

const sweetalert = (icon, title, text, footer) => {
    Swal.fire({
        icon: icon,
        title: title,
        text: text,
        footer: footer,
    })
}

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
        localStorage.setItem('TOKEN', 'Bearer ' + response.data[0].token)
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
                Swal.fire({
                    icon: 'success',
                    title: '회원가입이 완료되었습니다!',
                })
            })
            .addCase(signup.rejected, (state, { payload }) => {
                console.log('payload: ' + JSON.stringify(payload))
                if (payload.message.includes('중복된') || null) {
                    Swal.fire({
                        icon: 'error',
                        title: '중복된 아이디입니다!',
                        text: '다른 아이디를 입력해주세요',
                        footer: '<a href>Why do I have this issue?</a>',
                    })
                } else if (payload.message.includes('Validation') || null) {
                    Swal.fire({
                        icon: 'error',
                        title: '유효하지 않은 정보입니다.',
                        text: '다른 정보를 입력해주세요',
                        footer: '<a href>Why do I have this issue?</a>',
                    })
                } else {
                    alert('다른 에러')
                }
            })
            .addCase(signin.fulfilled, (state, { payload }) => {
                console.log('로그인() payload: ' + JSON.stringify(payload))
                localStorage.setItem('USER', JSON.stringify(payload))
            })
    },
})
export const userSelector = (state) => state.userReducer

export const { isUserLoggendIn } = userSlice.actions
export default userSlice.reducer

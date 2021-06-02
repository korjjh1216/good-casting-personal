import profileService from '../service/profile.service'

const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const profileRegister = createAsyncThunk('PROFILE_REGISTER', async (arg) => {
    console.log(arg)
    const response = await profileService.profileRegister()
    return response.data
})

export const myProfileList = createAsyncThunk('MYPROFILE_LIST', async (pageRequest) => {
    console.log('reducer myProfileList() pageRequest: ' + JSON.stringify(pageRequest))
    const response = await profileService.profileList(pageRequest)

    return response.data
})

export const fileRegister = createAsyncThunk('FILE_REGISTER', async (arg) => {
    console.log(arg)
    const response = await profileService.fileRegister(arg)
    return response.data
})

export const profileList = createAsyncThunk('PROFILE_LIST', async (pageRequest) => {
    console.log('reducer profileList() pageRequest: ' + JSON.stringify(pageRequest))
    const response = await profileService.profileList(pageRequest)

    return response.data
})

export const profileRead = createAsyncThunk('PROFILE_DETAIL', async () => {
    const response = await profileService.profileRead()
    return response.data
})

const profileSlice = createSlice({
    name: 'profile',
    initialState: {
        pageRequest: {
            page: 1,
            size: 10,
            type: '',
            sort: 'profileId',
            searchCond: {
                afrom: 0,
                ato: 0,
                rKeyword: '',
                gKeyword: '',
                wfrom: 0,
                wto: 0,
                hfrom: 0,
                hto: 0,
            },
            file: {
                fileName: '',
                uuid: '',
            },
        },
        pageResult: {
            pageList: [],
            dtoList: [],
            page: 1,
            size: 10,
            totalPage: 0,
            start: 0,
            end: 0,
            prev: false,
            next: false,
            totalElement: 0,
        },
    },
    reducers: {
        addCareer(state, { payload }) {
            state.careerList.push({
                year: payload.year,
                gerne: payload.gerne,
                title: payload.title,
                contents: payload.contents,
            })
        },
        deleteCareer(state, { payload }) {
            console.log('삭제')
            console.log(payload)
            state.careerList = state.careerList.filter((career) => career.id !== payload)
        },
    },
    extraReducers: (builder) => {
        builder
            .addCase(profileRegister.fulfilled, (state, { payload }) => {
                console.log(JSON.stringify(payload))
            })
            .addCase(profileList.fulfilled, (state, { payload }) => {
                console.log('payload :' + JSON.stringify(payload))

                return {
                    ...state,
                    pageResult: { ...payload },
                }
            })
            .addCase(profileRead.fulfilled, (state, { payload }) => {
                console.log('payload : ' + JSON.stringify(payload))
                state.profile = payload
            })
            .addCase(fileRegister.fulfilled, (state, { payload }) => {
                console.log('payload : ' + JSON.stringify(payload))
                state.fileList = payload
            })
    },
})

export const profileSelector = (state) => state.profileReducer

export const { addCareer, deleteCareer } = profileSlice.actions

export default profileSlice.reducer

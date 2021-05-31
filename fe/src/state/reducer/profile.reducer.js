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
        profile: [],
        careerList: [],
        profileList: [],
        pageRequest: {
            page: 1,
            size: 10,
            type: '',
            sort: 'profileId',
            ffrom: 0,
            fto: 0,
            conKeyword: '',
            castKeyword: '',
            gfrom: 0,
            gto: 0,
            tkeyword: 0,
            pkeyword: 0,
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
                //state.profileList.push(...payload)
                //     state.page = payload.page
                //     state.pageList = payload.pageList
                //     state.size = payload.size
                //     state.totalPage = payload.totalPage
                // })
                if (!payload) {
                    state.page = 1
                    return state
                }

                return {
                    ...state,
                    pageResult: { ...payload },
                }
            })
            .addCase(profileRead.fulfilled, (state, { payload }) => {
                console.log('payload : ' + JSON.stringify(payload))
                state.profile = payload
            })
    },
})

export const profileSelector = (state) => state.profileReducer

export const { addCareer, deleteCareer } = profileSlice.actions

export default profileSlice.reducer

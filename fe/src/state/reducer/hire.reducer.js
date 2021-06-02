import hireService from '../service/hire.service'

const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const hireList = createAsyncThunk('HIRE_LIST', async (pageRequest) => {
    console.log('createAsyncThunk enter: ' + JSON.stringify(pageRequest))
    if (pageRequest.page === 0) {
        return null
    }
    const response = await hireService.hireList(pageRequest)
    return response.data
})

export const hireDetail = createAsyncThunk('HIRE_DETAIL', async (id) => {
    console.log('createAsyncThunk enter: ' + JSON.stringify(id))

    const response = await hireService.hireDetail(id)

    console.log('hireDetail: ' + response.data)

    return response.data
})

const hireSlice = createSlice({
    name: 'hire',
    initialState: {
        pageRequest: {
            page: 1,
            size: 10,
            type: '',
            sort: 'hireId',
            searchCond: {
                ffrom: 0,
                fto: 0,
                conKeyword: '',
                castKeyword: '',
                gfrom: 0,
                gto: 0,
                tkeyword: 0,
                pkeyword: 0,
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
        hireDetail: {},
    },
    reducers: {
        pageListChange: (state, { payload }) => {
            state.pageResult.page = payload
        },
        setFfrom: ({ pageRequest }, { payload }) => {
            console.log('before ffrom: ' + pageRequest.ffrom)
            pageRequest.searchCond.ffrom = payload
            console.log('after ffrom' + pageRequest.ffrom)
        },
        setFto: ({ pageRequest }, { payload }) => {
            console.log('before fto: ' + pageRequest.fto)
            pageRequest.searchCond.fto = payload
            console.log('after fto: ' + pageRequest.fto)
        },
        setKeywords: ({ pageRequest }, { payload }) => {
            console.log('keyword: ' + payload)

            pageRequest.searchCond.castKeyword = payload
            pageRequest.searchCond.conKeyword = payload
            pageRequest.searchCond.pkeyword = payload
            pageRequest.searchCond.tkeyword = payload
        },
        setType: ({ pageRequest }, { payload }) => {
            pageRequest.searchCond.type += payload
        },
        setGfrom: ({ pageRequest }, { payload }) => {
            console.log('guarantee from: ' + payload)
            pageRequest.searchCond.gfrom = payload
        },
        setGto: ({ pageRequest }, { payload }) => {
            console.log('guarantee to: ' + payload)
            pageRequest.searchCond.gto = payload
        },
        resetSearchCondition: ({ pageRequest }) => {
            pageRequest.searchCond = {}
        },
    },
    extraReducers: (builder) => {
        builder
            .addCase(hireList.fulfilled, (state, { payload }) => {
                console.log('payload: ' + JSON.stringify(payload))

                if (!payload) {
                    state.page = 1
                    return state
                }

                return {
                    ...state,
                    pageResult: { ...payload },
                }
            })
            .addCase(hireDetail.fulfilled, (state, { payload }) => {
                return {
                    ...state,
                    hireDetail: { ...payload },
                }
            })
    },
})

export const hireSelector = (state) => state.hireReducer

export const { pageListChange, setFfrom, setFto, setKeywords, setType, setGfrom, setGto, resetSearchCondition } = hireSlice.actions
export default hireSlice.reducer

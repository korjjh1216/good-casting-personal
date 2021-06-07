import applyService from '../service/apply.service';
const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit');

export const applicantList = createAsyncThunk('APPLICANTLIST', async (pageRequest) => {
    console.log('reducer applicantList() pageRequest: ' + JSON.stringify(pageRequest));
    const response = await applyService.applicantist(pageRequest);

    return response.data;
});

export const applylist = createAsyncThunk('APPLYTLIST', async (pageRequest) => {
    console.log('reducer appllyList() pageRequest: ' + JSON.stringify(pageRequest));
    const response = await applyService.applylist(pageRequest);

    return response.data;
});

export const hireApply = createAsyncThunk('HIRE_APPLY', async (apply) => {
    console.log('createAsyncThunk enter : ' + JSON.stringify(apply));
    const response = await applyService.hireApply(apply);
    return response.data;
});

export const deleteApply = createAsyncThunk('DELETE_APPLY', async (id) => {
    console.log('DELETE_APPLY: ' + JSON.stringify(id));
    const response = await applyService.applyDelete(id);
    console.log('deleteApply: ' + response.data);
    return response.data;
});

const applySlice = createSlice({
    name: 'apply',
    initialState: {
        pageRequest: {
            page: 1,
            size: 10,
            type: '',
            sort: 'applyId',
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
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(hireApply.fulfilled, (state, { payload }) => {
                console.log('payload : ' + JSON.stringify(payload));
            })
            .addCase(applicantList.fulfilled, (state, { payload }) => {
                console.log(JSON.stringify(payload));
                return {
                    ...state,
                    pageResult: { ...payload },
                };
            })
            .addCase(applylist.fulfilled, (state, { payload }) => {
                console.log(JSON.stringify(payload));
                return {
                    ...state,
                    pageResult: { ...payload },
                };
            });
    },
});

export const applySelector = (state) => state.applyReducer;

export const {} = applySlice.actions;

export default applySlice.reducer;

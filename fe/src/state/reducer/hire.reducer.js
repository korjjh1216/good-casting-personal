import hireService from '../service/hire.service';

const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit');

export const hireList = createAsyncThunk('HIRE_LIST', async (pageRequest) => {
    console.log('createAsyncThunk enter: ' + JSON.stringify(pageRequest));
    const response = await hireService.hireList(pageRequest);
    return response.data;
});

const hireSlice = createSlice({
    name: 'hire',
    initialState: {
        hireList: [],
        page: 0,
        pageList: [],
    },
    reducers: {},
    extraReducers: (builder) => {
        builder.addCase(hireList.fulfilled, (state, { payload }) => {
            console.log('payload: ' + JSON.stringify(payload));
            // state.pageResult = payload=
            state.hireList = payload.dtoList;
            state.page = payload.page;
            state.pageList = payload.pageList;
        });
    },
});

export const hireSelector = (state) => state.hireReducer;

export const {} = hireSlice.actions;
export default hireSlice.reducer;

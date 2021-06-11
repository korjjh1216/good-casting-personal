import hireService from '../service/hire.service';
import Swal from 'sweetalert2';

const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit');

export const hireList = createAsyncThunk('HIRE_LIST', async (pageRequest) => {
    if (pageRequest.page === 0) {
        return null;
    }
    const response = await hireService.hireList(pageRequest);
    return response.data;
});

export const hireDetail = createAsyncThunk('HIRE_DETAIL', async (id) => {
    const response = await hireService.hireDetail(id);
    return response.data;
});

export const hireRegister = createAsyncThunk('HIRE_REGISTER', async (arg) => {
    const response = await hireService.hireRegister(arg);
    return response.data;
});

export const hireDelete = createAsyncThunk('HIRE_DELETE', async (id) => {
    const response = await hireService.hireDelete(id);
    return response.data;
});

export const hireUpdate = createAsyncThunk('HIRE_UPDATE', async (arg) => {
    console.log(arg);
    const response = await hireService.hireUpdate(arg);

    console.log(response.data);
    return response.data;
});

const initialState = {
    pageRequest: {
        page: 1,
        size: 15,
        sort: 'hireId',
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
    reset: false,
    hire: {
        deadline: '',
        producer: {},
        files: [],
    },
    status: '',
};

const hireSlice = createSlice({
    name: 'hire',
    initialState: initialState,
    reducers: {
        pageListChange: (state, { payload }) => {
            state.pageResult.page = payload;
        },
        resetHireSelector: (state = initialState) => {
            return {
                ...initialState,
                reset: !state.reset,
            };
        },
        resetStatus: (state) => {
            state.status = '';
        },
    },
    extraReducers: (builder) => {
        builder
            .addCase(hireList.fulfilled, (state, { payload }) => {
                if (!payload) {
                    state.page = 1;
                    return state;
                }

                return {
                    ...state,
                    pageResult: payload,
                    pageRequest: payload.pageRequest,
                };
            })
            .addCase(hireRegister.fulfilled, (state, { payload }) => {
                state.hire = payload;
                state.status = 'success';
                Swal.fire({
                    icon: 'success',
                    title: '공고문이 등록되었습니다.',
                });
            })
            .addCase(hireDetail.fulfilled, (state, { payload }) => {
                return {
                    ...state,
                    hire: payload,
                };
            })
            .addCase(hireDelete.fulfilled, (state) => {
                state.status = 'success';
                Swal.fire({
                    icon: 'success',
                    title: '공고문이 삭제되었습니다.',
                });
            })
            .addCase(hireUpdate.fulfilled, (state, { payload }) => {
                Swal.fire({
                    icon: 'success',
                    title: '공고문이 수정되었습니다.',
                });
            });
    },
});

export const hireSelector = (state) => state.hireReducer;

export const {
    pageListChange,
    resetHireSelector,
    resetStatus,
} = hireSlice.actions;
export default hireSlice.reducer;

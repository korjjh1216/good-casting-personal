import { navigate } from 'gatsby-link';
import Swal from 'sweetalert2';
import applyService from '../service/apply.service';
const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit');

export const applicantList = createAsyncThunk(
    'APPLICANTLIST',
    async (pageRequest) => {
        const response = await applyService.applicantist(pageRequest);
        return response.data;
    }
);

export const hireApply = createAsyncThunk(
    'HIRE_APPLY',
    async (apply, { rejectWithValue }) => {
        try {
            const response = await applyService.hireApply(apply);
            return response.data;
        } catch (e) {
            return rejectWithValue(e.response.data);
        }
    }
);

export const rejectApplicant = createAsyncThunk(
    'REJECT_APPLICANT',
    async (id) => {
        const response = await applyService.rejectApplicant(id);
        return response.data;
    }
);

export const applylist = createAsyncThunk('APPLYTLIST', async (pageRequest) => {
    const response = await applyService.applylist(pageRequest);
    return response.data;
});

export const deleteApply = createAsyncThunk('DELETE_APPLY', async (id) => {
    const response = await applyService.applyDelete(id);
    return response.data;
});

const initialState = {
    pageRequest: {
        page: 1,
        size: 10,
        sort: 'applyId',
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
    status: '',
};

const applySlice = createSlice({
    name: 'apply',
    initialState,
    reducers: {
        resetStatus: (state) => {
            state.status = '';
        },
    },
    extraReducers: (builder) => {
        builder
            .addCase(hireApply.fulfilled, (state, { payload }) => {
                state.status = 'success';
                Swal.fire({
                    icon: 'success',
                    title: '지원 되었습니다.',
                });
            })
            .addCase(hireApply.rejected, (state, { payload }) => {
                state.status = 'reject';
                if (payload.message.includes('duplicate') || null) {
                    Swal.fire({
                        icon: 'error',
                        title: '이미 지원된 프로필입니다.',
                    });
                }
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
            })
            .addCase(rejectApplicant.fulfilled, (state) => {
                state.status = 'reject';
                Swal.fire({
                    icon: 'success',
                    title: '불합격 처리되었습니다.',
                });
            })
            .addCase(deleteApply.fulfilled, (state) => {
                Swal.fire({
                    icon: 'success',
                    title: '지원이 취소되었습니다.',
                });
            });
    },
});

export const applySelector = (state) => state.applyReducer;

export const { resetStatus } = applySlice.actions;

export default applySlice.reducer;

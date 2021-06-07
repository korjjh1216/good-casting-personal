import profileService from '../service/profile.service';
import uuid from 'uuid/dist/v4';
import Swal from 'sweetalert2';
import { navigate } from 'gatsby-link';

const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit');

export const profileList = createAsyncThunk('PROFILE_LIST', async (pageRequest) => {
    console.log('reducer profileList() pageRequest: ' + JSON.stringify(pageRequest));
    const response = await profileService.profileList(pageRequest);

    return response.data;
});

export const profileDetail = createAsyncThunk('PROFILE_DETAIL', async (id) => {
    console.log('profileDetail() id: ' + id);

    const response = await profileService.profileDetail(id);

    return response.data;
});

export const profileRegister = createAsyncThunk('PROFILE_REGISTER', async (arg) => {
    const response = await profileService.profileRegister(arg);
    return response.data;
});

export const profileDelete = createAsyncThunk('PROFILE_DELETE', async (id) => {
    const response = await profileService.profileDelete(id);
    navigate('/actor-mypage');
    return response.data;
});
export const delcheck = createAsyncThunk('DELETE_CHECK', async (id) => {
    const response = await profileService.delcheck(id);
    return response.data;
});

const initialState = {
    profileList: [],
    careerList: [],
    fileList: [],
    pageRequest: {
        page: 1,
        size: 10,
        sort: 'profileId',
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
        pageRequest: {},
    },
    profile: {
        actor: {},
        files: [],
        careers: [],
    },
    reset: false,
};

const profileSlice = createSlice({
    name: 'profile',
    initialState: initialState,
    reducers: {
        resetProfileSearch: (state = initialState) => {
            return {
                ...initialState,
                reset: !state.reset,
            };
        },
        addCareer(state, { payload }) {
            state.careerList.push({
                uuid: uuid(),
                year: payload.year,
                genre: payload.genre,
                title: payload.title,
                contents: payload.contents,
            });
        },
        deleteCareer(state, { payload }) {
            state.careerList = state.careerList.filter((career) => career.uuid !== payload);
        },
    },
    extraReducers: (builder) => {
        builder
            .addCase(profileList.fulfilled, (state, { payload }) => {
                console.log('payload :' + JSON.stringify(payload));

                return {
                    ...state,
                    pageResult: payload,
                    pageRequest: payload.pageRequest,
                };
            })
            .addCase(profileRegister.fulfilled, (state, { payload }) => {
                console.log(payload);

                Swal.fire({
                    icon: 'success',
                    title: '프로필이 등록되었습니다.',
                });
            })
            .addCase(profileRegister.rejected, (state, { payload }) => {
                Swal.fire({
                    icon: 'error',
                    title: '내용을 모두 입력해주세요',
                });
            })
            .addCase(profileDetail.fulfilled, (state, { payload }) => {
                return {
                    ...state,
                    profile: payload,
                };
            })
            .addCase(profileDelete.fulfilled, (state, { payload }) => {
                console.log(payload);

                Swal.fire({
                    icon: 'success',
                    title: '프로필이 삭제되었습니다.',
                });
            });
    },
});

export const profileSelector = (state) => state.profileReducer;

export const { resetProfileSearch, addCareer, deleteCareer } = profileSlice.actions;
export default profileSlice.reducer;

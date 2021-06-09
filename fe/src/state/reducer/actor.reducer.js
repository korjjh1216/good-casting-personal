import actorService from '../service/actor.service';
import Swal from 'sweetalert2';

const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit');

export const updateActorInfo = createAsyncThunk('ACTOR_UPDATE', async (arg) => {
    const response = await actorService.updateactorInfo(arg);
    return response.data;
});

export const getActorInfo = createAsyncThunk('ACTOR_INFO', async () => {
    const response = await actorService.getActorInfo();
    return response.data;
});

export const unRegister = createAsyncThunk('UNREGISTER', async (arg) => {
    console.log(arg);
    const response = await actorService.unRegister(arg);
    return response.data;
});

const actorSlice = createSlice({
    name: 'actor',
    initialState: {
        actor: {},
    },
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(getActorInfo.fulfilled, (state, { payload }) => {
                console.log(payload);
                state.actor = payload;
            })
            .addCase(updateActorInfo.fulfilled, (state, { payload }) => {
                state.actor = payload;
                Swal.fire({
                    icon: 'success',
                    title: '정보가 수정되었습니다.',
                });
            });
    },
});

export const actorSelctor = (state) => state.actorReducer;

export default actorSlice.reducer;

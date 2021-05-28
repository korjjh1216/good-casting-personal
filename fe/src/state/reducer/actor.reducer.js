import actorService from '../service/actor.service';

const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit');

export const actorList = createAsyncThunk('ACTOR_LIST', async (arg) => {
    console.log('reducer signup() arg: ' + JSON.stringify(arg));
    const response = await actorService.actorInfo(arg);
    return response.data;
});

const actorSlice = createSlice({
    name: 'actor',
    initialState: {
        actor: {},
    },
    reducers: {},
    extraReducers: (builder) => {
        builder.addCase(actorList.fulfilled, (state, { payload }) => {});
    },
});
export const actorSelctor = (state) => state.actorReducer;

export const { isUserLoggendIn } = actorSlice.actions;
export default actorSlice.reducer;

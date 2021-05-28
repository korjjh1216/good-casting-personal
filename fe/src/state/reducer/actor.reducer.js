import actorService from '../service/actor.service'

const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const updateActorInfo = createAsyncThunk('ACTOR_UPDATE', async (arg) => {
    console.log(arg)
    const response = await actorService.updateactorInfo(arg)
    console.log('reducer : ' + JSON.stringify(response.data))
    return response.data
})

export const getActorInfo = createAsyncThunk('ACTOR_INFO', async () => {
    const response = await actorService.getActorInfo()
    return response.data
})

const actorSlice = createSlice({
    name: 'actor',
    initialState: {
        actor: {},
    },
    reducers: {},
    extraReducers: (builder) => {
        builder.addCase(getActorInfo.fulfilled, (state, { payload }) => {
            state.actor = payload
        })
    },
})

export const actorSelctor = (state) => state.actorReducer

export default actorSlice.reducer

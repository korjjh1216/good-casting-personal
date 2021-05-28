import producerService from '../service/producer.service'

const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const updateProducerInfo = createAsyncThunk('PRODUCER_UPDATE', async (arg) => {
    console.log(arg)
    const response = await producerService.updateProducerInfo(arg)
    console.log('reducer : ' + JSON.stringify(response.data))
    return response.data
})

export const getProducerInfo = createAsyncThunk('PRODUCER_INFO', async () => {
    const response = await producerService.getProducerInfo()
    console.log('reducer : ' + JSON.stringify(response.data))
    return response.data
})

const producerSlice = createSlice({
    name: 'producer',
    initialState: {
        producer: {},
    },
    reducers: {},
    extraReducers: (builder) => {
        builder.addCase(getProducerInfo.fulfilled, (state, { payload }) => {
            state.producer = payload
        })
    },
})

export const producerSelctor = (state) => state.producerReducer

export default producerSlice.reducer

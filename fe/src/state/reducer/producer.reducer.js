import producerService from '../service/producer.service';
import Swal from 'sweetalert2';

const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit');

export const updateProducerInfo = createAsyncThunk('PRODUCER_UPDATE', async (arg) => {
    const response = await producerService.updateProducerInfo(arg);
    return response.data;
});

export const getProducerInfo = createAsyncThunk('PRODUCER_INFO', async () => {
    const response = await producerService.getProducerInfo();
    return response.data;
});

export const unRegister = createAsyncThunk('UNREGISTER', async (arg) => {
    console.log(arg);
    const response = await producerService.unRegister(arg);
    return response.data;
});

const producerSlice = createSlice({
    name: 'producer',
    initialState: {
        producer: {},
    },
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(getProducerInfo.fulfilled, (state, { payload }) => {
                state.producer = payload;
            })
            .addCase(updateProducerInfo.fulfilled, (state, { payload }) => {
                state.producer = payload;
                Swal.fire({
                    icon: 'success',
                    title: '정보가 수정되었습니다.',
                });
            });
    },
});

export const producerSelctor = (state) => state.producerReducer;

export default producerSlice.reducer;

import messageService from '../service/message.service';

const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit');

export const messageList = createAsyncThunk('MESSAGE_LIST', async () => {
    const response = await messageService.messageList();
    console.log(response.data);
    return response.data;
});

const messageSlice = createSlice({
    name: 'message',
    initialState: {
        messageList: [],
    },
    reducers: {},
    extraReducers: (builder) => {
        builder.addCase(messageList.fulfilled, (state, { payload }) => {
            console.log(payload);
            state.messageList = payload;
        });
    },
});

export const messageSelector = (state) => state.messageReducer;

export default messageSlice.reducer;

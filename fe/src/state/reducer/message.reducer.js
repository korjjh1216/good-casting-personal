import messageService from '../service/message.service';

const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit');

export const getMessageList = createAsyncThunk('MESSAGE_LIST', async () => {
    const response = await messageService.messageList();
    return response.data;
});

export const deleteMessage = createAsyncThunk('DELETE_MESSAGE', async (arg) => {
    const response = await messageService.deleteMessage(arg);
    return response.data;
});

export const updateMessage = createAsyncThunk('READ_MESSAGE', async (arg) => {
    const response = await messageService.readMessage(arg);
    return response.data;
});

const messageSlice = createSlice({
    name: 'message',
    initialState: {
        messageList: [],
    },
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(getMessageList.fulfilled, (state, { payload }) => {
                return {
                    ...state,
                    messageList: payload,
                };
            })
            .addCase(deleteMessage.fulfilled, (state, { payload }) => {
                console.log(payload);
            })
            .addCase(updateMessage.fulfilled, (state, { payload }) => {
                console.log(payload);

                return {
                    ...state,
                    messageList: payload,
                };
            });
    },
});

export const messageSelector = (state) => state.messageReducer;
export const { readMessage } = messageSlice.actions;

export default messageSlice.reducer;

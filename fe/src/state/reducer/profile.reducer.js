const { createSlice } = require('@reduxjs/toolkit');

const profileSlice = createSlice({
    name: 'profile',
    initialState: {
        profile: [],
    },
    reducers: {},
    extraReducers: () => {},
});

export const profileSelctor = (state) => state.profileReducer;

export default profileSlice.reducer;

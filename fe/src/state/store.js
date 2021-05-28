import reducer from './reducer/user.reducer';

const { configureStore, getDefaultMiddleware } = require('@reduxjs/toolkit');
const { combineReducers } = require('redux');

const rootReducer = combineReducers({
    userReducer: reducer,
    actorReducer: reducer,
});

const store = configureStore({
    reducer: rootReducer,
    middleware: [...getDefaultMiddleware()],
});

export default store;

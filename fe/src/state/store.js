import userReducer from './reducer/user.reducer';
import actorReducer from './reducer/actor.reducer';
import hireReducer from './reducer/hire.reducer';
import profileReducer from './reducer/profile.reducer';
import producerReducer from './reducer/producer.reducer';
import fileReducer from './reducer/file.reducer';
import messageReducer from './reducer/message.reducer';
import applyReducer from './reducer/apply.reducer.js';

const { configureStore, getDefaultMiddleware } = require('@reduxjs/toolkit');
const { combineReducers } = require('redux');

const rootReducer = combineReducers({
    userReducer,
    actorReducer,
    hireReducer,
    profileReducer,
    producerReducer,
    fileReducer,
    messageReducer,
    applyReducer,
});

const store = configureStore({
    reducer: rootReducer,
    middleware: [...getDefaultMiddleware()],
});

export default store;

import {combineReducers, configureStore} from '@reduxjs/toolkit'
import userReducer from "./reducers/userReducer";
import issueReducer from "./reducers/issueReducer";

const reducers = combineReducers({
  // appReducer: appReducer,
  userReducer: userReducer,
  issueReducer: issueReducer,
})

const store = configureStore({
  reducer: reducers,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false
    })
},);

export default store;

import {combineReducers, configureStore} from '@reduxjs/toolkit'
import userReducer from "./reducers/userReducer";
import issueReducer from "./reducers/issueReducer";

const combinedReducers = combineReducers({
  issueReducer: issueReducer,
  userReducer: userReducer
})

const store = configureStore({
  reducer: combinedReducers,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false
    })
})

export default store

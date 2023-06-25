import {combineReducers, configureStore} from '@reduxjs/toolkit'
import issueReducer from "./reducers/issueReducer";
import appReducer from "./reducers/appReducer";

const combinedReducers = combineReducers({
  issueReducer: issueReducer,
  appReducer: appReducer
})

const store = configureStore({
  reducer: combinedReducers,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false
    })
})

export default store

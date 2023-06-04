import {configureStore} from '@reduxjs/toolkit'
import appReducer from './reducers/appReducer';

// Reference here all your application reducers
const reducer = {
  appReducer: appReducer,
}

const store = configureStore({
  reducer,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false
    })
},);

export default store;

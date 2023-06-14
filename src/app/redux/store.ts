import {configureStore} from '@reduxjs/toolkit'
import userReducer from "./reducers/userReducer";
// import appReducer from './reducers/appReducer';

// Reference here all your application reducers
const reducer = {
  // appReducer: appReducer,
  userReducer: userReducer
}

const store = configureStore({
  reducer,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false
    })
},);

export default store;

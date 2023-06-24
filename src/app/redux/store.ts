import {combineReducers, configureStore} from '@reduxjs/toolkit'
import userReducer from "./reducers/userReducer";
import issueReducer from "./reducers/issueReducer";
import AsyncStorage from "@react-native-async-storage/async-storage";
import {persistStore, persistReducer} from 'redux-persist'

const persistConfig = {
  key: 'app',
  storage: AsyncStorage,
  whitelist: [
    'userReducer',
  ]
};

const combinedReducers = combineReducers({
  issueReducer: issueReducer,
  userReducer: userReducer
});

const persistedReducers = persistReducer(persistConfig, combinedReducers);

const store = configureStore({
  reducer: persistedReducers,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false
    })
})

const persistor = persistStore(store)

export default store
export {persistor}

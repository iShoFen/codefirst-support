import {SET_THEME} from "../constants";
import {PayloadAction} from "@reduxjs/toolkit";
import {ThemeMode} from "../../themes/types";

export type UserReducerState = {
  theme: ThemeMode
}

const initialState: UserReducerState = {
  theme: 'dark'
}

const userReducer = (state: UserReducerState = initialState, action: PayloadAction<ThemeMode>) => {
  switch (action.type) {
    case SET_THEME:
      return {
        ...state,
        theme: action.payload
      }
    default:
      return state
  }
}

export default userReducer

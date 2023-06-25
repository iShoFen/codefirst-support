import {SET_LOGGED_USER, SET_THEME} from "../constants";
import {PayloadAction} from "@reduxjs/toolkit";
import {User} from "../../model/User";
import {CSTheme} from "../../data/themes";

export const setTheme = (theme: CSTheme): PayloadAction<CSTheme> => {
  console.log('setTheme', theme)
  return {
    type: SET_THEME,
    payload: theme
  }
}

export const setLoggedUser = (user: User | undefined): PayloadAction<User | undefined> => {
  return {
    type: SET_LOGGED_USER,
    payload: user
  }
}

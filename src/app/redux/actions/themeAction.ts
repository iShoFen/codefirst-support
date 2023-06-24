import {SET_THEME} from "../constants";
import {ThemeMode} from "../../themes/types";
import {PayloadAction} from "@reduxjs/toolkit";

export const setTheme = (theme: ThemeMode): PayloadAction<string> => {
  return {
    type: SET_THEME,
    payload: theme
  }
}

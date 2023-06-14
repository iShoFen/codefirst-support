import {SET_THEME} from "../constants";
import {ThemeMode} from "../../themes/types";

export const setTheme = (theme: ThemeMode) => {
  return {
    type: SET_THEME,
    payload: theme
  }
}

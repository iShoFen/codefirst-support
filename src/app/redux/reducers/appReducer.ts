import {SET_THEME, SET_LOGGED_USER} from "../constants";
import {User} from "../../model/User";
import {CSTheme, THEMES} from "../../data/themes";

export type AppReducerState = {
  theme: CSTheme
  loggedUser: User | undefined
}

const initialState: AppReducerState = {
  theme: THEMES[2],
  loggedUser: undefined
}

//@ts-ignore
const appReducer = (state: AppReducerState = initialState, action): AppReducerState => {
  switch (action.type) {
    case SET_THEME:
      return {
        ...state,
        theme: action.payload
      }
    case SET_LOGGED_USER:
      return {
        ...state,
        loggedUser: action.payload
      }
    default:
      return state
  }
}

export default appReducer

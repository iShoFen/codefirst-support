import {SET_THEME, SET_LOGGED_USER} from "../constants";
import {User} from "../../model/User";
import {CSTheme, THEMES} from "../../data/themes";

export type UserReducerState = {
  theme: CSTheme
  loggedUser: User | undefined
}

const initialState: UserReducerState = {
  theme: THEMES[2],
  loggedUser: undefined
}

//@ts-ignore
const userReducer = (state: UserReducerState = initialState, action): UserReducerState => {
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

export default userReducer

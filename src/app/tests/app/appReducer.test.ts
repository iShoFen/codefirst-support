import {describe, expect, it} from "@jest/globals";
import appReducer, {AppReducerState} from "../../redux/reducers/appReducer";
import {THEMES} from "../../data/themes";
import {setLoggedUser, setTheme} from "../../redux/actions/appAction";
import {USERS} from "../../data/user";

describe('test app reducer', () => {
  let initialState: AppReducerState = {
    loggedUser: undefined,
    theme: THEMES[2]
  }

  it('should return initial state', () => {
    expect(appReducer(undefined, {})).toEqual(initialState)
  })

  it('should handle SET_THEME', () => {
    let theme = THEMES[1]

    expect(
      appReducer(initialState, setTheme(theme))
    ).toEqual({
      loggedUser: undefined,
      theme: theme
    })

    theme = THEMES[0]

    expect(
      appReducer(initialState, setTheme(theme))
    ).toEqual({
      loggedUser: undefined,
      theme: theme
    })
  })

  it('should handle SET_LOGGED_USER', () => {
    const loggedUser = USERS[1]

    expect(
      appReducer(initialState, setLoggedUser(loggedUser))
    ).toEqual({
      ...initialState,
      loggedUser: loggedUser
    })

    expect(
      appReducer({
        ...initialState,
        loggedUser: loggedUser
      }, setLoggedUser(undefined))
    ).toEqual({
      ...initialState,
      loggedUser: undefined
    })
  })
})

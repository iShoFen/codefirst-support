import {describe, expect, it} from "@jest/globals";
import {CSTheme, THEMES} from "../../data/themes";
import {PayloadAction} from "@reduxjs/toolkit";
import {SET_LOGGED_USER, SET_THEME} from "../../redux/constants";
import {setLoggedUser, setTheme} from "../../redux/actions/appAction";
import {User} from "../../model/User";
import {USERS} from "../../data/user";

describe('test app actions', () => {
  it('should create an action with SET_THEME type', () => {
    const payload: CSTheme = THEMES[1]
    const expectation: PayloadAction<CSTheme> = {
      type: SET_THEME,
      payload: payload
    }

    expect(setTheme(payload)).toEqual(expectation)
  })

  it('should create an action with SET_LOGGED_USER type', () => {
    const payload: User = USERS[1]
    const expectation: PayloadAction<User | undefined> = {
      type: SET_LOGGED_USER,
      payload: payload
    }

    expect(setLoggedUser(payload)).toEqual(expectation)

    const expectation1: PayloadAction<User | undefined> = {
      type: SET_LOGGED_USER,
      payload: undefined
    }
    expect(setLoggedUser(undefined)).toEqual(expectation1)
  })
})

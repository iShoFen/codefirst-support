import {CSTheme} from "../../data/themes";
import AsyncStorage from "@react-native-async-storage/async-storage";
import {AppDispatch} from "../types";
import {setTheme} from "../actions/userAction";

const THEME_KEY = 'app_theme'

const storeTheme = async (theme: CSTheme): Promise<void> => {
  try {
    const jsonTheme = JSON.stringify(theme)
    await AsyncStorage.setItem(THEME_KEY, jsonTheme)
  } catch (error) {
    console.error('An error occured while saving theme to AsyncStorage', error)
  }
}

export const saveTheme = (theme: CSTheme) => {
  return async (dispatch: AppDispatch) => {
    await storeTheme(theme)
    dispatch(setTheme(theme))
  }
}

export const getTheme = async (): Promise<CSTheme | undefined> => {
  try {
    const jsonTheme = await AsyncStorage.getItem(THEME_KEY)
    return jsonTheme !== null ? JSON.parse(jsonTheme) : null
  } catch (error) {
    console.error('An error occured while retrieving theme to AsyncStorage', error)
  }
}

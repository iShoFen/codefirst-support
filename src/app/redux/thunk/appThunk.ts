import {CSTheme} from "../../data/themes";
import AsyncStorage from "@react-native-async-storage/async-storage";
import {AppDispatch} from "../types";
import {setLoggedUser, setTheme} from "../actions/userAction";
import {User} from "../../model/User";

const THEME_KEY = 'app_theme'
const LOGGED_USER_KEY = 'logged_user'

const storeToAsyncStorage = async (key: string, value: any): Promise<void> => {
  try {
    const json = JSON.stringify(value)
    await AsyncStorage.setItem(key, json)
  } catch (error) {
    console.error('An error occured while saving value to AsyncStorage', error)
  }
}

const getValueFromAsyncStorage = async (key: string): Promise<any> => {
  try {
    const json = await AsyncStorage.getItem(key)
    return json !== null ? JSON.parse(json) : null
  } catch (error) {
    console.error('An error occured while retrieving from AsyncStorage', error)
  }
}

export const saveTheme = (theme: CSTheme) => {
  return async (dispatch: AppDispatch) => {
    await storeToAsyncStorage(THEME_KEY, theme)
    dispatch(setTheme(theme))
  }
}

export async function getTheme(): Promise<CSTheme | undefined> {
  return await getValueFromAsyncStorage(THEME_KEY)
}

export const saveLoggedUser = (user: User) => {
  return async (dispatch: AppDispatch) => {
    await storeToAsyncStorage(LOGGED_USER_KEY, user)
    dispatch(setLoggedUser(user))
  }
}

export async function getLoggedUser(): Promise<User | undefined> {
  const json = await getValueFromAsyncStorage(LOGGED_USER_KEY)
  if(!json) return
  return new User(json['_email'], json['_image'], json['_isAdmin'])
}

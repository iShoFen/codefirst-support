import {darkColors, lightColors} from "../colors";
import {CSColors, ThemeColors} from "../types";
import {useThemeMode} from "./useThemeMode";

export function useColors(): CSColors {
  const {dark} = useThemeMode()
  const colors: ThemeColors = dark ? darkColors : lightColors

  return {
    white: '#ffffff',
    black: '#212121',
    success: '#5BBC27',
    info: '#3075FF',
    warning: '#FFC721',
    danger: '#E72D0A',
    ...colors
  }
}

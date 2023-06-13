import {useColorScheme} from "react-native";
import {darkColors, lightColors} from "../colors";
import {CSColors, ThemeColors} from "../types";

export function useColors(): CSColors {
  const theme = useColorScheme()

  // const colors: ThemeColors = theme == "dark" ? darkColors : lightColors
  const colors: ThemeColors = darkColors

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

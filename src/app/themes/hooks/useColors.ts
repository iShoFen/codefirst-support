import {useColorScheme} from "react-native";
import {darkColors, lightColors} from "../colors";
import {GlobalColors, ThemeColors} from "../types";


type Colors = GlobalColors & ThemeColors

export function useColors(): Colors {
  const theme = useColorScheme()

  const colors: ThemeColors = theme == "dark" ? darkColors : lightColors

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

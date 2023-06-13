import {Theme} from "@react-navigation/native";
import {useColorScheme} from "react-native";
import {useColors} from "./useColors";

export function useNavigationTheme(): Theme {
  const mode = useColorScheme()
  const colors = useColors()

  console.log(mode)

  return {
    colors: {
      primary: colors.primary,
      background: colors.background,
      card: '#202325',
      text: '#979C9E',
      notification: colors.danger,
      border: colors.background
    },
    dark: mode === 'dark'
  }
}

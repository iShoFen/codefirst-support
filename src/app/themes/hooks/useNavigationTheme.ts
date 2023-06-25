import {Theme} from "@react-navigation/native";
import {useThemeMode} from "./useThemeMode";
import {darkNavigationColors, lightNavigationColors} from "../colors";

export function useNavigationTheme(): Theme {
  const {dark} = useThemeMode()

  return {
    colors: dark ? darkNavigationColors : lightNavigationColors,
    dark: dark
  }
}

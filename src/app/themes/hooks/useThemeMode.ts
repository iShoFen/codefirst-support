import {useMemo} from "react";
import {useColorScheme} from "react-native";
import {useAppSelector} from "../../redux/hooks";
import {ThemeMode} from "../types";

type ThemeModeType = {
  theme: ThemeMode,
  dark: boolean
}

export function useThemeMode(): ThemeModeType {
  const mode = useColorScheme()
  const theme = useAppSelector(state => state.userReducer.theme)


  const dark = useMemo<boolean>(() => {
    return (theme === 'system' && mode === 'dark') || theme === 'dark'
  }, [mode, theme]);

  return {
    theme: theme,
    dark: dark
  }
}

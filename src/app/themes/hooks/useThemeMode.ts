import {useMemo} from "react";
import {useColorScheme} from "react-native";
import {useAppSelector} from "../../redux/hooks";
import {CSTheme} from "../../data/themes";

type ThemeModeType = {
  theme: CSTheme,
  dark: boolean
}

export function useThemeMode(): ThemeModeType {
  const mode = useColorScheme()
  const theme = useAppSelector(state => state.userReducer.theme)


  const dark = useMemo<boolean>(() => {
    return (theme.value === 'system' && mode === 'dark') || theme.value === 'dark'
  }, [mode, theme]);

  return {
    theme: theme,
    dark: dark
  }
}

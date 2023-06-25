import {SafeAreaView, StatusBar} from "react-native";
import {useColors} from "../themes/hooks/useColors";
import {useThemeMode} from "../themes/hooks/useThemeMode";
import {useNavigationTheme} from "../themes/hooks/useNavigationTheme";
import StackLoginNavigation from "../navigation/StackLoginNavigation";
import {NavigationContainer} from "@react-navigation/native";
import {useEffect} from "react";
import {getTheme} from "../redux/thunk/appThunk";
import {useAppDispatch} from "../redux/hooks";
import {setTheme} from "../redux/actions/userAction";

export default function RootScreen() {
  const colors = useColors()
  const {dark} = useThemeMode()
  const navigationTheme = useNavigationTheme()
  const dispatch = useAppDispatch()

  useEffect(() => {
    // load theme for AsyncStorage and save it to redux
    const loadTheme = async () => {
      const theme = await getTheme()
      if (theme) {
        dispatch(setTheme(theme))
      }
    }
    void loadTheme()
  }, [dispatch])

  return (
    <SafeAreaView style={{flex: 1, backgroundColor: colors.background}}>
      <StatusBar backgroundColor={colors.background} barStyle={dark ? "light-content" : "dark-content"}/>
      <NavigationContainer theme={navigationTheme}>
        <StackLoginNavigation/>
      </NavigationContainer>
    </SafeAreaView>
  )
}

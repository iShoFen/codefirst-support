import {SafeAreaView, StatusBar} from "react-native";
import {useColors} from "../themes/hooks/useColors";
import {useThemeMode} from "../themes/hooks/useThemeMode";
import {useNavigationTheme} from "../themes/hooks/useNavigationTheme";
import StackLoginNavigation from "../navigation/StackLoginNavigation";
import {NavigationContainer} from "@react-navigation/native";

export default function RootScreen() {
  const colors = useColors()
  const {dark} = useThemeMode()
  const navigationTheme = useNavigationTheme()

  return (
    <SafeAreaView style={{flex: 1, backgroundColor: colors.background}}>
      <StatusBar backgroundColor={colors.background} barStyle={dark ? "light-content": "dark-content"} />
      <NavigationContainer theme={navigationTheme}>
        <StackLoginNavigation />
      </NavigationContainer>
    </SafeAreaView>
  )
}

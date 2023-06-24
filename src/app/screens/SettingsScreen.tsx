import {Button, SafeAreaView, View} from "react-native";
import CSText from "../components/commons/CSText";
import {useAppDispatch, useAppSelector} from "../redux/hooks";
import {setTheme} from "../redux/actions/themeAction";
import {ThemeMode} from "../themes/types";

export default function SettingsScreen() {
  const theme = useAppSelector(state => state.userReducer.theme)
  const dispatch = useAppDispatch()
  const handleChange = (theme: ThemeMode) => {
    dispatch(setTheme(theme))
  }

  return (
    <SafeAreaView>
      <View style={{padding: 8}}>
        <CSText text="SettingsScreen" type="h1"/>

        <Button title="Light" onPress={() => handleChange('light')} />
        <Button title="Dark" onPress={() => handleChange('dark')} />
        <Button title="System" onPress={() => handleChange('system')} />

        <CSText text={theme} />

      </View>
    </SafeAreaView>
  )
}

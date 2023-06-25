import CSText from "../commons/CSText";
import ThemeList from "./ThemeList";
import {StyleSheet, View} from "react-native";
import {useAppDispatch, useAppSelector} from "../../redux/hooks";
import {useCallback} from "react";
import {CSTheme} from "../../data/themes";
import {setTheme} from "../../redux/actions/userAction";

export default function SettingsThemeSection() {
  const theme = useAppSelector(state => state.userReducer.theme)
  const dispatch = useAppDispatch()

  const handleChange = useCallback((theme: CSTheme) => {
    dispatch(setTheme(theme))
  }, [dispatch])

  return (
    <View style={styles.container}>
      <CSText text="Thème" type="h2"/>
      <CSText text={`Vous avez sélectionné le thème: ${theme.name}`}/>
      <ThemeList onChange={handleChange}/>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    gap: 4,
  }
})

import {CSTheme, THEMES} from "../../data/themes";
import CSButton from "../commons/CSButton";
import {StyleSheet, View} from "react-native";

type ThemeListProps = {
  onChange: (theme: CSTheme) => void
}

export default function ThemeList(props: ThemeListProps) {
  const {
    onChange
  } = props

  return (
    <View style={styles.container}>
      {THEMES.map(theme => (
        <CSButton key={theme.value} style={styles.button} text={theme.name} onPress={() => onChange(theme)}/>
      ))}
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    gap: 8,
    flexDirection: 'row',
  },
  button: {
    flex: 1
  }
})

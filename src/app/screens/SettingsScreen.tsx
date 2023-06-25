import {StyleSheet, View} from "react-native";
import CSText from "../components/commons/CSText";
import SettingsThemeSection from "../components/theme/SettingsThemeSection";
import SettingsUserSection from "../components/user/SettingsUserSection";
import {BASE_URL} from "@env";

export default function SettingsScreen() {

  return (
    <View style={styles.container}>
      <CSText text="Paramètres" type="h1"/>
      <SettingsThemeSection />
      <SettingsUserSection />

      <View style={{gap: 4}}>
        <CSText text="Informations" type="h2" />
        <CSText text={`API: ${BASE_URL}`} />
      </View>

    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    gap: 16,
    padding: 8
  }
})

import {SafeAreaView, StyleSheet, Text, TouchableOpacity, View} from "react-native";
import CSText from "../components/commons/CSText";
import {useCallback} from "react";
import {useNavigation} from "@react-navigation/native";
import {CreateStackNavigationProp} from "../navigation/types/NavigationProp";
import {useColors} from "../themes/hooks";

export default function HomeCreateScreen() {
  const navigation = useNavigation<CreateStackNavigationProp>()
  const colors = useColors()

  const handleCreateIssue = useCallback(() => {
    navigation.navigate('CreateIssue')
  }, [])

  return (<SafeAreaView style={{flex: 1}}>
    <View style={styles.container}>
      <CSText text="Création" type="h1" />

      <CSText text="Cette page permet de créer d'accéder à la création de tickets et de questionnaires." />
      <CSText text="C'est à vous de jouer et contribuer à la plateforme Codefirst." />

      <View style={styles.buttonContainer}>
        <TouchableOpacity
          style={[styles.button, {
            backgroundColor: colors.primary
          }]}
          onPress={handleCreateIssue}>
          <CSText text="Créer un ticket" style={{
            color: colors.black
          }}/>
        </TouchableOpacity>
        <TouchableOpacity
          style={[styles.button, {
            backgroundColor: colors.primary
          }]}>
          <CSText text="Créer un questionnaire" style={{
            color: colors.black
          }}/>
        </TouchableOpacity>
      </View>

    </View>
  </SafeAreaView>)
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 10,
    paddingBottom: 0,
    gap: 8
  },
  buttonContainer: {
    gap: 16,
    marginVertical: 16
  },
  button: {
    padding: 8,
    borderRadius: 8,
    alignSelf: 'center'
  }
})

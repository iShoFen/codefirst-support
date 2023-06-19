import {StyleSheet, View} from "react-native";
import CSField from "../../components/commons/CSField";

export default function CreateIssueScreen() {

  return (<View>
    <CSField placeholder="Nom du ticket"
             label="Nom"/>
    <CSField placeholder="Nom du ticket"
             label="Nom"/>
    <CSField value="bug" label="Catégorie" enabled={false}/>
    <CSField label="Multiline" placeholder="Champ 1" multiLine={true}/>
  </View>)
}

const styles = StyleSheet.create({})

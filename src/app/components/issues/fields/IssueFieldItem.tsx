import {IssueField} from "../../../model/issues/IssueField";
import {StyleSheet, View} from "react-native";
import CSText from "../../commons/CSText";

type IssueFieldItemProps = {
  field: IssueField
}

export default function IssueFieldItem(props: IssueFieldItemProps) {
  const {field} = props

  return (
    <View style={styles.container}>
      <CSText text={field.title} type="bold"/>
      <CSText text={field.value} type="small"/>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    padding: 8,
    gap: 4
  }
})

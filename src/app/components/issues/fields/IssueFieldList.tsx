import {StyleSheet, View} from "react-native";
import {IssueField} from "../../../model/issues/IssueField";
import {useColors} from "../../../themes/hooks";
import IssueFieldItem from "./IssueFieldItem";

type IssueFieldListProps = {
  fields: IssueField[]
}

export default function IssueFieldList(props: IssueFieldListProps) {
  const colors = useColors()
  const {fields} = props

  return (
    <View style={[
      styles.container,
      {
        backgroundColor: colors.backgroundVariant,
      }
    ]}>
      {
        fields.map(field => <IssueFieldItem key={field.title} field={field}/>)
      }
    </View>
  )
}
const styles = StyleSheet.create({
  container: {
    backgroundColor: 'white',
    marginTop: 8,
    borderRadius: 8
  }
})

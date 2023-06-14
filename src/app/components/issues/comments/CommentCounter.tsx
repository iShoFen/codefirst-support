import {StyleSheet, Text, View} from "react-native";
import MaterialCommunityIcons from "react-native-vector-icons/MaterialCommunityIcons";
import {useColors} from "../../../themes/hooks";

type CommentCounterProps = {
  value: number
}

export default function CommentCounter(props: CommentCounterProps) {

  const colors = useColors()

  const {
    value
  } = props

  return (<View style={styles.container}>
    <MaterialCommunityIcons name="comment-multiple" size={16} color={colors.text}/>
    <Text style={[styles.text, {
      color: colors.text
    }]}>{value}</Text>
  </View>)
}

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 4
  },
  text: {}
})

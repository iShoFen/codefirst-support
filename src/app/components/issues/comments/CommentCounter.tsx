import {StyleSheet, Text, View, ViewStyle} from "react-native";
import MaterialCommunityIcons from "react-native-vector-icons/MaterialCommunityIcons";

type CommentCounterProps = {
  value: number
  style?: ViewStyle
}

export default function CommentCounter(props: CommentCounterProps) {

  const {
    value
  } = props

  return (<View style={styles.container}>
    <MaterialCommunityIcons name="comment-multiple" size={16}/>
    <Text>{value}</Text>
  </View>)
}

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 4
  }
})

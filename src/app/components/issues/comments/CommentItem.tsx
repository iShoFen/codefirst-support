import {StyleSheet, Text, View, ViewStyle} from "react-native";
import {Comment} from "../../../model/issues/Comment";
import {Divider} from "react-native-paper";

type CommentItemProps = {
  comment: Comment
  style?: ViewStyle
}

export default function CommentItem(props: CommentItemProps) {

  const {
    comment,
    style
  } = props

  return (<View style={[styles.container, style]}>
    <View style={styles.header}>
      <Text style={styles.author}>{comment.author}</Text>
      <Text>-</Text>
      <Text style={styles.createdAt}>{comment.createdAt.toLocaleDateString()}</Text>
    </View>
    <Divider bold/>
    <Text style={styles.content}>{comment.content}</Text>
  </View>)
}

const styles = StyleSheet.create({
  container: {
    gap: 4,
    padding: 8,
    backgroundColor: 'white',
    borderBottomWidth: 1,
    borderColor: 'lightgray'
  },
  header: {
    flexDirection: 'row',
    gap: 4,
    alignItems: 'center'
  },
  author: {
    fontWeight: 'bold',
    fontSize: 18
  },
  createdAt: {
  },
  content: {
    fontSize: 16
  }
})

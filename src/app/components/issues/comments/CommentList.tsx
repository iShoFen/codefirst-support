import {Comment} from "../../../model/issues/Comment";
import CommentItem from "./CommentItem";
import {FlatList, StyleSheet} from "react-native";

type CommentListProps = {
  comments: Comment[]
}

export default function CommentList(props: CommentListProps) {
  const {comments} = props

  return (
    <FlatList
      data={comments}
      renderItem={
        ({item}) => <CommentItem style={styles.comment} comment={item}/>
      }
    />
  )
}

const styles = StyleSheet.create({
  comment: {
    marginVertical: 4,
  }
})

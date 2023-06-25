import {Comment} from "../../../model/issues/Comment";
import CommentItem from "./CommentItem";
import {FlatList, StyleSheet} from "react-native";
import CSText from "../../commons/CSText";

type CommentListProps = {
  comments: Comment[]
  onDelete: (comment: Comment) => void
}

export default function CommentList(props: CommentListProps) {
  const {comments, onDelete} = props

  return (
    <FlatList
      data={comments}
      ListEmptyComponent={() => (<CSText text="Aucun commentaire" />)}
      renderItem={
        ({item}) => <CommentItem style={styles.comment} comment={item} onDelete={onDelete}/>
      }
    />
  )
}

const styles = StyleSheet.create({
  comment: {
    marginVertical: 4,
  }
})

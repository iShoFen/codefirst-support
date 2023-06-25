import {ScrollView, StyleSheet, View} from "react-native";
import CSText from "../../commons/CSText";
import CommentList from "./CommentList";
import {Comment} from "../../../model/issues/Comment";

type CommentSectionProps = {
  comments: Comment[]
}

export default function CommentSection(props: CommentSectionProps) {
  const {comments} = props

  return (
    <View>
      <CSText text="Commentaires" type="h2" style={styles.header}/>

      <ScrollView horizontal contentContainerStyle={{flex: 1}}>
        <CommentList comments={comments}/>
      </ScrollView>
    </View>
  )
}

const styles = StyleSheet.create({
  header: {
    marginTop: 16,
    marginBottom: 8
  }
})

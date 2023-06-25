import {ScrollView, StyleSheet, View} from "react-native";
import CSText from "../../commons/CSText";
import CommentList from "./CommentList";
import {Comment} from "../../../model/issues/Comment";
import CSButton from "../../commons/CSButton";
import {useCallback} from "react";
import {useNavigation} from "@react-navigation/native";
import {IssueStackNavigationProp} from "../../../navigation/types/NavigationProp";

type CommentSectionProps = {
  issueId: string
  comments: Comment[]
}

export default function CommentSection(props: CommentSectionProps) {
  const {comments, issueId} = props
  const navigation = useNavigation<IssueStackNavigationProp>()

  const handleAdd = useCallback(() => {
    navigation.navigate('CreateComment', {issueId: issueId})
  }, [navigation, issueId])

  return (
    <View>
      <View style={styles.headerContainer}>
        <CSText text="Commentaires" type="h2"/>
        <CSButton icon="plus" style={styles.addButton} onPress={handleAdd}/>
      </View>

      <ScrollView horizontal contentContainerStyle={{flex: 1}}>
        <CommentList comments={comments}/>
      </ScrollView>
    </View>
  )
}

const styles = StyleSheet.create({
  headerContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginTop: 16,
    marginBottom: 8
  },
  addButton: {
    alignSelf: 'center',
    borderRadius: 100
  }
})

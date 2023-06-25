import {Alert, StyleSheet, View} from "react-native";
import {useNavigation, useRoute} from "@react-navigation/native";
import {CreateCommentRouteProps, IssueRouteProps} from "../../navigation/types/RouteProps";
import {useCallback, useState} from "react";
import CSField from "../../components/commons/CSField";
import CSText from "../../components/commons/CSText";
import CSButton from "../../components/commons/CSButton";
import {useAppDispatch, useAppSelector} from "../../redux/hooks";
import {createComment} from "../../hooks/comments";
import {getIssue} from "../../redux/thunk/issueThunk";
import {IssueStackNavigationProp} from "../../navigation/types/NavigationProp";

export default function CreateCommentScreen() {
  const route = useRoute<CreateCommentRouteProps>()
  const [content, setContent] = useState<string>('')
  const loggedUser = useAppSelector(state => state.appReducer.loggedUser)
  const dispatch = useAppDispatch()
  const navigation = useNavigation<IssueStackNavigationProp>()
  const {issueId} = route.params

  const handleValidate = useCallback(async () => {
    if (!loggedUser) return
    if (content === "") {
      Alert.alert("Attention", "Le commentaire ne peut pas être vide")
    }

    const comment = await createComment(issueId, content, loggedUser.email)

    if (!comment) {
      Alert.alert("Attention", "Une erreur est survenue lors de la création du commentaire")
      return
    }

    void dispatch(getIssue(issueId))
    navigation.goBack()
  }, [dispatch, navigation, issueId, content, loggedUser])

  return (
    <View
      style={styles.container}>
      <CSText text="Ajouter un commentaire" type="h2"/>
      <CSField
        placeholder="Ajouter un commentaire"
        value={content}
        multiLine={true}
        onChange={setContent}
        label="Commentaire"/>

      <CSButton
        text="Valider"
        onPress={handleValidate}
        style={styles.validationButton}
        icon="send"/>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    padding: 8,
    gap: 16
  },
  validationButton: {
    marginTop: 16,
    borderRadius: 24
  }
})

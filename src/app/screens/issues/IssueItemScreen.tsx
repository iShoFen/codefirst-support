import {
  Alert,
  FlatList,
  SafeAreaView,
  ScrollView,
  StyleSheet,
  TouchableOpacity,
  View
} from "react-native";
import CommentItem from "../../components/issues/comments/CommentItem";
import CSCapsule from "../../components/commons/CSCapsule";
import {useNavigation, useRoute} from "@react-navigation/native";
import {IssueItemRouteProps} from "../../navigation/types/RouteProps";
import CSText from "../../components/commons/CSText";
import {useColors} from "../../themes/hooks";
import {useAppDispatch, useAppSelector} from "../../redux/hooks";
import {getIssue, getIssues} from "../../redux/thunk/issueThunk";
import {useCallback, useEffect} from "react";
import {setSelectedIssue} from "../../redux/actions/issueAction";
import {IssueStackNavigationProp} from "../../navigation/types/NavigationProp";
import MaterialCommunityIcons from "react-native-vector-icons/MaterialCommunityIcons";
import {deleteIssue} from "../../hooks/issues";
import IssueFieldList from "../../components/issues/fields/IssueFieldList";
import IssueDetails from "../../components/issues/IssueDetails";

export default function IssueItemScreen() {
  const navigation = useNavigation<IssueStackNavigationProp>()
  const route = useRoute<IssueItemRouteProps>()
  const dispatch = useAppDispatch()
  const colors = useColors()
  const issue = useAppSelector(state => state.issueReducer.selectedIssue)
  const loading = useAppSelector(state => state.issueReducer.loading)

  const {
    id,
  } = route.params

  const handleDelete = useCallback(() => {
    const executeDelete = async () => {
      const result = await deleteIssue(id)
      if (result) {
        void dispatch(getIssues())
        navigation.goBack()
      } else {
        Alert.alert("Erreur", "Une erreur est survenue lors de la suppression du ticket")
      }
    }
    void executeDelete()
  }, [dispatch, id])

  useEffect(() => {
    navigation.setOptions({
      headerRight: () => {

        return (<TouchableOpacity
          onPress={handleDelete}
          style={{
            marginHorizontal: 8,
            padding: 8
          }}>
          <MaterialCommunityIcons
            name="delete"
            color={colors.text} size={24}/>
        </TouchableOpacity>)
      }
    })

  }, [navigation, handleDelete])

  useEffect(() => {
    const loadIssue = async () => {
      await dispatch(getIssue(id))
    }
    void loadIssue()

    return () => {
      const resetIssue = async () => dispatch(setSelectedIssue(undefined))
      void resetIssue()
    }
  }, [dispatch, id]);

  if (loading) {
    return <SafeAreaView style={{flex: 1}}>
      <View style={styles.container}>
        <CSText text="Chargement..." type="bold"/>
      </View>
    </SafeAreaView>
  } else if (!issue) {
    return <SafeAreaView style={{flex: 1}}>
      <View style={styles.container}>
        <CSText text="Impossible de récupérer le ticket car il n'existe pas" color="red" type="h1"/>
      </View>
    </SafeAreaView>
  }

  return (
    <ScrollView contentContainerStyle={{flexGrow: 1}}>
      <View style={styles.container}>
        <IssueDetails issue={issue}/>

        <IssueFieldList fields={issue.fields}/>

        <CSText text="Commentaires" type="h2" style={styles.commentHeader}/>

        <ScrollView horizontal contentContainerStyle={{flex: 1}}>
          <FlatList data={issue.comments}
                    renderItem={
                      ({item}) => <CommentItem style={styles.comment} comment={item}/>
                    }
          />
        </ScrollView>
      </View>
    </ScrollView>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 8
  },
  comment: {
    marginVertical: 4,
  },
  commentHeader: {
    marginTop: 16,
    marginBottom: 8
  }
})

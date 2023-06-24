import {FlatList, SafeAreaView, ScrollView, StyleSheet, View} from "react-native";
import CommentItem from "../../components/issues/comments/CommentItem";
import CSCapsule from "../../components/commons/CSCapsule";
import {useRoute} from "@react-navigation/native";
import {IssueItemRouteProps} from "../../navigation/types/RouteProps";
import CSText from "../../components/commons/CSText";
import {useColors} from "../../themes/hooks";
import {useAppDispatch, useAppSelector} from "../../redux/hooks";
import {getIssue} from "../../redux/thunk/issueThunk";
import {useEffect} from "react";
import {setSelectedIssue} from "../../redux/actions/issueAction";

export default function IssueItemScreen() {
  const route = useRoute<IssueItemRouteProps>()
  const dispatch = useAppDispatch()
  const colors = useColors()
  const issue = useAppSelector(state => state.issueReducer.selectedIssue)
  const loading = useAppSelector(state => state.issueReducer.loading)

  const {
    id,
    title
  } = route.params

  useEffect(() => {
    const loadIssue = async () => {
      await dispatch(getIssue(id))
    }
    loadIssue()

    return () => {
      const resetIssue = async () => await dispatch(setSelectedIssue(undefined))
      resetIssue()
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
    <SafeAreaView style={{flex: 1}}>
      <ScrollView contentContainerStyle={{flexGrow: 1}}>
        <View style={styles.container}>

          <View style={[styles.details, {
            backgroundColor: colors.backgroundVariant,
            borderRadius: 8
          }]}>
            <CSText text={title} type="h1"/>
            <View style={styles.authorAndDate}>
              <CSText text={`Par ${issue.author}`}/>
              <CSText text={issue.createdAt.toLocaleDateString()}/>
            </View>

            <View style={[styles.capsules]}>
              <CSCapsule text={issue.category.name}/>
              <CSCapsule text={issue.category.name}/>
            </View>
          </View>

          <View style={[styles.fields, {
            backgroundColor: colors.backgroundVariant,
            borderRadius: 8
          }]}>
            {issue.fields.map((field, index) => (
              <View
                key={index}
                style={{
                  padding: 8,
                  gap: 4
                }}>
                <CSText text={field.title} type="bold"/>
                <CSText text={field.value} type="small"/>
              </View>
            ))}
          </View>

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
    </SafeAreaView>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 8
  },
  subtitle: {
    paddingVertical: 8,
    paddingHorizontal: 10,
    fontSize: 24,
    fontWeight: '600'
  },
  details: {
    backgroundColor: 'white',
    padding: 8,
    gap: 8,
  },
  fields: {
    backgroundColor: 'white',
    marginTop: 8
  },
  comment: {
    marginVertical: 4,
  },
  authorAndDate: {
    flexDirection: 'row',
    justifyContent: 'space-between'
  },
  capsules: {
    flexDirection: 'row',
    gap: 8
  },
  commentHeader: {
    marginTop: 16,
    marginBottom: 8
  }
})

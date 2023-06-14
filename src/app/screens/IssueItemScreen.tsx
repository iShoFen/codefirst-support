import {FlatList, SafeAreaView, ScrollView, StyleSheet, View} from "react-native";
import CommentItem from "../components/issues/comments/CommentItem";
import CSCapsule from "../components/commons/CSCapsule";
import {useRoute} from "@react-navigation/native";
import {IssueItemRouteProps} from "../navigation/types/RouteProps";
import CSText from "../components/commons/CSText";
import {useColors} from "../themes/hooks";
import {useAppSelector} from "../redux/hooks";
import {getIssue} from "../redux/thunk/issueThunk";
import {useEffect} from "react";

export default function IssueItemScreen() {
  const route = useRoute<IssueItemRouteProps>()
  const colors = useColors()

  const {
    id,
    title
  } = route.params

  const issue = useAppSelector(state => state.issueReducer.selectedIssue)
  if (!issue) {
    return <SafeAreaView style={{flex: 1}}>
      <CSText text="Impossible de récupérer le ticket car il n'existe pas" color="red" type="h1"/>
    </SafeAreaView>
  }

  useEffect(() => {
    return () => {
      getIssue(id)
    };
  }, [id]);


  return (
    <SafeAreaView style={{flex: 1}}>
      <ScrollView contentContainerStyle={{flexGrow: 1}}>
        <View style={styles.container}>
          <CSText text={title} type="h1"/>

          <View style={[styles.details, {
            backgroundColor: colors.backgroundVariant,
            borderRadius: 8
          }]}>
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

          <CSText text="Commentaires" type="h2"/>

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
  title: {
    fontSize: 32,
    padding: 10,
    fontWeight: 'bold'
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
  }
})

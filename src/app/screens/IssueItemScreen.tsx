import {FlatList, SafeAreaView, ScrollView, StyleSheet, Text, View} from "react-native";
import {Issue} from "../model/issues/Issue";
import {IssueStatus} from "../model/issues/IssueStatus";
import {Category} from "../model/issues/Category";
import {IssueModelInfo} from "../model/issues/IssueModelInfo";
import {Comment} from "../model/issues/Comment";
import CommentItem from "../components/issues/comments/CommentItem";
import CategoryCapsule from "../components/commons/CategoryCapsule";
import {IssueField} from "../model/issues/IssueField";

const issue = new Issue("", "NAN for language Percentage #2735", "CEbbinghaus",
  new Date(), IssueStatus.OPENED, new Category("category"),
  new IssueModelInfo("", "",
    "Lorem ipsum odor amet, consectetuer adipiscing elit. Pellentesque dignissim eget sagittis phasellus in pellentesque egestas. Mi pulvinar finibus aliquet phasellus iaculis dis etiam. Nunc penatibus class tempus lectus nisi placerat quisque. Eros diam conubia quisque torquent taciti netus platea dictum diam. Enim efficitur consequat semper nunc dis quis ipsum."),
  [
    new Comment(new Date(), "samuel", "@robertmclaws Thanks for your feature request. We use #1935 to decide which features or PRs we implement. If enough people support your idea it gets implemented 👍."),
    new Comment(new Date(), "samuel", "The URL is malformed since there is a & missing after count_weight but I would expect it to spit out an error rather than NaN"),
    new Comment(new Date(), "florent", "@CEbbinghaus thanks for bringing this bug to our attention. It would be wonderful if you can create a pull request to resolve this issue. You can read how to contribute in CONTRIBUTING.md."),
    new Comment(new Date(), "admin", "@rickstaa here is my fix for this issue, please see in #2761"),
  ], [
    new IssueField("Is your feature request related to a problem? Please describe.", "" , true, "Nope."),
    new IssueField("Describe the solution you'd like", "", true, "This is a really great tool. Would love a version of it that let me point at a profile name and would return package count and download stats for a NuGet.org profile. For example: https://www.nuget.org/profiles/advancedrei"),
    new IssueField("Describe alternatives you've considered", "", true, "I haven't found anything else that will do this."),
    new IssueField("Additional context", "", true, "I could see this being a really big deal in the Microsoft .NET community."),
  ])

export default function IssueItemScreen() {
  // const route = useRoute<IssueItemRouteProps>()
  //
  // const {
  //   item
  // } = route.params

  return (
    <SafeAreaView style={{flex: 1}}>
      <ScrollView contentContainerStyle={{flexGrow: 1}}>
        <View style={styles.container}>
          <Text style={styles.title}>{issue.title}</Text>

          <View style={styles.details}>
            <View style={styles.authorAndDate}>
              <Text>Par {issue.author}</Text>
              <Text>{issue.createdAt.toLocaleDateString()}</Text>
            </View>

            <View style={styles.capsules}>
              <CategoryCapsule category={issue.category}/>
              <CategoryCapsule category={issue.category}/>
            </View>
          </View>

          <View style={styles.fields}>
            {issue.fields.map((field, index) => (
              <View
                key={index}
                style={{
                  padding: 8,
                  backgroundColor: 'white',
                  gap: 4
                }}>
                <Text style={{fontWeight: '600', fontSize: 16}}>{field.title}</Text>
                <Text>{field.value}</Text>
              </View>
            ))}
          </View>


          <Text style={styles.subtitle}>Commentaires</Text>

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

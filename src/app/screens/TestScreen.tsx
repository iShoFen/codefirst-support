import {SafeAreaView, ScrollView, View} from "react-native";
import IssueListItem from "../components/issues/IssueListItem";
import {Issue} from "../model/issues/Issue";
import {IssueStatus} from "../model/issues/IssueStatus";
import {Category} from "../model/issues/Category";
import {IssueModelInfo} from "../model/issues/IssueModelInfo";
import CommentItem from "../components/issues/comments/CommentItem";
import {Comment} from "../model/issues/Comment";
import CSText from "../components/commons/CSText";

export default function TestScreen() {

  const issue = new Issue("", "Nom du ticket", "author",
    new Date(), IssueStatus.OPENED, new Category("category"),
    new IssueModelInfo("", "",
      "Lorem ipsum odor amet, consectetuer adipiscing elit. Pellentesque dignissim eget sagittis phasellus in pellentesque egestas. Mi pulvinar finibus aliquet phasellus iaculis dis etiam. Nunc penatibus class tempus lectus nisi placerat quisque. Eros diam conubia quisque torquent taciti netus platea dictum diam. Enim efficitur consequat semper nunc dis quis ipsum."),
    [], [])

  const comment = new Comment(new Date(), "author", "Le super commentaire du tickejdsf dsqdjqskl jdkqs jdqklsjd kqdj klqsjdlqjdfj lsdkfj lskjf lskjfklsdj fksdjf lgkjdjgl")

  return (<SafeAreaView>
    <ScrollView>
      <View style={{
        padding: 8,
        gap: 8
      }}>
        <CSText text="TestView" type="h1"/>
        <IssueListItem issue={issue}/>
        <View style={{
          gap: 10
        }}>
          <CommentItem comment={comment}/>
          <CommentItem comment={comment}/>
          <CommentItem comment={comment}/>
          <CommentItem comment={comment}/>
          <CommentItem comment={comment}/>
          <CommentItem comment={comment}/>
          <CommentItem comment={comment}/>
        </View>
      </View>
    </ScrollView>
  </SafeAreaView>)
}

import {StyleSheet, Text, View} from "react-native";
import {Issue} from "../../model/issues/Issue";
import IssueStatusIcon from "./IssueStatusIcon";
import CategoryCapsule from "../commons/CategoryCapsule";
import CommentCounter from "./comments/CommentCounter";

type IssueListItemProps = {
  issue: Issue
}

export default function IssueListItem(props: IssueListItemProps) {
  const {
    issue
  } = props

  return (<View style={styles.container}>
    <IssueStatusIcon status={issue.status} style={styles.icon} />
    <View style={styles.rightPane}>
      <View style={styles.issueHeader}>
        <Text style={styles.issueTitle}>{issue.title}</Text>
        <Text style={styles.issueDate}>{issue.createdAt.toLocaleDateString()}</Text>
      </View>
      <Text>{issue.author}</Text>
      <View style={styles.issueFooter}>
        <CategoryCapsule category={issue.category} />
        <CommentCounter value={issue.comments.length} />
      </View>

    </View>
  </View>)
}

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    gap: 6,
    borderRadius: 10,
    borderColor: 'gray',
    borderWidth: 1,
    paddingHorizontal: 10,
    paddingVertical: 5
  },
  icon: {
    alignSelf: 'center'
  },
  rightPane: {
    flex: 1,
    flexDirection: 'column',
  },
  issueHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center'
  },
  issueFooter: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginTop: 4
  },
  issueTitle: {
    fontSize: 16,
  },
  issueDate: {},
  content: {
    color: 'gray'
  },
})

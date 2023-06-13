import {StyleSheet, TouchableOpacity, View} from "react-native";
import {Issue} from "../../model/issues/Issue";
import IssueStatusIcon from "./IssueStatusIcon";
import CommentCounter from "./comments/CommentCounter";
import CSText from "../commons/CSText";
import CSCapsule from "../commons/CSCapsule";
import {useColors} from "../../themes/hooks";
import MaterialCommunityIcons from "react-native-vector-icons/MaterialCommunityIcons";

type IssueListItemProps = {
  issue: Issue
}

export default function IssueListItem(props: IssueListItemProps) {
  const colors = useColors()

  const {
    issue
  } = props

  return (<View style={[styles.container, {
    backgroundColor: colors.tertiary
  }]}>
    <IssueStatusIcon status={issue.status} style={styles.icon}/>
    <View style={styles.rightPane}>
      <View>
        <View style={styles.issueHeader}>
          <CSText text={`${issue.title} - ${issue.createdAt.toLocaleDateString()}`}/>
          <TouchableOpacity>
            <MaterialCommunityIcons name="dots-horizontal" size={24} color={colors.text}/>
          </TouchableOpacity>
        </View>
        <CSText text={issue.author} type="small"/>
      </View>
      <View style={styles.issueFooter}>
        <CSCapsule text={issue.category.name}/>
        <CommentCounter value={issue.comments.length}/>
      </View>
    </View>
  </View>)
}

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    gap: 8,
    borderRadius: 8,
    padding: 8
  },
  icon: {
    alignSelf: 'center'
  },
  rightPane: {
    flex: 1,
    gap: 8,
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

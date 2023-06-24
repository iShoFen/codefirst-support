import {StyleSheet, TouchableOpacity, View, ViewStyle} from "react-native";
import IssueStatusIcon from "./IssueStatusIcon";
import CSText from "../commons/CSText";
import CSCapsule from "../commons/CSCapsule";
import {useColors} from "../../themes/hooks";
import MaterialCommunityIcons from "react-native-vector-icons/MaterialCommunityIcons";
import {IssueSummary} from "../../model/issues/IssueSummary";

type IssueListItemProps = {
  issue: IssueSummary
  style?: ViewStyle
}

export default function IssueListItem(props: IssueListItemProps) {
  const colors = useColors()

  const {
    issue,
    style
  } = props

  return (<View
    style={[styles.container, {
      backgroundColor: colors.backgroundVariant
    }, style]}>
    <IssueStatusIcon status={issue.status} style={styles.icon}/>
    <View style={styles.rightPane}>
      <View>
        <View style={styles.issueHeader}>
          <CSText text={`${issue.title} - ${issue.createdAt.toLocaleDateString()}`}
                  style={{flex: 1}} numberOfLines={2}/>
          <TouchableOpacity>
            <MaterialCommunityIcons name="dots-horizontal" size={24} color={colors.text}/>
          </TouchableOpacity>
        </View>
        <CSText text={issue.author} type="small"/>
      </View>
      <View style={styles.issueFooter}>
        <CSCapsule text={issue.category.name}/>
        {/*<CommentCounter value={issue.comments?.length ?? 0}/>*/}
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
    flex: 1,
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    gap: 4
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

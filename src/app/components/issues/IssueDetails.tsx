import {StyleSheet, View} from "react-native";
import {Issue} from "../../model/issues/Issue";
import CSText from "../commons/CSText";
import CSCapsule from "../commons/CSCapsule";
import {useColors} from "../../themes/hooks";

type IssueDetailsProps = {
  issue: Issue
}

export default function IssueDetails(props: IssueDetailsProps) {
  const colors = useColors()
  const {issue} = props

  return (
    <View
      style={[
        styles.container,
        {
          backgroundColor: colors.backgroundVariant,
        }
      ]}>
      <CSText text={issue.title} type="h1"/>
      <View style={styles.authorAndDate}>
        <CSText text={`Par ${issue.author}`}/>
        <CSText text={issue.createdAt.toLocaleDateString()}/>
      </View>

      <View style={[styles.capsules]}>
        <CSCapsule text={issue.category.name}/>
        <CSCapsule text={issue.category.name}/>
      </View>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: 'white',
    padding: 8,
    gap: 8,
    borderRadius: 8
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

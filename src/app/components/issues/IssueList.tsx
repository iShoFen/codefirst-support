import {FlatList, StyleSheet, TouchableOpacity, ViewStyle} from "react-native";
import IssueListItem from "./IssueListItem";
import React, {useCallback, useEffect, useMemo} from "react";
import {useAppDispatch, useAppSelector} from "../../redux/hooks";
import {getIssues} from "../../redux/thunk/issueThunk";
import {IssueSummary} from "../../model/issues/IssueSummary";
import {useNavigation} from "@react-navigation/native";
import {IssueStackNavigationProp} from "../../navigation/types/NavigationProp";

type IssueListProps = {
  filter?: string
}

export default function IssueList(props: IssueListProps) {
  const dispatch = useAppDispatch()
  const issues = useAppSelector(state => state.issueReducer.issues)
  const issueLoading = useAppSelector(state => state.issueReducer.loading)
  const issueNavigation = useNavigation<IssueStackNavigationProp>()

  const {
    filter
  } = props

  const resfreshIssues = useCallback(() => {
    const loadIssues = async () => {
      await dispatch(getIssues())
    }
    loadIssues()
  }, [dispatch]);


  useEffect(() => {
    resfreshIssues()
  }, [resfreshIssues]);

  const handleItemPress = useCallback<(item: IssueSummary) => void>((item) => {
    issueNavigation.navigate('Item', {id: item.id, title: item.title})
  }, [issueNavigation])

  const filteredIssues = useMemo(() => {
    if (!filter) return issues

    return issues.filter(value => value.title.includes(filter))
  }, [filter, issues])

  return (
    <FlatList data={filteredIssues}
              style={styles.issues}
              onRefresh={resfreshIssues}
              refreshing={issueLoading}
              renderItem={({item, index}) => {
                const isFirst = index == 0
                const isLast = index == issues.length - 1
                const margin = styles.issueItem.marginVertical
                const itemStyle: ViewStyle = {
                  marginVertical: isFirst || isLast ? undefined : margin,
                  marginTop: isLast ? margin : undefined,
                  marginBottom: isFirst ? margin : undefined,
                }
                return <TouchableOpacity onPress={() => handleItemPress(item)}>
                  <IssueListItem style={itemStyle} issue={item}/>
                </TouchableOpacity>
              }}/>
  )
}

const styles = StyleSheet.create({
  issues: {
    marginVertical: 16
  },
  issueItem: {
    marginVertical: 4
  },
})

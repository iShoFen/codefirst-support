import {FlatList, SafeAreaView, StyleSheet, TouchableOpacity, View, ViewStyle} from "react-native";
import React, {useCallback} from "react";
import {useNavigation} from "@react-navigation/native";
import {IssueStackNavigationProp, SurveyStackNavigationProp,} from "../navigation/types/NavigationProp";
import {useAppDispatch, useAppSelector} from "../redux/hooks";
import {Survey} from "../model/surveys/Survey";
import {Issue} from "../model/issues/Issue";
import IssueListItem from "../components/issues/IssueListItem";
import CSText from "../components/commons/CSText";
import {setSelectedIssue} from "../redux/actions/issueAction";

export default function HomeScreen() {
  const dispatch = useAppDispatch()
  const issueNavigation = useNavigation<IssueStackNavigationProp>()
  const surveyNavigation = useNavigation<SurveyStackNavigationProp>()

  const issues = useAppSelector(state => state.issueReducer.issues)
  const surveys: Survey[] = [
    new Survey("", "Questionnaire 1", new Date(), new Date(), new Date(), "", []),
    new Survey("", "Questionnaire 2", new Date(), new Date(), new Date(), "", []),
    new Survey("", "Questionnaire 3", new Date(), new Date(), new Date(), "", []),
  ]

  // const nounours = useAppSelector(state => state.appReducer.nounours)

  // useEffect(() => {
  //   const loadNounours = async () => {
  //     await dispatch(getNounoursList())
  //   }
  //   loadNounours()
  // }, [dispatch])

  const handleItemPress = useCallback<(item: Issue) => void>((item) => {
    dispatch(setSelectedIssue(item))
    issueNavigation.navigate('Item', {id: item.id, title: item.title})
  }, [])

  return (
    <SafeAreaView style={{flex: 1}}>
      <View style={styles.container}>
        <CSText text="Codefirst Support" type="h1"/>

        <CSText text="Tickets" type="h2" style={styles.issueHeader}/>
        <FlatList data={issues}
                  renderItem={({item, index}) => {
                    const isFirst = index == 0
                    const isLast = index == issues.length - 1
                    const margin = 4
                    const itemStyle: ViewStyle = {
                      marginVertical: isFirst || isLast ? 0 : margin,
                      marginTop: isLast ? margin : 0,
                      marginBottom: isFirst ? margin : 0
                    }
                    return <TouchableOpacity onPress={() => handleItemPress(item)}>
                      <IssueListItem style={itemStyle} issue={item}/>
                    </TouchableOpacity>
                  }}/>
      </View>
    </SafeAreaView>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 10,
    paddingBottom: 0
  },
  issueHeader: {
    marginTop: 8
  },
  titleHeader: {
    marginBottom: 16,
    fontSize: 32,
    fontWeight: 'bold'
  },
  touchableItem: {
    marginVertical: 5
  }
})

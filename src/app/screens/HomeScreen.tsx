import {FlatList, SafeAreaView, StyleSheet, TouchableOpacity, View, ViewStyle} from "react-native";
import React, {useCallback, useEffect} from "react";
import {useNavigation} from "@react-navigation/native";
import {IssueStackNavigationProp, SurveyStackNavigationProp,} from "../navigation/types/NavigationProp";
import {useAppDispatch, useAppSelector} from "../redux/hooks";
import {Survey} from "../model/surveys/Survey";
import IssueListItem from "../components/issues/IssueListItem";
import CSText from "../components/commons/CSText";
import {getIssues} from "../redux/thunk/issueThunk";
import {IssueSummary} from "../model/issues/IssueSummary";
import MaterialCommunityIcons from "react-native-vector-icons/MaterialCommunityIcons";
import {useColors} from "../themes/hooks";

export default function HomeScreen() {
  const dispatch = useAppDispatch()
  const issueNavigation = useNavigation<IssueStackNavigationProp>()
  const surveyNavigation = useNavigation<SurveyStackNavigationProp>()

  const colors = useColors()

  const issues = useAppSelector(state => state.issueReducer.issues)
  const surveys: Survey[] = [
    new Survey("", "Questionnaire 1", new Date(), new Date(), new Date(), "", []),
    new Survey("", "Questionnaire 2", new Date(), new Date(), new Date(), "", []),
    new Survey("", "Questionnaire 3", new Date(), new Date(), new Date(), "", []),
  ]
  const issueLoading = useAppSelector(state => state.issueReducer.loading)

  const handleItemPress = useCallback<(item: IssueSummary) => void>((item) => {
    issueNavigation.navigate('Item', {id: item.id, title: item.title})
  }, [issueNavigation])

  const handleIssuesPress = useCallback(() => {
    issueNavigation.navigate('List')
  }, [issueNavigation]);


  const resfreshIssues = useCallback(() => {
    const loadIssues = async () => {
      await dispatch(getIssues())
    }
    loadIssues()
  }, [dispatch]);


  useEffect(() => {
    resfreshIssues()
  }, [resfreshIssues]);

  return (
    <SafeAreaView style={{flex: 1}}>
      <View style={styles.container}>
        <CSText text="Codefirst Support" type="h1"/>

        <View style={{
          flexDirection: 'row',
          alignItems: 'baseline',
          justifyContent: 'space-between'
        }}>
          <CSText text="Tickets" type="h2" style={styles.issueHeader}/>
          <TouchableOpacity
            style={[styles.arrowButton, {
              backgroundColor: colors.primary,
            }]}
            onPress={handleIssuesPress}>
            <MaterialCommunityIcons name="arrow-right" color={colors.backgroundVariant} size={24}/>
          </TouchableOpacity>
        </View>

        <FlatList data={issues}
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
  issues: {
    marginVertical: 16
  },
  issueItem: {
    marginVertical: 4
  },
  arrowButton: {
    borderRadius: 50,
    padding: 4,
  }
})

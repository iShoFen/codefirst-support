import {SafeAreaView, StyleSheet, TouchableOpacity, View} from "react-native";
import React, {useCallback} from "react";
import {useNavigation} from "@react-navigation/native";
import {IssueStackNavigationProp, SurveyStackNavigationProp,} from "../navigation/types/NavigationProp";
import CSText from "../components/commons/CSText";
import MaterialCommunityIcons from "react-native-vector-icons/MaterialCommunityIcons";
import {useColors} from "../themes/hooks";
import IssueList from "../components/issues/IssueList";

export default function HomeScreen() {
  const issueNavigation = useNavigation<IssueStackNavigationProp>()
  const surveyNavigation = useNavigation<SurveyStackNavigationProp>()

  const colors = useColors()

  const handleIssuesPress = useCallback(() => {
    issueNavigation.navigate('List')
  }, [issueNavigation]);


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

        <IssueList/>
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
  arrowButton: {
    borderRadius: 50,
    padding: 4,
  }
})

import {DefaultSectionT, SectionList, SectionListData, StyleSheet, Text, TouchableOpacity, View} from "react-native";
import React, {useCallback, useEffect} from "react";
import {useNavigation} from "@react-navigation/native";
import {HomeIssueNavigationProp, HomeSurveyNavigationProp,} from "../navigation/types/NavigationProp";
import {useAppDispatch, useAppSelector} from "../redux/hooks";
import {getNounoursList} from "../redux/thunk/nounours";

type SectionId = 'survey' | 'issue'
type NavigationCallbackProps = {
  sectionId: SectionId,
  item: number
}
type SectionItemBase = DefaultSectionT & {
  id: SectionId
}
type SectionItemData = SectionListData<string, SectionItemBase>


export default function HomeScreen() {
  const dispatch = useAppDispatch()
  const issueNavigation = useNavigation<HomeIssueNavigationProp>()
  const surveyNavigation = useNavigation<HomeSurveyNavigationProp>()

  const nounours = useAppSelector(state => state.appReducer.nounours)

  useEffect(() => {
    const loadNounours = async () => {
      await dispatch(getNounoursList())
    }
    loadNounours()
  }, [dispatch])

  const DATA: SectionItemData[] = [
    {
      title: 'Tickets',
      id: 'issue',
      data: ['Pizza', 'Burger', 'Risotto', 'Pizza', 'Burger', 'Risotto', 'Pizza', 'Burger', 'Risotto', 'Pizza', 'Burger', 'Risotto', 'Pizza', 'Burger', 'Risotto', 'Pizza', 'Burger', 'Risotto', 'Pizza', 'Burger', 'Risotto', 'Pizza', 'Burger', 'Risotto'],
    },
    {
      title: 'Questionnaires',
      id: 'survey',
      data: ['French Fries', 'Onion Rings', 'Fried Shrimps'],
      renderItem: ({item}) => {
        return (<View><Text style={{color: 'red'}}>{item}</Text></View>)
      }
    }
  ]

  const handleItemPress = useCallback(({sectionId}: NavigationCallbackProps) => {
    switch (sectionId) {
      case "survey":
        surveyNavigation.navigate('Item', {item: ''})
        break
      case "issue":
        issueNavigation.navigate('Item', {item: ''})
        break
    }
  }, [])


  return (
    <View style={styles.container}>
      <Text style={styles.titleHeader}>Codefirst Support</Text>

      <SectionList sections={DATA}
                   renderSectionHeader={({section}) => (<Text style={{fontSize: 40}}>{section.title}</Text>)}
                   renderItem={({item, section}) => (<View>
                     <TouchableOpacity onPress={() => handleItemPress({
                       sectionId: section.id,
                       item: item.length
                     })}>
                       <Text>{item}</Text>
                     </TouchableOpacity>
                   </View>)}
      />
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 10,
    paddingBottom: 0
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

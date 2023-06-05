import {
  DefaultSectionT,
  SectionList,
  SectionListData,
  StyleSheet,
  Text,
  TouchableHighlight,
  TouchableOpacity,
  View
} from "react-native";
import React, {useCallback, useEffect} from "react";
import {useNavigation} from "@react-navigation/native";
import {TicketListNavigationProp} from "../navigation/types/NavigationProp";
import {useAppDispatch, useAppSelector} from "../redux/hooks";
import {getNounoursList} from "../redux/thunk/nounours";
import {Nounours} from "../model/Nounours";

type SectionId = 'survey' | 'ticket'
type NavigationCallbackProps = {
  sectionId: SectionId,
  navigationKey: string
}
type SectionItemBase = DefaultSectionT & NavigationCallbackProps
type SectionItemData = SectionListData<string, SectionItemBase>





export default function HomeScreen() {
  const dispatch = useAppDispatch()
  const navigation = useNavigation<TicketListNavigationProp>()

  const nounours = useAppSelector(state => state.appReducer.nounours)

  const handleNavigate = useCallback((item: Nounours) => {
    navigation.navigate('Item', {
      nounours: item
    })
  }, [])

  useEffect(() => {
    const loadNounours = async () => {
      await dispatch(getNounoursList())
    }
    loadNounours()
  }, [dispatch])

  const DATA: SectionItemData[] = [
    {
      title: 'Tickets',
      sectionId: 'ticket',
      navigationKey: 'ticket',
      data: ['Pizza', 'Burger', 'Risotto', 'Pizza', 'Burger', 'Risotto', 'Pizza', 'Burger', 'Risotto', 'Pizza', 'Burger', 'Risotto', 'Pizza', 'Burger', 'Risotto', 'Pizza', 'Burger', 'Risotto', 'Pizza', 'Burger', 'Risotto', 'Pizza', 'Burger', 'Risotto'],
    },
    {
      title: 'Questionnaires',
      sectionId: 'survey',
      navigationKey: 'ticket',
      data: ['French Fries', 'Onion Rings', 'Fried Shrimps'],
      renderItem: ({item}) => {
        return (<View><Text style={{color: 'red'}}>{item}</Text></View>)
      }
    }
  ]

  const handleItemPress = useCallback(({sectionId, navigationKey}: NavigationCallbackProps) => {
    navigation.navigate('Item', {
      nounours: new Nounours(navigationKey, 0, 0, "")
    })
  }, [])


  return (
    <View style={styles.container}>
      <Text style={styles.titleHeader}>Codefirst Support</Text>

      <SectionList sections={DATA}
                   renderSectionHeader={({section}) => (<Text style={{fontSize: 40}}>{section.title}</Text>)}
                   renderItem={({item, section}) => (<View>
                     <TouchableOpacity onPress={() => handleItemPress({
                       navigationKey: section.navigationKey,
                       sectionId: section.sectionId
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

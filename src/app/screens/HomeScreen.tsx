import {Button, FlatList, StyleSheet, Text, TouchableHighlight, TouchableOpacity, View} from "react-native";
import React, {useCallback, useEffect} from "react";
import {useNavigation} from "@react-navigation/native";
import {TicketListNavigationProp} from "../navigation/types/NavigationProp";
import NounoursItem from "../components/NounoursItem";
import {useAppDispatch, useAppSelector} from "../redux/hooks";
import {getNounoursList} from "../redux/thunk/nounours";
import {Nounours} from "../model/Nounours";


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

  return (
    <View style={styles.container}>
      <Text>HomeScreen</Text>
      <FlatList data={nounours}
                renderItem={({item, index}) => (
                  <TouchableOpacity onPress={() => handleNavigate(item)}
                                    style={styles.touchableItem}>
                    <NounoursItem nounours={item} index={index}/>
                  </TouchableOpacity>
                )}
      />
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 10,
  },
  touchableItem: {
    marginVertical: 5
  }
})

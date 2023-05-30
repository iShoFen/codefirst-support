import {Button, Text, View} from "react-native";
import React, {useCallback} from "react";
import {useNavigation} from "@react-navigation/native";
import {TicketListNavigationProp} from "../navigation/types/NavigationProp";


export default function HomeScreen() {
  const navigation = useNavigation<TicketListNavigationProp>()

  const handleNavigate = useCallback(() => {
    navigation.navigate('Item', {id: Math.random() * 20})
  }, [])

  return (
    <View>
      <Text>HomeScreen</Text>
      <Button title="Navigate to detail" onPress={handleNavigate}/>
    </View>
  )
}

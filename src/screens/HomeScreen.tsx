import {Button, Text, View} from "react-native";
import {useCallback} from "react";
import {useNavigation} from "@react-navigation/native";


export default function HomeScreen() {
  const navigation = useNavigation()
  const handleNavigate = useCallback(() => {
    navigation.navigate('Item')
  }, [])

  return (
    <View>
      <Text>HomeScreen</Text>
      <Button title="Navigate to detail" onPress={handleNavigate} />
    </View>
  )
}

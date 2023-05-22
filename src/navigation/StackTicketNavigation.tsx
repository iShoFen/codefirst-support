import {createStackNavigator} from "@react-navigation/stack";
import HomeScreen from "../screens/HomeScreen";
import TicketItemScreen from "../screens/TicketItemScreen";

export default function StackTicketNavigation() {
  const Stack = createStackNavigator()

  return (
    <Stack.Navigator>
      <Stack.Screen name="List" component={HomeScreen}/>
      <Stack.Screen name="Item" component={TicketItemScreen}/>
    </Stack.Navigator>
  )
}

import {createStackNavigator} from "@react-navigation/stack";
import HomeScreen from "../screens/HomeScreen";
import TicketItemScreen from "../screens/TicketItemScreen";

export default function StackTicketNavigation() {
  const Stack = createStackNavigator()

  return (
    <Stack.Navigator>
      <Stack.Screen name="List" navigationKey="TicketList" component={HomeScreen}/>
      <Stack.Screen name="Item" navigationKey="TicketItem" component={TicketItemScreen}/>
    </Stack.Navigator>
  )
}

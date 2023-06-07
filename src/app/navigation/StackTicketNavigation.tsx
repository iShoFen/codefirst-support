import {createStackNavigator} from "@react-navigation/stack";
import HomeScreen from "../screens/HomeScreen";
import IssueItemScreen from "../screens/IssueItemScreen";

export default function StackTicketNavigation() {
  const Stack = createStackNavigator<TicketParamList>()

  return (
    <Stack.Navigator>
      <Stack.Screen name="List" component={HomeScreen}/>
      <Stack.Screen name="Item" component={IssueItemScreen}/>
    </Stack.Navigator>
  )
}

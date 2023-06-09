import {createStackNavigator} from "@react-navigation/stack";
import HomeScreen from "../screens/HomeScreen";
import IssueItemScreen from "../screens/IssueItemScreen";
import {IssueParamList} from "./types/ParamList";

export default function StackIssueNavigation() {
  const Stack = createStackNavigator<IssueParamList>()

  return (
    <Stack.Navigator initialRouteName="List">
      <Stack.Screen name="List" component={HomeScreen}/>
      <Stack.Screen name="Item" component={IssueItemScreen}/>
    </Stack.Navigator>
  )
}

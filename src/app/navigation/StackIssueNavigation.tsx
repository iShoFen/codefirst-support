import {createStackNavigator} from "@react-navigation/stack";
import HomeScreen from "../screens/HomeScreen";
import IssueItemScreen from "../screens/issues/IssueItemScreen";
import {IssueParamList} from "./types/ParamList";
import {useColors} from "../themes/hooks";
import HomeIssueScreen from "../screens/issues/HomeIssueScreen";
import CreateCommentScreen from "../screens/issues/CreateCommentScreen";

export default function StackIssueNavigation() {
  const Stack = createStackNavigator<IssueParamList>()
  const colors = useColors()

  return (
    <Stack.Navigator
      initialRouteName="HomeTickets"
      screenOptions={{
        headerBackTitle: 'Retour',
        headerStyle: {
          backgroundColor: colors.background,
        },
        headerTitleContainerStyle: {
          padding: 8,
        },
      }}>
      <Stack.Screen
        name="HomeTickets"
        component={HomeScreen}
        options={{
          headerShown: false
        }}/>
      <Stack.Screen
        name="List"
        component={HomeIssueScreen}
        options={{
          title: 'Tickets'
        }}/>
      <Stack.Screen
        name="Item"
        component={IssueItemScreen}
        options={({route}) => ({
          title: route.params.title,
        })}/>
      <Stack.Screen
        name="CreateComment"
        component={CreateCommentScreen}
      />
    </Stack.Navigator>
  )
}

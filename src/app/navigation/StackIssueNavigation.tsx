import {createStackNavigator} from "@react-navigation/stack";
import HomeScreen from "../screens/HomeScreen";
import IssueItemScreen from "../screens/IssueItemScreen";
import {IssueParamList} from "./types/ParamList";
import {useColors} from "../themes/hooks";

export default function StackIssueNavigation() {
  const Stack = createStackNavigator<IssueParamList>()
  const colors = useColors()

  return (
    <Stack.Navigator initialRouteName="List" screenOptions={{
      headerBackTitle: 'Retour',
      headerStyle: {
        backgroundColor: colors.background,
      },
      headerTitleContainerStyle: {
        padding: 8,
      },
    }}>
      <Stack.Screen name="List" component={HomeScreen}
                    options={{
                      headerShown: false
                    }}/>
      <Stack.Screen name="Item" component={IssueItemScreen}
                    options={({route}) => ({
                      title: route.params.title
                    })}/>
    </Stack.Navigator>
  )
}

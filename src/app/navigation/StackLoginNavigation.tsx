import {createStackNavigator} from "@react-navigation/stack";
import {LoginParamList} from "./types/ParamList";
import LoginScreen from "../screens/LoginScreen";
import BottomBarNavigation from "./BottomBarNavigation";

export default function StackLoginNavigation() {
  const Stack = createStackNavigator<LoginParamList>()

  return (
    <Stack.Navigator
      initialRouteName={'Login'}
      screenOptions={{
        headerShown: false
      }}
    >
      <Stack.Screen
        name="Login"
        component={LoginScreen}
      />
      <Stack.Screen
        name="Logged"
        component={BottomBarNavigation}
      />
    </Stack.Navigator>
  )
}

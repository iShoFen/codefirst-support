import {NavigationContainer} from "@react-navigation/native";
import {createBottomTabNavigator} from "@react-navigation/bottom-tabs";
import MaterialCommunityIcons from 'react-native-vector-icons/MaterialCommunityIcons';
import SettingsScreen from "../screens/SettingsScreen";
import StackIssueNavigation from "./StackIssueNavigation";
import {BottomBarParamList} from "./types/ParamList";
import HomeAddScreen from "../screens/HomeAddScreen";
import {useColors} from "../themes/hooks/useColors";
import {useNavigationTheme} from "../themes/hooks";


export default function BottomBarNavigation() {
  const BottomTabNavigator = createBottomTabNavigator<BottomBarParamList>()
  const colors = useColors()
  const navigationTheme = useNavigationTheme()

  return (
    <NavigationContainer theme={navigationTheme}>
      <BottomTabNavigator.Navigator initialRouteName="Home"
                                    screenOptions={{
                                      headerShown: false,
                                      tabBarActiveTintColor: colors.primary
                                    }}>
        <BottomTabNavigator.Screen
          name="Home"
          component={StackIssueNavigation}
          options={{
            tabBarIcon: ({color, size}) => (
              <MaterialCommunityIcons name="home" color={color} size={size}/>
            ),
            tabBarLabel: 'Accueil'
          }}/>
        <BottomTabNavigator.Screen
          name="Add"
          component={HomeAddScreen}
          options={{
            tabBarIcon: ({color, size}) => (
              <MaterialCommunityIcons name="plus-circle" color={color} size={size}/>
            ),
            tabBarLabel: 'Créer'
          }}/>
        <BottomTabNavigator.Screen
          name="Settings"
          component={SettingsScreen}
          options={{
            tabBarIcon: ({color, size}) => (
              <MaterialCommunityIcons name="cog" color={color} size={size}/>
            ),
            tabBarLabel: 'Paramètres'
          }}/>
      {/*  <BottomTabNavigator.Screen*/}
      {/*    name="Test"*/}
      {/*    component={HomeIssueScreen}*/}
      {/*    options={{*/}
      {/*      tabBarIcon: ({color, size}) => (*/}
      {/*        <MaterialCommunityIcons name="test-tube" color={color} size={size}/>*/}
      {/*      ),*/}
      {/*      tabBarLabel: 'Test',*/}
      {/*    }}/>*/}
      </BottomTabNavigator.Navigator>
    </NavigationContainer>
  )
}

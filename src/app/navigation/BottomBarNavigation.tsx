import {NavigationContainer} from "@react-navigation/native";
import {createBottomTabNavigator} from "@react-navigation/bottom-tabs";
import MaterialCommunityIcons from 'react-native-vector-icons/MaterialCommunityIcons';
import SettingsScreen from "../screens/SettingsScreen";
import AddTicketScreen from "../screens/AddTicketScreen";
import StackTicketNavigation from "./StackTicketNavigation";


export default function BottomBarNavigation() {
  const BottomTabNavigator = createBottomTabNavigator<BottomBarParamList>()

  return (
    <NavigationContainer>
      <BottomTabNavigator.Navigator initialRouteName="Home"
                                    screenOptions={{headerShown: false}}>
        <BottomTabNavigator.Screen
          name="Home"
          component={StackTicketNavigation}
          options={{
            tabBarIcon: ({color, size}) => (
              <MaterialCommunityIcons name="home" color={color} size={size}/>
            ),
            tabBarLabel: 'Accueil'
          }}/>
        <BottomTabNavigator.Screen
          name="AddTicket"
          component={AddTicketScreen}
          options={{
            tabBarIcon: ({color, size}) => (
                <MaterialCommunityIcons name="plus-circle" color={color} size={size}/>
            ),
            tabBarLabel: 'Nouveau ticket'
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
      </BottomTabNavigator.Navigator>
    </NavigationContainer>
  )
}

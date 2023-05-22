import {NavigationContainer} from "@react-navigation/native";
import {createBottomTabNavigator} from "@react-navigation/bottom-tabs";
import MaterialCommunityIcons from 'react-native-vector-icons/MaterialCommunityIcons';
import SettingsScreen from "../screens/SettingsScreen";
import AddTicketScreen from "../screens/AddTicketScreen";
import StackTicketNavigation from "./StackTicketNavigation";
import {Text, View} from "react-native";


export default function BottomBarNavigation() {
  const BottomTabNavigator = createBottomTabNavigator<BottomBarParamList>()

  return (
    <NavigationContainer>
      <BottomTabNavigator.Navigator initialRouteName="Home"
                                    screenOptions={{headerShown: false}}>
        <BottomTabNavigator.Screen
          name="Home" component={StackTicketNavigation}
          options={{
            tabBarIcon: ({color, size}) => (
              <MaterialCommunityIcons name="home" color={color} size={size}/>
            )
          }}/>
        <BottomTabNavigator.Screen
          name="AddTicket" component={AddTicketScreen}
          options={{
            tabBarIcon: ({color, size}) => (
              <View style={{
                bottom: 20,
                backgroundColor: 'darksalmon',
                width: 64,
                height: 64,
                padding: 20,
                borderRadius: 100
              }
              }>
                <MaterialCommunityIcons name="plus" color={color} size={size}/>
              </View>
            ),
            tabBarLabel: ''
          }}/>
        <BottomTabNavigator.Screen
          name="Settings" component={SettingsScreen}
          options={{
            tabBarIcon: ({color, size}) => (
              <MaterialCommunityIcons name="cog" color={color} size={size}/>
            )
          }}/>
      </BottomTabNavigator.Navigator>
    </NavigationContainer>
  )
}

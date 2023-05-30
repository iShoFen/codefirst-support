import {BottomTabNavigationProp} from "@react-navigation/bottom-tabs";
import {StackNavigationProp} from "@react-navigation/stack";


export type HomeScreenNavigationProps = BottomTabNavigationProp<BottomBarParamList, 'Home'>
export type AddTicketScreenNavigationProps = BottomTabNavigationProp<BottomBarParamList, 'AddTicket'>
export type SettingsScreenNavigationProps = BottomTabNavigationProp<BottomBarParamList, 'Settings'>

export type TicketListNavigationProp = StackNavigationProp<TicketParamList, 'List'>
export type TicketItemNavigationProp = StackNavigationProp<TicketParamList, 'Item'>

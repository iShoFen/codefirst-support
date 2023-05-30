import {RouteProp} from "@react-navigation/native";

export type HomeRouteProps = RouteProp<BottomBarParamList, 'Home'>
export type AddTicketRouteProps = RouteProp<BottomBarParamList, 'AddTicket'>
export type SettingsRouteProps = RouteProp<BottomBarParamList, 'Settings'>

export type TicketListRouteProps = RouteProp<TicketParamList, 'List'>
export type TicketItemRouteProps = RouteProp<TicketParamList, 'Item'>

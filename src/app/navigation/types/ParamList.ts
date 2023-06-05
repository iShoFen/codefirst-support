import {Nounours} from "../../model/Nounours";

export type BottomBarParamList = {
  Home: undefined
  HomeTicket: undefined
  Add: undefined
  HomeSurvey: undefined
  Settings: undefined
}

export type TicketParamList = {
  List: undefined
  Item: { nounours: Nounours }
}

import {BottomTabNavigationProp} from "@react-navigation/bottom-tabs";
import {StackNavigationProp} from "@react-navigation/stack";
import {BottomBarParamList, CreateParamList, IssueParamList, SurveyParamList} from "./ParamList";

export type BottomNavigationProp = BottomTabNavigationProp<BottomBarParamList>

export type IssueStackNavigationProp = StackNavigationProp<IssueParamList>
export type SurveyStackNavigationProp = StackNavigationProp<SurveyParamList>

export type CreateStackNavigationProp = StackNavigationProp<CreateParamList>

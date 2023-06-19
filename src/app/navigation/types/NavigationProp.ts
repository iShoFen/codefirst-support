import {BottomTabNavigationProp} from "@react-navigation/bottom-tabs";
import {StackNavigationProp} from "@react-navigation/stack";
import {BottomBarParamList, CreateParamList, HomeParamList, IssueParamList, SurveyParamList} from "./ParamList";

export type IssueStackNavigationProp = StackNavigationProp<IssueParamList>
export type SurveyStackNavigationProp = StackNavigationProp<SurveyParamList>

export type CreateStackNavigationProp = StackNavigationProp<CreateParamList>

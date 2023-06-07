import {BottomTabNavigationProp} from "@react-navigation/bottom-tabs";
import {StackNavigationProp} from "@react-navigation/stack";
import {BottomBarParamList, HomeParamList, IssueParamList, SurveyParamList} from "./ParamList";


export type HomeScreenNavigationProps = BottomTabNavigationProp<BottomBarParamList, 'Home'>
export type AddScreenNavigationProps = BottomTabNavigationProp<BottomBarParamList, 'Add'>
export type SettingsScreenNavigationProps = BottomTabNavigationProp<BottomBarParamList, 'Settings'>

export type HomeIssueNavigationProp = StackNavigationProp<IssueParamList>
export type HomeSurveyNavigationProp = StackNavigationProp<SurveyParamList>

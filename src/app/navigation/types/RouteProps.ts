import {RouteProp} from "@react-navigation/native";
import {BottomBarParamList, HomeParamList, IssueParamList, SurveyParamList} from "./ParamList";

export type HomeRouteProps = RouteProp<BottomBarParamList, 'Home'>
export type AddRouteProps = RouteProp<BottomBarParamList, 'Add'>
export type SettingsRouteProps = RouteProp<BottomBarParamList, 'Settings'>

export type IssueRouteProps = RouteProp<HomeParamList, 'Issue'>
export type SurveyRouteProps = RouteProp<HomeParamList, 'Survey'>

export type IssueListRouteProps = RouteProp<IssueParamList, 'List'>
export type IssueItemRouteProps = RouteProp<IssueParamList, 'Item'>
export type CreateCommentRouteProps = RouteProp<IssueParamList, 'CreateComment'>

export type SurveyListRouteProps = RouteProp<SurveyParamList, 'List'>
export type SurveyItemRouteProps = RouteProp<SurveyParamList, 'Item'>


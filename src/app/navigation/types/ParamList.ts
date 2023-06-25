import {Issue} from "../../model/issues/Issue";
import {Survey} from "../../model/surveys/Survey";

export type BottomBarParamList = {
  Home: undefined
  Add: undefined
  Settings: undefined
  Test: undefined
}

export type HomeParamList = {
  Issue: undefined
  Survey: undefined
}

type FeatureParamList<T> = {
  List: undefined
  Item: { id: string, title: string }
}

export type IssueParamList = FeatureParamList<Issue> & {
  HomeTickets: undefined
  CreateComment: {issueId: string}
}
export type SurveyParamList = FeatureParamList<Survey>

export type CreateParamList = {
  HomeCreate: undefined
  CreateIssue: undefined
  CreateSurvey: undefined
}

export type LoginParamList = {
  Login: undefined
  Logged: undefined
}

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
  Item: {item: T}
}

export type IssueParamList = FeatureParamList<Issue>
export type SurveyParamList = FeatureParamList<Survey>

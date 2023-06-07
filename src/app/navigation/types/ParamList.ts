import {Nounours} from "../../model/Nounours";

export type BottomBarParamList = {
  Home: undefined
  Add: undefined
  Settings: undefined
}

export type HomeParamList = {
  Issue: undefined
  Survey: undefined
}

type FeatureParamList<T> = {
  List: undefined
  Item: {item: T}
}

export type IssueParamList = FeatureParamList<string>
export type SurveyParamList = FeatureParamList<string>

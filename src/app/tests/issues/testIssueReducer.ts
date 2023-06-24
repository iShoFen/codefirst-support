import issueReducer, {IssueReducerState} from "../../redux/reducers/issueReducer";
import {ISSUE_LIST, ISSUE_SELECTED} from "../../data/stub";

const initialState: IssueReducerState = {
  issues: ISSUE_LIST,
  loading: false,
  selectedIssue: ISSUE_SELECTED
}

// @ts-ignore
export default testIssueReducer = (state = initialState, action) => {
  return issueReducer(initialState, action);
}

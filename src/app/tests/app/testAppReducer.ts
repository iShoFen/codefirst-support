import appReducer, {AppReducerState} from "../../redux/reducers/appReducer";
import {THEMES} from "../../data/themes";

const initialState: AppReducerState = {
  theme: THEMES[2],
  loggedUser: undefined
}

// @ts-ignore
export default testAppReducer = (state = initialState, action) => {
  return appReducer(initialState, action);
}

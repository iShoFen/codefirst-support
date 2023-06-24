import userReducer, {UserReducerState} from "../../redux/reducers/userReducer";

const initialState: UserReducerState = {
  theme: "system"
}

// @ts-ignore
export default testUserReducer = (state = initialState, action) => {
  return userReducer(initialState, action);
}

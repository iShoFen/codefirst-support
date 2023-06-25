import store from "./redux/store";
import {Provider} from "react-redux";
import RootScreen from "./screens/RootScreen";

export default function App() {
  return (
    <Provider store={store}>
      <RootScreen/>
    </Provider>
  );
}

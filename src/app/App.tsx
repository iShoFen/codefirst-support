import BottomBarNavigation from "./navigation/BottomBarNavigation";
import store from "./redux/store";
import {Provider} from "react-redux";

export default function App() {
  return (
    <Provider store={store}>
      <BottomBarNavigation/>
    </Provider>
  );
}

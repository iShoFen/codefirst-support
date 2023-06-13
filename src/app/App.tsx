import BottomBarNavigation from "./navigation/BottomBarNavigation";
import store from "./redux/store";
import {Provider} from "react-redux";
import {useColors} from "./themes/hooks/useColors";

export default function App() {
  const colors = useColors()

  return (
    <Provider store={store}>
      <BottomBarNavigation/>
    </Provider>
  );
}

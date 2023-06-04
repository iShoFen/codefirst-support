import {StyleSheet} from 'react-native';
//import {Provider as PaperProvider} from 'react-native-paper'
import BottomBarNavigation from "./navigation/BottomBarNavigation";
import store from "./redux/store";
import {Provider} from "react-redux";
import {PaperProvider} from "react-native-paper";

export default function App() {
  return (
    <PaperProvider>
      <Provider store={store}>
        <BottomBarNavigation/>
      </Provider>
    </PaperProvider>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});

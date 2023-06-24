import {SafeAreaView, ScrollView, StyleSheet, Text, TouchableOpacity, View} from "react-native";
import SearchBar from "../../components/commons/SearchBar";
import CSText from "../../components/commons/CSText";
import IssueList from "../../components/issues/IssueList";

export default function HomeIssueScreen() {

  return (<SafeAreaView style={{flex: 1}}>
    <ScrollView contentContainerStyle={{flex: 1}}>
      <View style={styles.container}>
        <CSText text="Tickets" type="h1"/>

        <SearchBar/>

        {/*<View style={styles.filters}>*/}
        {/*  <TouchableOpacity style={styles.categoryButton}>*/}
        {/*    <Text>Catégorie</Text>*/}
        {/*  </TouchableOpacity>*/}
        {/*  <TouchableOpacity style={styles.categoryButton}>*/}
        {/*    <Text>Catégorie</Text>*/}
        {/*  </TouchableOpacity>*/}
        {/*</View>*/}

        <ScrollView horizontal={true} contentContainerStyle={{flex: 1}}>
          <IssueList/>
        </ScrollView>
      </View>
    </ScrollView>
  </SafeAreaView>)
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 8
  },
  title: {
    fontSize: 32,
    fontWeight: 'bold'
  },
  categoryButton: {
    padding: 8,
    backgroundColor: 'white',
    borderRadius: 8,
    shadowRadius: 10,
    shadowColor: 'black',
    shadowOpacity: 0.1,
    shadowOffset: {
      width: 0,
      height: 8
    }
  },
  filters: {
    flexDirection: 'row',
    justifyContent: 'space-evenly'
  }
})

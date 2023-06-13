import {SafeAreaView, ScrollView, StyleSheet, Text, TouchableOpacity, View} from "react-native";
import SearchBar from "../../components/commons/SearchBar";

export default function HomeIssueScreen() {

  return (<SafeAreaView style={{flex: 1}}>
    <ScrollView contentContainerStyle={{flex: 1}}>
      <View style={styles.container}>
        <Text style={styles.title}>Tickets</Text>

        <SearchBar style={{margin: 10}}/>

        <View style={styles.filters}>
          <TouchableOpacity style={styles.categoryButton}>
            <Text>Catégorie</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.categoryButton}>
            <Text>Catégorie</Text>
          </TouchableOpacity>
        </View>
      </View>
    </ScrollView>
  </SafeAreaView>)
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  title: {
    fontSize: 32,
    padding: 10,
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

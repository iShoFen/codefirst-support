import {FlatList, SafeAreaView, ScrollView, StyleSheet, Text, TouchableOpacity, View, ViewStyle} from "react-native";
import SearchBar from "../../components/commons/SearchBar";
import {useAppSelector} from "../../redux/hooks";
import IssueListItem from "../../components/issues/IssueListItem";
import CSText from "../../components/commons/CSText";

export default function HomeIssueScreen() {
  const issues = useAppSelector(state => state.issueReducer.issues)

  return (<SafeAreaView style={{flex: 1}}>
    <ScrollView contentContainerStyle={{flex: 1}}>
      <View style={styles.container}>
        <CSText text="Tickets" type="h1" />

        <SearchBar/>

        <View style={styles.filters}>
          <TouchableOpacity style={styles.categoryButton}>
            <Text>Catégorie</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.categoryButton}>
            <Text>Catégorie</Text>
          </TouchableOpacity>
        </View>

        <ScrollView horizontal={true} contentContainerStyle={{flex: 1}}>
          <FlatList data={issues}
                    keyExtractor={item => item.id}
                    renderItem={({item, index}) => {
                      const isFirst = index == 0
                      const isLast = index == issues.length - 1
                      const margin = 4
                      const itemStyle: ViewStyle = {
                        marginVertical: isFirst || isLast ? 0 : margin,
                        marginTop: isLast ? margin : 0,
                        marginBottom: isFirst ? margin : 0
                      }
                      return <IssueListItem style={itemStyle} issue={item}/>
                    }}/>
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

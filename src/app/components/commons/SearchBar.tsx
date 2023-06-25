import {StyleSheet, TextInput, TouchableOpacity, View, ViewStyle} from "react-native";
import MaterialCommunityIcons from "react-native-vector-icons/MaterialCommunityIcons";

type SearchBarProps = {
  style?: ViewStyle,
}

export default function SearchBar(props: SearchBarProps) {

  const {
    style
  } = props

  const handleSearch = () => {

  }

  return (<View style={[styles.container, style]}>
    <TextInput style={styles.input} placeholder="Rechercher" onSubmitEditing={handleSearch}/>
    <TouchableOpacity onPress={handleSearch}>
      <MaterialCommunityIcons name="magnify" size={24} color="gray"/>
    </TouchableOpacity>
  </View>)
}

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    paddingVertical: 6,
    paddingHorizontal: 8,
    borderWidth: 1,
    borderRadius: 8,
    backgroundColor: 'white',
    borderColor: 'gray'
  },
  input: {
    flex: 1
  }
})

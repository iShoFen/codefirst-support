import {StyleSheet, Text, View, ViewStyle} from "react-native";
import {Category} from "../../model/issues/Category";

type CategoryCapsuleProps = {
  category: Category
  style?: ViewStyle
}

export default function CategoryCapsule(props: CategoryCapsuleProps) {

  const {
    category: {name}
  } = props

  return (<View style={styles.container}>
    <Text style={styles.text}>{name}</Text>
  </View>)
}

const styles = StyleSheet.create({
  container: {
    borderRadius: 14,
    borderWidth: 1,
    borderColor: 'green',
    color: 'white',
    backgroundColor: 'green',
    padding: 4,
    alignSelf: 'flex-start',
  },
  text: {
    color: 'white'
  }
})

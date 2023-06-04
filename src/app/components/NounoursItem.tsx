import {Image, StyleSheet, Text, TouchableHighlight, View} from "react-native";
import {Nounours} from "../model/Nounours";

type NounoursItemProps = {
  nounours: Nounours,
  index: number
}

export default function NounoursItem(props: NounoursItemProps) {
  const {
    nounours,
    index
  } = props

  return (<View style={styles.container}>
      <Text>{nounours.name}</Text>
      <Text>{nounours.nbPoils}</Text>
  </View>)
}

const styles = StyleSheet.create({
  container: {
    padding: 10,
    borderRadius: 10,
    borderStyle: 'solid',
    borderWidth: 1
  }
})

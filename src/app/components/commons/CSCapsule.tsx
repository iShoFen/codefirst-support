import {StyleSheet, Text, TextStyle, View, ViewStyle} from "react-native";


type CSCapsuleProps = {
  text: string
  containerStyle?: ViewStyle
  textStyle?: TextStyle
  testID?: string
}

export default function CSCapsule(props: CSCapsuleProps) {

  const {
    text,
    containerStyle,
    textStyle,
    testID
  } = props

  return (<View style={[styles.container, containerStyle]}>
    <Text style={[styles.text, textStyle]}
    testID={testID}>{text}</Text>
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

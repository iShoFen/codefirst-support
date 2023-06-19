import {StyleSheet, TextInput, TextStyle, View, ViewStyle} from "react-native";
import CSText from "./CSText";
import {useColors} from "../../themes/hooks";

type CSFieldProps = {
  label?: string
  value?: string
  placeholder?: string
  enabled?: boolean,
  multiLine?: boolean
}

export default function CSField(props: CSFieldProps) {

  const {
    label,
    value,
    placeholder,
    enabled = true,
    multiLine
  } = props

  const colors = useColors()

  const customStyles: TextStyle = {
    minHeight: multiLine ? 60 : undefined,
    backgroundColor: enabled ? colors.white : colors.textVariant,
  }

  return (<View>
    {label && <CSText text={label}/>}
    <TextInput editable={enabled}
               value={value}
               style={[styles.container, customStyles]}
               placeholder={placeholder}
               multiline={multiLine}/>
  </View>)
}

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    paddingVertical: 12,
    paddingHorizontal: 8,
    borderWidth: 1,
    borderRadius: 8,
    borderColor: 'gray'
  },
  input: {
    flex: 1
  }
})

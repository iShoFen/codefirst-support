import {StyleSheet, TextInput, TextStyle, View, ViewStyle} from "react-native";
import CSText from "./CSText";
import {useColors} from "../../themes/hooks";

type CSFieldProps = {
  label?: string
  value?: string
  placeholder?: string
  enabled?: boolean,
  multiLine?: boolean
  onChange?: (text: string) => void
  required?: boolean
}

export default function CSField(props: CSFieldProps) {

  const {
    label,
    value,
    placeholder,
    enabled = true,
    multiLine,
    onChange,
    required
  } = props

  const colors = useColors()

  const customStyles: TextStyle = {
    minHeight: multiLine ? 60 : undefined,
    backgroundColor: enabled ? colors.white : colors.textVariant,
  }

  return (<View>
    <View style={{justifyContent: 'space-between', flexDirection: 'row'}}>
      {label && <CSText text={label}/>}
      {required && <CSText text="Requis" type="small" color="red"/>}
    </View>
    <TextInput editable={enabled}
               value={value}
               style={[styles.container, customStyles]}
               placeholder={placeholder}
               onChangeText={onChange}
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

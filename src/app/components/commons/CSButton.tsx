import {
  GestureResponderEvent,
  StyleProp,
  StyleSheet,
  TouchableNativeFeedback,
  TouchableOpacity,
  ViewStyle
} from "react-native";
import MaterialCommunityIcons from "react-native-vector-icons/MaterialCommunityIcons";
import React from "react";
import {useColors} from "../../themes/hooks";
import CSText from "./CSText";

type CSButtonProps = {
  style?: StyleProp<ViewStyle>
  onPress?: ((event: GestureResponderEvent) => void)
  text?: string
  icon?: string
  iconSize?: number
}

export default function CSButton(props: CSButtonProps) {
  const colors = useColors()

  const {
    onPress,
    style,
    text,
    icon,
    iconSize = 24
  } = props

  return (
    <TouchableOpacity
      style={[
        styles.container,
        {
          backgroundColor: colors.primary,
        },
        style
      ]}
      onPress={onPress}>
      {text && <CSText
        text={text}
        style={{
          color: colors.black
        }}/>}
      {icon && <MaterialCommunityIcons
        name={icon}
        color={colors.backgroundVariant}
        size={iconSize}/>}
    </TouchableOpacity>
  )
}

const styles = StyleSheet.create({
  container: {
    flexDirection: 'row',
    padding: 8,
    borderRadius: 8,
    alignItems: 'center',
    justifyContent: 'center',
    gap: 8
  }
})

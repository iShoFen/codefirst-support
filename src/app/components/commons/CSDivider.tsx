import {StyleProp, View, ViewStyle} from "react-native";
import {useColors} from "../../themes/hooks/useColors";

type CSDividerProps = {
  style?: StyleProp<ViewStyle>,
  color?: string
}

export default function CSDivider(props: CSDividerProps) {
  const colors = useColors()

  const {
    style,
    color = colors.text
  } = props

  return (<View style={[
    {
      height: 1,
      backgroundColor: color
    },
    style
  ]}/>)
}

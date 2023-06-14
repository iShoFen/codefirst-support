import {View} from "react-native";
import {useColors} from "../../themes/hooks/useColors";

type CSDividerProps = {}

export default function CSDivider(props: CSDividerProps) {
  const colors = useColors()

  const {} = props

  return (<View style={{
    height: 1,
    backgroundColor: colors.text
  }}/>)
}

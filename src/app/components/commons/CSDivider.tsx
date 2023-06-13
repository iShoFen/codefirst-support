import {View} from "react-native";
import {useColors} from "../../themes/hooks/useColors";


export default function CSDivider() {
  const colors = useColors()

  return (<View style={{
    height: 1,
    backgroundColor: colors.text
  }} />)
}

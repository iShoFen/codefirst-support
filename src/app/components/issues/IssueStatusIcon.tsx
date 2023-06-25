import {IssueStatus} from "../../model/issues/IssueStatus";
import MaterialCommunityIcons from "react-native-vector-icons/MaterialCommunityIcons";
import {useMemo} from "react";
import {ViewStyle} from "react-native";

type IssueStatusIconProps = {
  status: IssueStatus
  size?: number
  style?: ViewStyle
}

export default function IssueStatusIcon(props: IssueStatusIconProps) {

  const {
    status,
    size = 24,
    style
  } = props

  const color = useMemo<string>(() => {
    switch (status) {
      case IssueStatus.OPEN:
        return "green"
      case IssueStatus.CLOSED:
        return "gray"
      default:
        return "black"
    }
  }, [status]);

  return (<>
    <MaterialCommunityIcons style={style} name="record-circle-outline" size={size} color={color}/>
  </>)
}

import {Text, View} from "react-native";
import {useRoute} from "@react-navigation/native";
import {IssueItemRouteProps} from "../navigation/types/RouteProps";

export default function IssueItemScreen() {
  const route = useRoute<IssueItemRouteProps>()

  const {item} = route.params

  return (
    <View>
      <Text>IssueItemScreen</Text>
      <Text>{item}</Text>
    </View>
  )
}

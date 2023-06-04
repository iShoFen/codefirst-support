import {Text, View} from "react-native";
import {useRoute} from "@react-navigation/native";
import {TicketItemRouteProps} from "../navigation/types/RouteProps";

export default function TicketItemScreen() {
  const route = useRoute<TicketItemRouteProps>()

  const {nounours} = route.params

  return (
    <View>
      <Text>TicketItemScreen</Text>
      <Text>{nounours.name}</Text>
      <Text>{nounours.age}</Text>
    </View>
  )
}

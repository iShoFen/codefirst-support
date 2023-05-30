import {Text, View} from "react-native";
import {useRoute} from "@react-navigation/native";
import {TicketItemRouteProps} from "../navigation/types/RouteProps";

export default function TicketItemScreen() {
  const route = useRoute<TicketItemRouteProps>()

  return (
    <View>
      <Text>TicketItemScreen</Text>
      <Text>{route.params.id}</Text>
    </View>
  )
}

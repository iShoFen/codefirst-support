import {User} from "../../model/User";
import {Image, StyleSheet, View} from "react-native";
import CSText from "../commons/CSText";

type UserAvatarProps = {
  user: User
}

export default function UserAvatar(props: UserAvatarProps) {
  const {
    user
  } = props

  return (
    <View style={styles.container}>
      <Image
        style={styles.image}
        source={{uri: user.image}}/>
      <CSText text={user.email}/>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    alignItems: 'center'
  },
  image: {
    width: 100,
    height: 100,
    borderRadius: 500
  }
})

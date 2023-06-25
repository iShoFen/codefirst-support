import {StyleSheet, TouchableOpacity, View} from "react-native";
import {USERS} from "../../data/user";
import {User} from "../../model/User";
import UserAvatar from "./UserAvatar";

type UserListProps = {
  onSelected: (user: User) => void
}

export default function UserList(props: UserListProps) {
  const {
    onSelected
  } = props

  return (
    <View style={styles.container}>
      {USERS.map(user =>
        <TouchableOpacity
          key={user.email}
          onPress={() => onSelected(user)}>
          <UserAvatar user={user}/>
        </TouchableOpacity>)}
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    gap: 24,
    justifyContent: 'space-evenly',
    flex: 1
  }
})

import React, {useCallback} from "react";
import {StyleSheet, View} from "react-native";
import CSText from "../components/commons/CSText";
import UserList from "../components/user/UserList";
import {User} from "../model/User";
import {useAppDispatch} from "../redux/hooks";
import {setLoggedUser} from "../redux/actions/userAction";
import {useNavigation} from "@react-navigation/native";
import {LoginStackNavigationProp} from "../navigation/types/NavigationProp";

export default function LoginScreen() {
  const dispatch = useAppDispatch()
  const loginNavigation = useNavigation<LoginStackNavigationProp>()

  const handleUserSelected = useCallback((user: User) => {
    dispatch(setLoggedUser(user))
    loginNavigation.navigate('Logged')
  }, [dispatch])

  return (
      <View style={styles.container}>
        <CSText text="Séléction du profil" type="h1" />
        <UserList onSelected={handleUserSelected} />
      </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 8,
  }
})

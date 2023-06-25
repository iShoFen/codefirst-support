import React, {useCallback, useEffect} from "react";
import {StyleSheet, View} from "react-native";
import CSText from "../components/commons/CSText";
import UserList from "../components/user/UserList";
import {User} from "../model/User";
import {useAppDispatch} from "../redux/hooks";
import {setLoggedUser} from "../redux/actions/userAction";
import {useNavigation} from "@react-navigation/native";
import {LoginStackNavigationProp} from "../navigation/types/NavigationProp";
import {getLoggedUser, saveLoggedUser} from "../redux/thunk/appThunk";

export default function LoginScreen() {
  const dispatch = useAppDispatch()
  const loginNavigation = useNavigation<LoginStackNavigationProp>()

  const handleUserSelected = useCallback((user: User) => {
    void dispatch(saveLoggedUser(user))
    loginNavigation.navigate('Logged')
  }, [dispatch])

  useEffect(() => {
    const loadLoggedUser = async () => {
      const loggedUser = await getLoggedUser()
      if (loggedUser) {
        console.log('ici')
        dispatch(setLoggedUser(loggedUser))
        loginNavigation.navigate('Logged')
      }
    }
    void loadLoggedUser()
  }, [dispatch, loginNavigation])

  return (
    <View style={styles.container}>
      <CSText text="Séléction du profil" type="h1"/>
      <UserList onSelected={handleUserSelected}/>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 8,
  }
})

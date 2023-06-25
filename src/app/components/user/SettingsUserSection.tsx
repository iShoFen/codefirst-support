import {StyleSheet, View} from "react-native";
import UserAvatar from "./UserAvatar";
import {useAppDispatch, useAppSelector} from "../../redux/hooks";
import CSText from "../commons/CSText";
import CSButton from "../commons/CSButton";
import {useNavigation} from "@react-navigation/native";
import {LoginStackNavigationProp} from "../../navigation/types/NavigationProp";
import {useCallback} from "react";
import {setLoggedUser} from "../../redux/actions/userAction";

export default function SettingsUserSection() {
  const user = useAppSelector(state => state.userReducer.loggedUser)
  const dispatch = useAppDispatch()
  const loginNavigation = useNavigation<LoginStackNavigationProp>()

  const handleDisconnect = useCallback(() => {
    dispatch(setLoggedUser(undefined))
    loginNavigation.popToTop()
  }, [loginNavigation, dispatch])

  return (
    <View style={styles.container}>
      <CSText text="Compte" type="h2"/>
      {
        user ?
          <UserAvatar user={user}/>
          :
          <CSText text="Aucun utilisateur connecté"/>
      }
      <CSButton onPress={handleDisconnect} icon="logout" text="Se déconnecter"/>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    gap: 4,
  }
})

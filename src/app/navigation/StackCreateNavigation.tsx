import {createStackNavigator} from "@react-navigation/stack";
import {CreateParamList} from "./types/ParamList";
import CreateIssueScreen from "../screens/issues/CreateIssueScreen";
import CreateSurveyScreen from "../screens/surveys/CreateSurveyScreen";
import HomeCreateScreen from "../screens/HomeCreateScreen";
import {useColors} from "../themes/hooks";

export default function StackCreateNavigation() {
  const Stack = createStackNavigator<CreateParamList>()
  const colors = useColors()

  return (
    <Stack.Navigator
      initialRouteName="HomeCreate"
      screenOptions={{
        headerBackTitle: 'Retour',
        headerStyle: {
          backgroundColor: colors.background,
        },
        headerTitleContainerStyle: {
          padding: 8,
        },
      }}>
      <Stack.Screen name="HomeCreate"
                    options={{
                      headerShown: false
                    }}
                    component={HomeCreateScreen}/>
      <Stack.Screen name="CreateIssue"
                    options={{
                      title: 'Créer un ticket'
                    }}
                    component={CreateIssueScreen}/>
      <Stack.Screen name="CreateSurvey"
                    options={{
                      title: 'Créer un questionnaire'
                    }}
                    component={CreateSurveyScreen}/>
    </Stack.Navigator>
  )
}

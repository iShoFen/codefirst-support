import {Alert, ScrollView, StyleSheet, View} from "react-native";
import {useCallback, useEffect, useState} from "react";
import {Picker} from "@react-native-picker/picker";
import CSText from "../../components/commons/CSText";
import {useColors} from "../../themes/hooks";
import {createIssue, fetchIssueModel, fetchIssueModels} from "../../hooks/issues";
import {IssueModel} from "../../model/issues/IssueModel";
import {IssueModelInfo} from "../../model/issues/IssueModelInfo";
import IssueForm from "../../components/issues/IssueForm";
import {IssueField} from "../../model/issues/IssueField";
import {useNavigation} from "@react-navigation/native";
import {CreateStackNavigationProp, IssueStackNavigationProp} from "../../navigation/types/NavigationProp";

export default function CreateIssueScreen() {
  const [selectedIssueModelId, setSelectedIssueModelId] = useState<string>("")
  const [issueModels, setIssueModels] = useState<IssueModelInfo[]>()
  const [issueModel, setIssueModel] = useState<IssueModel>()
  const issueNavigation = useNavigation<IssueStackNavigationProp>()
  const createNavigation = useNavigation<CreateStackNavigationProp>()

  const colors = useColors()

  const handleValidation = useCallback(async (title: string, fields: IssueField[]) => {
    if(!issueModel) return
    if(title === "") {
      Alert.alert("Attention", "Le nom du ticket ne peut pas être vide")
      return
    } else if (fields.findIndex(value => value.value === "" && value.required) > -1) {
      Alert.alert("Attention", "Vous devez remplir tous les champs requis")
      return
    }
    const createdIssue = await createIssue(title, "author", issueModel, fields)

    if(!createdIssue) {
      Alert.alert("Attention", "Une erreur est survenue lors de la création du ticket")
      return
    }

    createNavigation.goBack()
    issueNavigation.navigate('Item', {id: createdIssue.id, title: createdIssue.title})
  }, [issueModel])

  const handleIssueModelChange = useCallback((itemValue: string) => {
    setSelectedIssueModelId(itemValue)
  }, [])

  useEffect(() => {
    const fetchModels = async () => {
      try {
        const issueModels = await fetchIssueModels()
        setIssueModels(issueModels)
      } catch (error) {
        console.error(error?.toString())
      }
    }
    void fetchModels()
  }, [])

  useEffect(() => {
    const fetchModels = async () => {
      try {
        if (!selectedIssueModelId || selectedIssueModelId === "") {
          setIssueModel(undefined)
          return
        }
        const issueModel = await fetchIssueModel(selectedIssueModelId)
        setIssueModel(issueModel)
      } catch (error) {
        console.error(error?.toString())
      }
    }
    void fetchModels()
  }, [selectedIssueModelId])

  return (
    <ScrollView>
      <View style={styles.container}>
        <View>
          <CSText text="Modèle"/>
          <View style={styles.picker}>
            {
              issueModels ?
                <Picker
                  style={{
                    color: colors.onPrimary
                  }}
                  selectedValue={selectedIssueModelId}
                  onValueChange={handleIssueModelChange}>
                  <Picker.Item label="Choissisez un modèle" value=""/>
                  {issueModels.map(model => <Picker.Item key={model.id} label={model.name} value={model.id}/>)}
                </Picker>
                :
                <CSText text="Chargement des modèles"/>
            }
          </View>
        </View>

        {
          selectedIssueModelId !== "" &&
          (
            issueModel ?
              <IssueForm issueModel={issueModel} onValidate={handleValidation}/>
              :
              <CSText text="Récupération du modèle"/>
          )
        }
      </View>
    </ScrollView>
  )
}

const styles = StyleSheet.create({
  container: {
    padding: 8,
    gap: 8
  },
  picker: {
    backgroundColor: 'white',
    borderRadius: 8,
  },
  pickerItem: {
    padding: 8,
    borderWidth: 1,
    borderRadius: 8,
    borderColor: 'gray'
  }
})

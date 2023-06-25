import CSField from "../commons/CSField";
import CSDivider from "../commons/CSDivider";
import CSText from "../commons/CSText";
import CSButton from "../commons/CSButton";
import {StyleSheet, View} from "react-native";
import {IssueModel} from "../../model/issues/IssueModel";
import {useColors} from "../../themes/hooks";
import {useCallback, useMemo, useState} from "react";
import {IssueField} from "../../model/issues/IssueField";

type IssueFormProps = {
  issueModel: IssueModel,
  onValidate: (title: string, fields: IssueField[]) => void
}

export default function IssueForm(props: IssueFormProps) {
  const {
    issueModel,
    onValidate
  } = props

  const colors = useColors()

  const [title, setTitle] = useState<string>("")
  const [fields, setFields] = useState<IssueField[]>([])

  const convertedFields = useMemo(() => {
    const f = issueModel.fields.map(field => new IssueField(field.title, field.description, field.required, ""))
    setFields(f)
    return f
  }, [issueModel])

  const handleFieldChange = useCallback((index: number, newValue: string) => {
    setFields(prevState => {
      const field = prevState[index]
      field.value = newValue

      const newState = [...prevState]
      newState[index] = field

      return newState
    })
  }, [])

  const handleValidate = useCallback(() => {
    onValidate(title, fields)
  }, [title, fields])

  return (
    <View style={styles.container}>
      <CSField placeholder="Nom du ticket"
               value={title}
               onChange={setTitle}
               label="Nom"/>

      <CSField value={issueModel.category.name} label="Catégorie" enabled={false}/>

      <CSDivider style={{marginVertical: 8, marginHorizontal: 16}} color={colors.primary}/>
      {
        convertedFields.map((field, index) =>
          <CSField
            required={field.required}
            key={field.title + index}
            label={field.title}
            value={field.value}
            onChange={(text) => handleFieldChange(index, text)}
            placeholder={field.description}
            multiLine={true}/>
        )
        ?? <CSText text="Chargment des champs..."/>
      }

      <CSButton text="Valider"
                onPress={handleValidate}
                style={styles.validationButton}
                icon="send"/>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    gap: 8
  },
  validationButton: {
    marginTop: 16,
    borderRadius: 24
  }
})

import {Text, TextStyle} from "react-native";
import {useMemo} from "react";
import {useColors} from "../../themes/hooks/useColors";


type CSTextProps = {
  text: string
  style?: TextStyle
  type?: 'h1' | 'h2' | 'h3' | 'normal' | 'bold' | 'small'
  color?: string
  numberOfLines?: number,
  testID?: string
}

export default function CSText(props: CSTextProps) {
  const colors = useColors()

  const {
    text,
    style,
    type = 'NORMAL',
    color,
    numberOfLines,
    testID
  } = props

  const customStyle: TextStyle = useMemo<TextStyle>(() => {
    let textColor: string = colors.text
    let isBold: boolean = false
    let fontSize: number = 20

    switch (type) {
      case 'h1':
        textColor = colors.h1
        fontSize = 35
        isBold = true
        break
      case 'h2':
        textColor = colors.h2
        fontSize = 30
        isBold = true
        break
      case 'bold':
        isBold = true
        break
      case 'small':
        fontSize = 16
        break
    }

    return {
      color: color ?? textColor,
      fontWeight: isBold ? 'bold' : 'normal',
      fontSize: fontSize
    }
  }, [type, colors])

  return (<Text style={[customStyle, style]} numberOfLines={numberOfLines} testID={testID}>
    {text}
  </Text>)
}

import {Text, TextStyle} from "react-native";
import {CSTextType} from "./types";
import {useMemo} from "react";
import {useColors} from "../../themes/hooks/useColors";


type CSTextProps = {
  text: string
  style?: TextStyle
  type?: 'h1' | 'h2' | 'h3' | 'normal' | 'bold' | 'small'
}

export default function CSText(props: CSTextProps) {
  const colors = useColors()

  const {
    text,
    style,
    type = 'NORMAL'
  } = props

  const customStyle: TextStyle = useMemo<TextStyle>(() => {
    let color: string = colors.text
    let isBold: boolean = false
    let fontSize: number = 20

    switch (type) {
      case 'h1':
        color = colors.h1
        fontSize = 35
        isBold = true
        break
      case 'h2':
        color = colors.h2
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
      color: color,
      fontWeight: isBold ? 'bold' : 'normal',
      fontSize: fontSize
    }
  }, [type, colors])

  return (<Text style={[customStyle, style]}>
    {text}
  </Text>)
}

export type ThemeColors = {
  primary: string
  secondary: string
  tertiary: string
  text: string
  textVariant: string
  background: string
  h1: string
  h2: string
}

export type GlobalColors = {
  white: string
  black: string
  success: string
  info: string
  warning: string
  danger: string
}

export type CSColors = GlobalColors & ThemeColors

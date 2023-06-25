export type NavigationThemeColors = {
  primary: string;
  background: string;
  card: string;
  text: string;
  border: string;
  notification: string;
}

export type ThemeColors = {
  primary: string
  onPrimary: string
  secondary: string
  onSecondary: string
  tertiary: string
  onTertiary: string
  text: string
  textVariant: string
  background: string
  backgroundVariant: string
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
export type ThemeMode = 'light' | 'dark' | 'system'

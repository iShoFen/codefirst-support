import {GlobalColors, NavigationThemeColors, ThemeColors} from "./types";

const globalColors: GlobalColors = {
  white: '#ffffff',
  black: '#212121',
  success: '#5BBC27',
  info: '#3075FF',
  warning: '#FFC721',
  danger: '#E72D0A',
}

const lightColors: ThemeColors = {
  primary: '#3DDC97',
  onPrimary: '#313131',
  secondary: '#021D1E',
  onSecondary: '#F0EFF4',
  tertiary: '#023C40',
  onTertiary: '#F0EFF4',
  text: '#313131',
  textVariant: '#979C9E',
  background: '#F0EFF4',
  backgroundVariant: '#fff',
  h1: '#021D1E',
  h2: '#023C40'
}

const darkColors: ThemeColors = {
  primary: '#3DDC97',
  onPrimary: '#313131',
  secondary: '#021D1E',
  onSecondary: '#F0EFF4',
  tertiary: '#023C40',
  onTertiary: '#F0EFF4',
  text: '#F0EFF4',
  textVariant: '#979C9E',
  background: '#021D1E',
  backgroundVariant: '#023C40',
  h1: '#F0EFF4',
  h2: '#F0EFF4'
}

const lightNavigationColors: NavigationThemeColors = {
  primary: lightColors.primary,
  background: lightColors.background,
  card: lightColors.backgroundVariant,
  text: lightColors.text,
  notification: globalColors.danger,
  border: lightColors.secondary
}

const darkNavigationColors: NavigationThemeColors = {
  primary: darkColors.primary,
  background: darkColors.secondary,
  card: '#202325',
  text: darkColors.text,
  notification: globalColors.danger,
  border: darkColors.secondary
}

export {globalColors, lightColors, darkColors, lightNavigationColors, darkNavigationColors}

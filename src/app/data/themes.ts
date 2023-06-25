import {ThemeMode} from "../themes/types";

export type CSTheme = {
  name: string
  value: ThemeMode
}

export const THEMES: CSTheme[] = [
  {
    name: 'Clair',
    value: 'light'
  },
  {
    name: 'Sombre',
    value: 'dark'
  },
  {
    name: 'Système',
    value: 'system'
  },
]

import {FETCH_NOUNOURS} from "../constants";

type AppReducerState = {
  nounours: Nounours[],
  favoriteNounours: Nounours[]
}

type AppReducerPayload = {
  type: string,
  payload: any
}


const initialState = {
  nounours: [],
  favoriteNounours: [],
}

const appReducer = (state = initialState, action) => {
  switch (action.type) {
    // case ADD_FAVORITE_NOUNOURS:
    //   // @ts-ignore
    //   state.favoriteNounours.push(action.nounours)
    //   return {...state};
    case FETCH_NOUNOURS:
      // @ts-ignore
      return {...state, nounours: action.payload};
    // case DELETE_NOUNOURS:
    //   // @ts-ignore
    //   return {...state, nounours: [...state.nounours.filter((item) => item.name != action.payload.name)]}
    default:
      return state;
  }
}

export default appReducer

import {FETCH_NOUNOURS} from '../constants';

export const setNounoursList = (nounoursList: Nounours[]) => {
  return {
    type: FETCH_NOUNOURS,
    payload: nounoursList,
  };
}

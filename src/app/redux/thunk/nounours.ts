//Define your action creators that will be responsible for asynchronous operations
import {setNounoursList} from "../actions/nounoursAction";
import {Nounours} from "../../model/Nounours";

export const getNounoursList = () => {
  //In order to use await your callback must be asynchronous using async keyword.
  return async (dispatch) => {
    //Then perform your asynchronous operations.
    try {
      //Have it first fetch data from our starwars url.
      const nounoursPromise = await fetch('https://iut-weather-api.azurewebsites.net/nounours');
      //Then use the json method to get json data from api/
      const nounoursListJson = await nounoursPromise.json();
      const nounoursList = nounoursListJson.map((elt) => new Nounours(elt["name"], elt["nbPoils"], elt["age"], elt["image"]));
      dispatch(setNounoursList(nounoursList));
    } catch (error) {
      console.log('Error---------', error);
      //You can dispatch to another action if you want to display an error message in the application
      //dispatch(fetchDataRejected(error))
    }
  }
}

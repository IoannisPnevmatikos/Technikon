import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const findOwnerByTin = async (token) => {

  // Send POST request
  return axios.get(
    `${base_url}${uri.api}${uri.owner}/tinNumber`,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    }
  );
};

export default findOwnerByTin;

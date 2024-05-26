import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";
const findAllOwners = async (token) => {

  return axios.get(
    `${base_url}${uri.api}${uri.admin}${uri.owner}`,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    }
  );
}

export default findAllOwners;
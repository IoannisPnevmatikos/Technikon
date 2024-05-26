import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const findAllOwners = async ( formData, token) => {
    const data = Object.fromEntries(formData.entries())
  // Send POST request
  return axios.get(
    `${base_url}${uri.admin}${uri.owner}`,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    }
  );
}

export default findAllOwners;
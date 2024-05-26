import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const findOwnerByEmail = (formData, token)=> {
    const data = Object.fromEntries(formData.entries())
    // Send POST request
    return axios.get(
      `${base_url}${uri.api}${uri.admin}${uri.owner}/email/${data.email}`,
      {
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        }
      }
    );
}

export default findOwnerByEmail
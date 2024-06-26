import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const findOwnerByUsername = (formData,token ) =>{
    const data = Object.fromEntries(formData.entries())
    // Send POST request
    return axios.get(
      `${base_url}${uri.api}${uri.admin}${uri.owner}/username/${data.username}`,
      {
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        }
      }
    );
}

export default findOwnerByUsername
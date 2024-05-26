import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const findOwnerByDate = (formData, token)=> {
    const data = Object.fromEntries(formData.entries())
    // Send POST request
    return axios.get(
      `${base_url}${uri.admin}${uri.owner}/${data.startDate}/to/${data.endDate}`,
      {
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        }
      }
    );
}

export default findOwnerByDate
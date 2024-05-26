import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const deactivateOwner =(formData, token)=> {

        const data = Object.fromEntries(formData.entries())
          // Send POST request
          return axios.put(
            `${base_url}${uri.admin}${uri.owner}/deactivate/${data.username}`,
            {
              headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
              }
            }
          );
        

}
export default deactivateOwner
import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const deactivateOwner =(formData, token)=> {

        const data = Object.fromEntries(formData.entries())

          return axios.put(
            `${base_url}${uri.api}${uri.admin}${uri.owner}/deactivate/${data.username}`,
            {},
            {
              headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
              }
            }
          );
        

}
export default deactivateOwner
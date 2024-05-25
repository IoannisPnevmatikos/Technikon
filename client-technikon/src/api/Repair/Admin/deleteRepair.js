import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const deleteRepair = async (formData, token) => {
     // Extract form data entries once
     const data = Object.fromEntries(formData.entries());
    

     console.log(data)
 

    // Send PUT request
    return axios.delete(
        `${base_url}${uri.api}${uri.admin}${uri.repair}/${data.repairId}`,
        {
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
          }
        }
      );

}

export default deleteRepair;
import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const findRepairByDate = async (formData, token) => {
    // Extract form data entries once
    const data = Object.fromEntries(formData.entries());

    console.log(data.localDate);

    // Send GET request
    return axios.get(
        `${base_url}${uri.api}${uri.admin}${uri.repair}/${data.localDate}`,
        {
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
          }
        }
      );
}

    export default findRepairByDate;
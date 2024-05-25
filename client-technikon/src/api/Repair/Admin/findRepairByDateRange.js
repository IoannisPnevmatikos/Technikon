import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const findRepairByDateRange = async (formData, token) => {
    // Extract form data entries once
    const data = Object.fromEntries(formData.entries());

    console.log(data.startDate);
    console.log(data.endDate);

    // Send GET request
    return axios.get(
        `${base_url}${uri.api}${uri.admin}${uri.repair}/dateRange/${data.startDate}/${data.endDate}`,
        {
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
          }
        }
      );
}

    export default findRepairByDateRange;
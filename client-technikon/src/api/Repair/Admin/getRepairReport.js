import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const getRepairReport = async (formData, token) => {
    // Extract form data entries once
    const data = Object.fromEntries(formData.entries());

    // Send GET request
    return axios.get(
        `${base_url}${uri.api}${uri.admin}${uri.repair}/report/dateRange/${data.startDate}/${data.endDate}`,
        {
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
          }
        }
      );
}

    export default getRepairReport;
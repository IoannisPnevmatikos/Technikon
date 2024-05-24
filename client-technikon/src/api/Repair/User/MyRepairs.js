import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const MyRepairs = async (formData, token) => {
    // Extract form data entries once
    const data = Object.fromEntries(formData.entries());

    console.log(data.startDate);
    console.log(data.endDate);

    // Send GET request
    return axios.get(
        `${base_url}${uri.api}${uri.repair}/tinNumber}`,
        {
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
          }
        }
      );
}

    export default MyRepairs;
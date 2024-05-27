import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const getRepostAdmin = async (startDate, endDate, token) => {

  // Send POST request
  return axios.get(
    `${base_url}${uri.api}${uri.admin}${uri.property}/rangeOfDates/${startDate}/${endDate}`,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json' // Optional but recommended
      }
    }
  );
};

export default getRepostAdmin;

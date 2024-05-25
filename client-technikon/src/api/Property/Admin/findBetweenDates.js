import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const findBetweenDates = async (formData, token) => {
  // Extract form data entries once
  const data = Object.fromEntries(formData.entries());

  // Send POST request
  return axios.get(
    `${base_url}${uri.api}${uri.admin}${uri.property}/rangeOfDates/${data.startDate}/${data.endDate}`,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json' // Optional but recommended
      }
    }
  );
};

export default findBetweenDates;

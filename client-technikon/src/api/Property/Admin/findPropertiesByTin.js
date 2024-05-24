import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const findPropertiesByTinNumber = async (formData, token) => {
  // Extract form data entries once
  const data = Object.fromEntries(formData.entries());

  // Send POST request
  return axios.get(
    `${base_url}${uri.api}${uri.admin}${uri.property}/tinNumber/${data.tinNumber}`,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json' // Optional but recommended
      }
    }
  );
};

export default findPropertiesByTinNumber;

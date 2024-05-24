import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const findPropertyByE9 = async (formData, token) => {
  // Extract form data entries once
  const data = Object.fromEntries(formData.entries());

  // Send POST request
  return axios.get(
    `${base_url}${uri.api}${uri.admin}${uri.property}/propertyId/${data.propertyE9}`,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json' // Optional but recommended
      }
    }
  );
};

export default findPropertyByE9;

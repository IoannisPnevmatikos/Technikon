import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const deleteProperty = async (formData, token) => {
  // Extract form data entries once
  const data = Object.fromEntries(formData.entries());

  // Send POST request
  return axios.delete(
    `${base_url}${uri.api}${uri.property}/${data.id}`,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json' // Optional but recommended
      }
    }
  );
};

export default deleteProperty;

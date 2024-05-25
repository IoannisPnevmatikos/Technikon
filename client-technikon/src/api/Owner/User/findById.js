import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const findById = async (formData, token) => {
  // Extract form data entries once
  const data = Object.fromEntries(formData.entries());

  // Send POST request
  return axios.get(
    `${base_url}${uri.api}${uri.owner}/${data.id}`,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json' 
      }
    }
  );
};

export default findById;

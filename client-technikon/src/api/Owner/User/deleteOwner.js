import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const deleteOwner = async (formData, token) => {
  // Extract form data entries once
  const data = Object.fromEntries(formData.entries());

  // Send POST request
  return axios.delete(
    `${base_url}${uri.api}${uri.owner}/${data.id}`,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json' // Optional but recommended
      }
    }
  );
};

export default deleteOwner;

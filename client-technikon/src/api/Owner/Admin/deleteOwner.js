import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const deleteOwner = async (formData, token) => {
  // Extract form data entries once
  const data = Object.fromEntries(formData.entries());

  // Send POST request
  return axios.delete(
    `${base_url}${uri.admin}${uri.owner}/${data.username}`,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    }
  );
};

export default deleteOwner;

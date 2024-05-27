import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const getPropertyDetailsAdmin = async (id, token) => {

  // Send POST request
  return axios.get(
    `${base_url}${uri.api}${uri.admin}${uri.property}/propertyId/${id}`,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json' // Optional but recommended
      }
    }
  );
};

export default getPropertyDetailsAdmin;

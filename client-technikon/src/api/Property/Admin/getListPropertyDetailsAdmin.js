import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const getListPropertyDetailsAdmin = async (id, token) => {

  // Send POST request
  return axios.get(
    `${base_url}${uri.api}${uri.admin}${uri.property}/tinNumber/${id}`,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json' // Optional but recommended
      }
    }
  );
};

export default getListPropertyDetailsAdmin;

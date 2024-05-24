import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const findMyProperties = async (token) => {

  // Send POST request
  return axios.get(
    `${base_url}${uri.api}${uri.property}/tinNumber`,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json' // Optional but recommended
      }
    }
  );
};

export default findMyProperties;

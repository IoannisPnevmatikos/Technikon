import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const findOwnerByTin = async (formData,token) => {
const data = Object.fromEntries(formData.entries())
  // Send POST request
  return axios.get(
    `${base_url}${uri.admin}${uri.owner}/tinNum/${data.tin}`,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    }
  );
};

export default findOwnerByTin;

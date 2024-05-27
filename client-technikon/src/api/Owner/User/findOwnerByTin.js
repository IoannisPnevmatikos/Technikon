import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const findOwnerByTin = async (formData,token) => {
const data = Object.fromEntries(formData.entries())
const tin = data.tin
console.log("The tin num entered is ", tin)
  // Send POST request
  return axios.get(
    `${base_url}${uri.api}${uri.owner}/tin/${tin}`,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    }
  );
};

export default findOwnerByTin;

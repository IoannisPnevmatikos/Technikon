import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const updateOwner = async (formData, token) => {
  // Extract form data entries once
  const data = Object.fromEntries(formData.entries());
  let firstnameStr = data.firstname
  firstnameStr = data.firstName.charAt(0).toUpperCase() + data.firstName.slice(1)
  let lastnameStr = data.lastName
  lastnameStr = data.lastName.charAt(0).toUpperCase() + data.lastName.slice(1)

  const payload = {
    tinNumber: data.tinNumber,
    firstName: data.firstName,
    lastName: data.lastName,
    username: data.username,
    password: data.password,
    email: data.email,
    address: data.address,
    phone: data.phone
    }
 ;

  // Send POST request
  return axios.put(
    `${base_url}${uri.api}${uri.admin}${uri.owner}/${data.username}`,
    payload,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json' // Optional but recommended
      }
    }
  );
};

export default updateOwner;

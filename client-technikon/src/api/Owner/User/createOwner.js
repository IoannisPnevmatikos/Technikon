import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const createOwner = async (formData, token) => {
  const data = Object.fromEntries(formData.entries());

  let firstnameStr = data.firstname
  firstnameStr = data.firstName.charAt(0).toUpperCase() + data.firstName.slice(1)
  console.log("firstName is capitalized??? ", firstnameStr)
  let lastnameStr = data.lastName
  lastnameStr = data.lastName.charAt(0).toUpperCase() + data.lastName.slice(1)
  console.log("lastName is capitalized??? ", lastnameStr)
  const payload = {
    tinNumber: data.tinNumber,
    firstName: firstnameStr,
    lastName: lastnameStr,
    username: data.username,
    password: data.password,
    email: data.email,
    address: data.address,
    phone: data.phone
  }

  console.log(payload)
  // Send POST request
  return axios.put(
    `${base_url}${uri.api}${uri.owner}`,
    payload,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    }
  )
}

export default createOwner;

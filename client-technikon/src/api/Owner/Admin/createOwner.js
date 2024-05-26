import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const createOwner = async (formData, token) => {

  const data = Object.fromEntries(formData.entries());
  

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
    
    console.log(payload)
  // {{baseUrl}}/auth/signup/user
  return axios.put(
    `${base_url}${uri.auth}/signup/${data.role}`,
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

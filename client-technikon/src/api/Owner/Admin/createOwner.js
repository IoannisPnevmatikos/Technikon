import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const createOwner = async (formData, token) => {
  //const token = useToken.getState().token?.data;
  const data = Object.fromEntries(formData.entries());
  console.log(data)

  const ownerPayload = {

    username: data.username,
    password: data.password,
    email: data.email,

  }

  console.log(ownerPayload)
  // {{admin}}/owner/create/user
  return axios.post(
    `${base_url}${uri.api}${uri.admin}${uri.owner}/create/${data.role}`,
    ownerPayload,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    }
  )
}

export default createOwner;

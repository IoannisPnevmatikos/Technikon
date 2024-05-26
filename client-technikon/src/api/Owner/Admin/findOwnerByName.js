import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const findOwnerByFirstname= async (formData,token) => {
const data = Object.fromEntries(formData.entries())
  // Send POST request
  return axios.get(
    `${base_url}${uri.api}${uri.admin}${uri.owner}/name/${data.firstname}`,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    }
  );
};

const findOwnerByLastname= async (formData,token) => {
  const data = Object.fromEntries(formData.entries())
    // Send POST request
    return axios.get(
      `${base_url}${uri.api}${uri.admin}${uri.owner}/surname/${data.lastname}`,
      {
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        }
      }
    );
  };


export  {findOwnerByFirstname, findOwnerByLastname};

import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const updateProperty = async (formData, token) => {
  // Extract form data entries once
  const data = Object.fromEntries(formData.entries());

  // Prepare the request payload
  const payload = {
    propertyId: data.propertyE9? data.propertyE9 : null,
    address: data.address? data.address : null,
    yearOfConstruction: data.yearOfConstruction? data.yearOfConstruction : null,
    typeOfProperty: data.typeOfProperty? data.typeOfProperty : null,
    photo: data.photoURL? data.photoURL : null,
    mapLocation: {
      latitude: data.latitude? data.latitude : null,
      longitude: data.longitude? data.longitude : null
    }
  };

  // Send POST request
  return axios.put(
    `${base_url}${uri.api}${uri.admin}${uri.property}/${data.id}`,
    payload,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json' // Optional but recommended
      }
    }
  );
};

export default updateProperty;

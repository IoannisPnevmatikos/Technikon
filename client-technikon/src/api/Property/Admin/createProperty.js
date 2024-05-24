import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const createProperty = async (formData, token) => {
  // Extract form data entries once
  const data = Object.fromEntries(formData.entries());

  // Prepare the request payload
  const payload = {
    propertyId: data.propertyE9,
    address: data.address,
    yearOfConstruction: data.yearOfConstruction,
    typeOfProperty: data.typeOfProperty,
    photo: data.photoURL,
    mapLocation: {
      latitude: data.latitude,
      longitude: data.longitude
    }
  };

  // Send POST request
  return axios.post(
    `${base_url}${uri.api}${uri.admin}${uri.property}/${data.tinNumber}`,
    payload,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json' // Optional but recommended
      }
    }
  );
};

export default createProperty;

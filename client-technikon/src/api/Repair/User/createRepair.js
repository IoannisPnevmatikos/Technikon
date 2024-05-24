import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const createRepair = async (formData, token) => {
    // Extract form data entries once
    const data = Object.fromEntries(formData.entries());
  
    // Prepare the request payload
    const payload = {
        localDate: data.localDate,
        shortDescription: data.shortDescription,
        typeOfRepair: data.typeOfRepair,
        statusOfRepair: data.statusOfRepair,
        cost: data.cost,
        descriptionText: data.descriptionText,
        propertyId: data.propertyId
    };

    
    console.log("hello");

    // Send POST request
  return axios.post(
    `${base_url}${uri.api}${uri.repair}`,
    payload,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    }
  );
  

};



export default createRepair;
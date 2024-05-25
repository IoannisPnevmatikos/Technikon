import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const updateRepair = async (formData, token) => {
    // Extract form data entries once
    const data = Object.fromEntries(formData.entries());
    

    console.log(data)


    // Prepare the request payload
    const payload = {
        localDate: data.localDate?data.localDate:null,
        shortDescription: data.shortDescription?data.shortDescription:null,
        typeOfRepair: data.typeOfRepair?data.typeOfRepair:null,
        statusOfRepair: data.statusOfRepair?data.statusOfRepair:null,
        cost: data.cost?data.cost:null,
        descriptionText: data.descriptionText?data.descriptionText:null,
        propertyId: null
    };

    // Send PUT request
  return axios.put(
    `${base_url}${uri.api}${uri.admin}${uri.repair}/${data.repairId}`,
    payload,
    {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    }
  );

};



export default updateRepair;
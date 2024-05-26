import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const findOwnerByDate = (formData, token)=> {
    const data = Object.fromEntries(formData.entries())
    console.log(data)

    const formatDateString = (dateStr) => {
      const [month, day, year] = dateStr.split('/');
      dateStr.replace(/\//g, '-');
      return `${year}-${month}-${day}`;
    };


    return axios.get(
      `${base_url}${uri.api}${uri.admin}${uri.owner}/${formatDateString(data.startDate)}/to/${formatDateString(data.endDate)}`,
      {
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        }
      }
    );
}

export default findOwnerByDate
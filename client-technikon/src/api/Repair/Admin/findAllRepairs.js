import axios from "axios";
import { base_url, uri } from "../../../constants/endpoints/endpoints";

const findAllRepairs = async (token) => {

    // Send GET request
    return axios.get(
        `${base_url}${uri.api}${uri.admin}${uri.repair}`,
        {
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
          }
        }
      );
}

    export default findAllRepairs;
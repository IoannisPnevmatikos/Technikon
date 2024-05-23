import axios from "axios";
import { base_url, uri } from "../../constants/endpoints/endpoints";

const login = async ({username, password}) => {
    return axios.post(`${base_url}${uri.auth}login`,{
        username: username,
        password: password
    });
};

export default login
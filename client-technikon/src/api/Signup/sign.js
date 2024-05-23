import axios from "axios";
import { base_url, uri } from "../../constants/endpoints/endpoints";

export const signUser = async ({username, email, password}) => {
    return axios.post(`${base_url}${uri.auth}signup/user`,{
        username: username,
        email: email,
        password: password
    });
};

export const signAdmin = async ({username, email, password}) => {
    return axios.post(`${base_url}${uri.auth}signup/admin`,{
        username: username,
        email: email,
        password: password
    });
};
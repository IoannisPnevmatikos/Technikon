import axios from "axios";
import { base_url, uri } from "../../constants/endpoints/endpoints";

const logout = async () => {
    try {
        const token = localStorage.getItem("token");
        axios.get(`${base_url}${uri.auth}logout`, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer  ${token},`,
            },
        })
    }
    catch (err) {
        console.error(err);
    }

};

export default logout
import axios from "axios";
import { base_url, uri } from "../../constants/endpoints/endpoints";

const logout = async () => {
    try{
        const token = localStorage.getItem("token");
     axios.get(`${base_url}${uri.auth}logout`,{
            withCredentials:true,
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer  ${token},`,
              },
        }).then((response)=> {

            if (response.status === 204) {
                console.log('LogOUT');
                localStorage.clear
        }
        }
    )
    }
    catch (err) {
        console.error(err);
    }
   
};

export default logout
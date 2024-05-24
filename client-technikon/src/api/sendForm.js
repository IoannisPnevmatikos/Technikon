import axios from "axios";

const sendForm =(url, token, formData) => {

return axios.post(`${url}/`,formData,{
    withCredentials:true,
    headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer  ${token},`,
      },
})



}

export default sendForm
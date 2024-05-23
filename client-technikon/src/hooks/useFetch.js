import axios from "axios"
import { useState, useEffect } from "react";

export const useFetchList = ({ url, resource }) => {
    const [array, setArray] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
        
    useEffect(() => {
        axios.get(`${url}/${resource}`)
            .then((data) => {
                setArray(data);
                setIsLoading(false);
            });
    }, [url, resource]);
    
    return [array, isLoading];
    
}

export const useFetchSingle=({url, resource,query})=>{
const [single, setSingle ] = useState([]);
const [isLoading, setIsLoading] = useState(true);
      
useEffect(() => {
    axios.get(`${url}/${resource}/${query}`)
        .then((data) => {
            setSingle(data);
            setIsLoading(false);
        });
}, [url, resource]);

return [single, isLoading];

}
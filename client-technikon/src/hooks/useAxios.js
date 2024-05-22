import axios from "axios";
import { useState, useEffect } from "react";

const useAxios = (url) => {
    const [state, setState] = useState(null);

    useEffect(() => {
        axios
          .get("https://swapi.dev/api/people")
          .then((data) => {
            console.log(data);
            setState({...state, data, isLoading: false})
          })
          .catch((error) => {
            setState({...state, error});
          });
      }, []);

    return state;
}

export default useAxios;
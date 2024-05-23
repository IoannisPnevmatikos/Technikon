import { useState, useEffect } from "react";

const usePromise = (promise) => {
  const [state, setState] = useState({
    data: null,
    isLoading: true,
    error: null,
  });

  useEffect(() => {
    promise
      .then((res) => {
        setState({ ...state, data: res.data, isLoading: false });
      })
      .catch((error) => {
        console.log(error);
        setState({ ...state, error });
      });
  }, []);

  return state;
};

export default usePromise;

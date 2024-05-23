import { Container } from "@mui/material";
import {useFetchList} from "../../hooks/useFetch";
import { useEffect,useState } from "react";
import useToken from "../../stores/useToken";
import axios from "axios";


const Owner = () => {
  // const [owners, setOwners ]= useState([]);
  
  // const [list,isLoading] = useFetchList({
  //   url: "http://localhost:8080/api/v1",
  //   resource: "owner"
  // })

  // useEffect(()=>{
  //   console.log(list.data)
  // },[])

  // return (
  // <Container >
  //   <p>NOthing</p>
  //   {list.data.map((owner)=> {
  //     <>
  //     <p>nothing yet</p>
  //     </>
  //   })}
  //    {/* {isLoading ? <h3>Loading...</h3> :   <p>nothing yet</p>} */}
  //   </Container>
  // );

  const [joke, setJoke] = useState(null);
  const [isLoading, setIsLoading] = useState(true);

  const {token} = useToken();

  useEffect(() => {
    axios
      .get("https://api.chucknorris.io/jokes/random")
      .then((data) => {
        setJoke(data.data.value);
        setIsLoading(false);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <div>
      <h1>Admin page</h1>
      <h1>{token?.data}</h1>
      <div>{isLoading ? <h3>Loading...</h3> : <h3>{joke}</h3>}</div>
    </div>
  );
  
};

export default Owner;

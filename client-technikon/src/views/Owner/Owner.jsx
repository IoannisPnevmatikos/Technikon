import { Container } from "@mui/material";
import {useFetchList} from "../../hooks/useFetch";
import { useEffect,useState } from "react";


const Owner = () => {

  const [owners, setOwners ]= useState([]);
  
  const [list,isLoading] = useFetchList({
    url: "http://localhost:8080/api/v1",
    resource: "owner"
  })



  useEffect(()=>{
    console.log(list.data)
  },[])

  return (
  <Container >
    <p>NOthing</p>
    {list.data.map((owner)=> {
      <>
      <p>nothing yet</p>
      </>
    })}
     {/* {isLoading ? <h3>Loading...</h3> :   <p>nothing yet</p>} */}
    </Container>
  );
};

export default Owner;

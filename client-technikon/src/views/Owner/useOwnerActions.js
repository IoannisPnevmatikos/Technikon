import { useCallback } from 'react';
import createOwner from '../../api/Owner/User/createOwner';
import deleteOwner from '../../api/Owner/User/deleteOwner';
import updateOwner from '../../api/Owner/User/updateOwner';
import findOwnerByTin from '../../api/Owner/User/findOwnerByTin';
import {paths} from '../../constants/paths/paths'
//import { useNavigate, redirect } from 'react-router-dom';

const useOwnerActions = (token, navigate,setOwnerData) => {

  const handleSubmitCreate = useCallback(async (formData) => {
   // setIsLoading(true);
  //  const formData = new FormData(event.target);
    try {
      const response = await createOwner(formData, token?.data); 
    
    //  setOwnerData(response.data)
    if( response.status===200){
      console.log('Owner created successfully', response);
      alert('Owner created!')
    }
     // navigate(paths.owner)
  
    } catch (error) {
      console.error('Owner creation failed:', error);
      if (error.response.status === 403) {
        alert('You are not authorized to do this.');
    } else if (error.response.status === 409) {
        alert('Owner validation failed. Check your inputs again');
    } else {
        alert('Owner creation failed. Please try again!');
    }
    }
    finally {
     // setIsLoading(false);
    }
  }, [token, setOwnerData]);

  const handleSubmitFindOwnerByTin = useCallback(async (formData) => {
    try {
      const response = await findOwnerByTin(formData, token?.data); 
      console.log('Owner found successfully', response.data);
      alert('Owner found!');
      setOwnerData(response.data);
 
    } catch (error) {
      console.error('Owner search failed:', error);
      if (error.response.status === 403) {
        alert('You are not authorized to do this.');
    } else if (error.response.status === 404) {
        alert('Owner validation failed. Check your inputs again');
    } else {
        alert('Owner search failed!');
    }
    }
  }, [token, navigate, setOwnerData]);


  const handleSubmitUpdate = useCallback(async (event,setIsLoading) => {
    event.preventDefault();
    setIsLoading(true);
    const formData = new FormData(event.target);

    try {
      const response = await updateOwner(formData, token?.data); 
      if( response.status===200){
        
      console.log('Owner updated successfully', response);
      alert('Owner updated!')
      event.target.reset();
      }
     
      navigate(paths.owner);
    } catch (error) {
      if (error.response.status === 403) {
        alert('You are not authorized to do this.');

    } else if (error.response.status === 409) {
        alert('Owner validation failed. Check your inputs again');
    }
    else if (error.response.status === 404) {
        alert('Owner not found. Check username again!')
    }
    else {
        alert('Owner update failed. Please try again!');
    }
    }
    finally {
      setIsLoading(false);
    }
  }, [token, navigate]);


  const handleSubmitDelete = useCallback(async (event,setIsLoading) => {
    event.preventDefault();
    setIsLoading(true);
    const formData = new FormData(event.target);

    try {
      const response = await deleteOwner(formData, token?.data); // Pass the token here
      console.log('Owner deleted successfully', response);
      alert('Owner deleted!');
      event.target.reset();
      navigate(paths.owner);
    } catch (error) {
      console.error('Owner deletion failed:', error);
      if (error.response.status === 403) {
        alert('You are not authorized to do this.');
    } else if (error.response.status === 409) {
        alert('Owner validation failed. Check your inputs again');
    }
    else if (error.response.status === 404) {
        alert('Owner not found. Check username again!')
    }
     else {
        alert('Owner delete failed. Please try again!');
    }
    }
    finally {
      setIsLoading(false);
    }
  }, [token, navigate]);


  return {
    handleSubmitCreate,
    handleSubmitFindOwnerByTin,
    handleSubmitUpdate,
    handleSubmitDelete
  };
};

export default useOwnerActions;

import { useCallback,useState } from 'react';
import createOwner from '../../api/Owner/User/createOwner';
import deleteOwner from '../../api/Owner/User/deleteOwner';
import updateOwner from '../../api/Owner/User/updateOwner';
import findById from '../../api/Owner/User/findById';
import findOwnerByTin from '../../api/Owner/User/findOwnerByTin';
import {paths} from '../../constants/paths/paths'
import { redirect  } from 'react-router-dom';

const useOwnerActions = (token, navigate) => {


  const handleSubmitCreate = useCallback(async (event,setIsLoading) => {
    event.preventDefault();
    setIsLoading(true);
    const formData = new FormData(event.target);
    try {
      const response = await createOwner(formData, token?.data); 
      console.log('Owner created successfully', response);
      alert('Owner created!');
      event.target.reset();
      navigate(paths.owner);
  
    } catch (error) {
      console.error('Owner creation failed:', error);
      alert('Owner creation failed. Please try again.');
    }
    finally {
      setIsLoading(false);
    }
  }, [token, navigate]);

  
  const [ownerData, setOwnerData] = useState(null);

  const handleSubmitFindOwnerByTin = useCallback(async (event,setIsLoading) => {
    event.preventDefault();
    const formData = new FormData(event.target);

    try {
      const response = await findOwnerByTin(formData, token?.data); // Pass the token here
      console.log('Owner found successfully', response);
      
      alert('Owner found!');
    
      setOwnerData(response.data);
      redirect(paths.ownerProfile);
    } catch (error) {
      console.error('Owner search failed:', error);
      alert('Owner search failed. Please try again.');
    } finally {
      setIsLoading(false);
    }

  }, [token, navigate]);


  const handleSubmitUpdate = useCallback(async (event,setIsLoading) => {
    event.preventDefault();
    setIsLoading(true);
    const formData = new FormData(event.target);

    try {
      const response = await updateOwner(formData, token?.data); 
      console.log('Owner updated successfully', response);
      alert('Owner updated!')
      event.target.reset();
     
      navigate(paths.owner);
    } catch (error) {
      console.error('Owner update failed:', error);
      alert('Owner update failed. Please try again.');
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
      alert('Owner deletion failed. Please try again.');
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

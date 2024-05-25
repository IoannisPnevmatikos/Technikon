import { useCallback } from 'react';
import createOwner from '../../api/Owner/User/createOwner';
import deleteOwner from '../../api/Owner/User/deleteOwner';
import updateOwner from '../../api/Owner/User/updateOwner';
import findById from '../../api/Owner/User/findById';
import findOwnerByTin from '../../api/Owner/User/findOwnerByTin';
import {paths} from '../../constants/paths/paths'

const useOwnerActions = (token, navigate) => {
  const handleSubmitCreate = useCallback(async (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);

    try {
      const response = await createOwner(formData, token?.data); // Pass the token here
      console.log('Owner created successfully', response);
      alert('Owner created!');
      navigate(paths.owner);
    } catch (error) {
      console.error('Owner creation failed:', error);
      alert('Owner creation failed. Please try again.');
    }
  }, [token, navigate]);

  const handleSubmitDelete = useCallback(async (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);

    try {
      const response = await deleteOwner(formData, token?.data); // Pass the token here
      console.log('Owner deleted successfully', response);
      alert('Owner deleted!');
      navigate(paths.owner);
    } catch (error) {
      console.error('Owner deletion failed:', error);
      alert('Owner deletion failed. Please try again.');
    }
  }, [token, navigate]);

  const handleSubmitUpdate = useCallback(async (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);

    try {
      const response = await updateOwner(formData, token?.data); // Pass the token here
      console.log('Owner updated successfully', response);
      alert('Owner updated!');
      navigate(paths.owner);
    } catch (error) {
      console.error('Owner update failed:', error);
      alert('Owner update failed. Please try again.');
    }
  }, [token, navigate]);

  const handleSubmitFindOwnerByTin = useCallback(async (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);

    try {
      const response = await findOwnerByTin(formData, token?.data); // Pass the token here
      console.log('Owner found successfully', response);
      alert('Owner found!');
      navigate(paths.owner);
    } catch (error) {
      console.error('Owner search failed:', error);
      alert('Owner search failed. Please try again.');
    }
  }, [token, navigate]);

  const handleSubmitFindById = useCallback(async () => {

    try {
      const response = await findById(token?.data); // Pass the token here
      console.log('Owner found successfully', response);
      alert('Owner found!');
      navigate(paths.owner);
    } catch (error) {
      console.error('Owner search failed:', error);
      alert('Owner search failed. Please try again.');
    }
  }, [token, navigate]);

  return {
    handleSubmitCreate,
    handleSubmitDelete,
    handleSubmitUpdate,
    handleSubmitFindOwnerByTin,
    handleSubmitFindById
  };
};

export default useOwnerActions;

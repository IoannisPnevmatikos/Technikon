import { useCallback } from 'react';
import createProperty from '../../api/Property/User/createProperty';
import {paths} from '../../constants/paths/paths'

const usePropertyActions = (token, navigate) => {
  const handleSubmitCreate = useCallback(async (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);

    try {
      const response = await createProperty(formData, token?.data); // Pass the token here
      console.log('Property created successfully', response);
      alert('Property created!');
      navigate(paths.property);
    } catch (error) {
      console.error('Property creation failed:', error);
      alert('Property creation failed. Please try again.');
    }
  }, [token, navigate]);

  const handleSubmitDelete = useCallback((event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    console.log(Object.fromEntries(formData.entries()));
  }, []);

  const handleSubmitUpdate = useCallback((event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    console.log(Object.fromEntries(formData.entries()));
  }, []);

  const handleSubmitFindByE9 = useCallback((event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    console.log(Object.fromEntries(formData.entries()).propertyE9); 
  }, []);

  return {
    handleSubmitCreate,
    handleSubmitDelete,
    handleSubmitUpdate,
    handleSubmitFindByE9,
  };
};

export default usePropertyActions;

import { useCallback } from 'react';
import createProperty from '../../../api/Property/Admin/createProperty'
import deleteProperty from '../../../api/Property/Admin/deleteProperty';
import updateProperty from '../../../api/Property/Admin/updatePoperty';
import findPropertiesByTin from '../../../api/Property/Admin/findPropertiesByTin'
import findPropertyByE9 from '../../../api/Property/Admin/findPropertyByE9';
import findBetweenDates from '../../../api/Property/Admin/findBetweenDates';
import {paths} from '../../../constants/paths/paths'

const usePropertyActions = (token, navigate) => {
  const handleSubmitCreate = useCallback(async (event, setIsLoading) => {
    event.preventDefault();
    setIsLoading(true);
    const formData = new FormData(event.target);

    try {
      const response = await createProperty(formData, token?.data); // Pass the token here
      console.log('Property created successfully', response);
      alert('Property created!');
      navigate(paths.adminProperty);
    } catch (error) {
      console.error('Property creation failed:', error);
      alert('Property creation failed. Please try again.');
    } finally {
      setIsLoading(false);
    }
  }, [token, navigate]);

  const handleSubmitDelete = useCallback(async (event, setIsLoading) => {
    event.preventDefault();
    setIsLoading(true);
    const formData = new FormData(event.target);

    try {
      const response = await deleteProperty(formData, token?.data); // Pass the token here
      console.log('Property deleted successfully', response);
      alert('Property deleted!');
      navigate(paths.adminProperty);
    } catch (error) {
      console.error('Property deletion failed:', error);
      alert('Property deletion failed. Please try again.');
    } finally {
      setIsLoading(false);
    }
  }, [token, navigate]);

  const handleSubmitUpdate = useCallback(async (event, setIsLoading) => {
    event.preventDefault();
    setIsLoading(true);
    const formData = new FormData(event.target);

    try {
      const response = await updateProperty(formData, token?.data); // Pass the token here
      console.log('Property updated successfully', response);
      alert('Property updated!');
      navigate(paths.adminProperty);
    } catch (error) {
      console.error('Property update failed:', error);
      alert('Property update failed. Please try again.');
    } finally {
      setIsLoading(false);
    }
  }, [token, navigate]);

  const handleSubmitFindByE9 = useCallback(async (event, setIsLoading) => {
    event.preventDefault();
    setIsLoading(true);
    const formData = new FormData(event.target);

    try {
      const response = await findPropertyByE9(formData, token?.data); // Pass the token here
      console.log('Property found successfully', response);
      alert('Property found!');
      navigate(paths.adminProperty);
    } catch (error) {
      console.error('Property search failed:', error);
      alert('Property search failed. Please try again.');
    } finally {
      setIsLoading(false);
    }
  }, [token, navigate]);

  const handleSubmitFindByTin = useCallback(async (event, setIsLoading) => {
    event.preventDefault();
    setIsLoading(true);
    const formData = new FormData(event.target);

    try {
      const response = await findPropertiesByTin(formData, token?.data); // Pass the token here
      console.log('Properties found successfully', response);
      alert('Properties found!');
      navigate(paths.adminProperty);
    } catch (error) {
      console.error('Properties search failed:', error);
      alert('Properties search failed. Please try again.');
    } finally {
      setIsLoading(false);
    }
  }, [token, navigate]);

  const handleSubmitFindBetweenDates = useCallback(async (event, setIsLoading) => {
    event.preventDefault();
    setIsLoading(true);
    const formData = new FormData(event.target);

    try {
      const response = await findBetweenDates(formData, token?.data); // Pass the token here
      console.log('Properties found successfully', response);
      alert('Properties found!');
      navigate(paths.adminProperty);
    } catch (error) {
      console.error('Properties search failed:', error);
      alert('Properties search failed. Please try again.');
    } finally {
      setIsLoading(false);
    }
  }, [token, navigate]);

  return {
    handleSubmitCreate,
    handleSubmitDelete,
    handleSubmitUpdate,
    handleSubmitFindByE9,
    handleSubmitFindByTin,
    handleSubmitFindBetweenDates
  };
};

export default usePropertyActions;

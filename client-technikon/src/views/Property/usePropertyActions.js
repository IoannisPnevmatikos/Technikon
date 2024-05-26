import { useCallback } from 'react';
import createProperty from '../../api/Property/User/createProperty';
import deleteProperty from '../../api/Property/User/deleteProperty';
import updateProperty from '../../api/Property/User/updatePoperty';
import findMyProperties from '../../api/Property/User/findMyProperties';
import findPropertyByE9 from '../../api/Property/User/findPropertyByE9';
import { paths } from '../../constants/paths/paths';

const usePropertyActions = (token, navigate) => {
  const handleSubmitCreate = useCallback(async (event, setIsLoading) => {
    event.preventDefault();
    setIsLoading(true);
    const formData = new FormData(event.target);

    try {
      const response = await createProperty(formData, token?.data); // Pass the token here
      console.log('Property created successfully', response);
      alert('Property created!');
      navigate(`${paths.property}/${response.data.propertyId}`);
    } catch (error) {
      console.error('Property creation failed:', error);
      if (error.response.status === 409) {
        alert("Property creation failed! A property with the same E9 number already exists.")
      } else if (error.response.status === 400) {
        alert('Property creation failed! Invalid input data.');
      } else if (error.response.status === 403) {
        alert('Property creation failed! You are not currently permitted to do this action.');
      } else if (error.response.status === 404) {
        alert('Property creation failed! Requested tin number is not available.');
      } else {
        alert('Property creation failed!');
      }
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
      navigate(paths.property);
    } catch (error) {
      console.error('Property deletion failed:', error);
      if (error.response.status === 400) {
        alert('Property deletion failed! Invalid input data.');
      } else if (error.response.status === 403) {
        alert('Property deletion failed! You are not currently permitted to do this action.');
      } else if (error.response.status === 404) {
        alert('Property deletion failed! Requested E9 is not available.');
      } else {
        alert('Property deletion failed!');
      }
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
      navigate(`${paths.property}/${response.data.propertyId}`);
    } catch (error) {
      console.error('Property update failed:', error);
      if (error.response.status === 409) {
        alert("Property update failed! A property with the same E9 number already exists.")
      } else if (error.response.status === 400) {
        alert('Property update failed! Invalid input data.');
      } else if (error.response.status === 403) {
        alert('Property update failed! You are not currently permitted to do this action.');
      } else if (error.response.status === 404) {
        alert('Property update failed! Requested E9 does not exist.');
      } else {
        alert('Property update failed!');
      }
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
      navigate(`${paths.property}/${response.data.propertyId}`);
    } catch (error) {
      console.error('Property search failed:', error);
      if (error.response.status === 403) {
        alert('Property search failed! You are not currently permitted to do this action.');
      } else if (error.response.status === 404) {
        alert('Property search failed! Requested E9 does not exist.');
      } else {
        alert('Property search failed!');
      }
    } finally {
      setIsLoading(false);
    }
  }, [token, navigate]);

  const handleFindMyProperties = useCallback(async (setIsLoading) => {
    setIsLoading(true);

    try {
      const response = await findMyProperties(token?.data); // Pass the token here
      console.log('Properties found successfully', response);
      alert('Properties found!');
      navigate(paths.listPropertyResult);
    } catch (error) {
      console.error('Property search failed:', error);
      if (error.response.status === 403) {
        alert('Property search failed! You are not currently permitted to do this action.');
      } else if (error.response.status === 404) {
        alert('Property search failed! No properties found for your account.');
      } else {
        alert('Property search failed!');
      }
    } finally {
      setIsLoading(false);
    }
  }, [token, navigate]);

  return {
    handleSubmitCreate,
    handleSubmitDelete,
    handleSubmitUpdate,
    handleSubmitFindByE9,
    handleFindMyProperties
  };
};

export default usePropertyActions;

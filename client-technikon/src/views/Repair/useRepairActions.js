import { useCallback, useState } from 'react';
import createRepair from '../../api/Repair/User/createRepair';
import updateRepair from '../../api/Repair/User/updateRepair';
import deleteRepair from '../../api/Repair/User/deleteRepair';
import findRepairByDate from '../../api/Repair/User/findRepairByDate';
import findRepairByDateRange from '../../api/Repair/User/findRepairByDateRange';
import MyRepairs from '../../api/Repair/User/MyRepairs';
import { paths } from '../../constants/paths/paths';
//import { encryptData } from './encryptionUtils'; // Import the encryption function

// // Encryption function
// const encryptData = async (data) => {
//   const encoder = new TextEncoder();
//   const dataBuffer = encoder.encode(JSON.stringify(data));
//   const hash = await window.crypto.subtle.digest('SHA-256', dataBuffer);
//   const hashArray = Array.from(new Uint8Array(hash));
//   return hashArray.map(byte => String.fromCharCode(byte)).join('');
// };

// // Decryption function
// const decryptData = async (encryptedData) => {
//   const decoder = new TextDecoder();
//   const encryptedBytes = new Uint8Array(encryptedData.length);
//   for (let i = 0; i < encryptedData.length; i++) {
//     encryptedBytes[i] = encryptedData.charCodeAt(i);
//   }
//   const hash = await window.crypto.subtle.digest('SHA-256', encryptedBytes);
//   const hashArray = Array.from(new Uint8Array(hash));
//   return decoder.decode(Uint8Array.from(hashArray));
// };


const useRepairActions = (token, navigate) => {
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmitCreate = useCallback(async (event) => {
    event.preventDefault();
    setIsLoading(true);
    const formData = new FormData(event.target);
  
    try {
      const response = await createRepair(formData, token?.data); // Pass the token here
      console.log('Repair created successfully', response);
      alert('Repair created!');
      navigate(paths.repair);
    } catch (error) {
      if (error.response) {
        const statusCode = error.response.status;
        switch (statusCode) {
          case 400:
            alert('Bad Request: Please check your input.');
            break;
          case 401:
            alert('Unauthorized: Please log in.');
            break;
          case 403:
            alert('Forbidden: You do not have permission to perform this action.');
            break;
          case 404:
            alert('Not Found: The requested resource could not be found.');
            break;
          case 409:
            alert('Conflict: The resource you are trying to create already exists or there is a conflict with the current state.');
            break;
          case 500:
            alert('Internal Server Error: Please try again later.');
            break;
          default:
            alert(`An error occurred: ${error.response.statusText}`);
            break;
        }
      } else if (error.request) {
        // The request was made but no response was received
        console.error('No response received:', error.request);
        alert('No response from server. Please check your network connection.');
      } else {
        // Something happened in setting up the request that triggered an Error
        console.error('Error setting up request:', error.message);
        alert('An error occurred. Please try again.');
      }
    } finally {
      setIsLoading(false);
    }
  }, [token, navigate]);
  

  const handleSubmitDelete = useCallback(async (event) => {
    event.preventDefault();
    setIsLoading(true);
    const formData = new FormData(event.target);
  
    try {
      const response = await deleteRepair(formData, token?.data); // Pass the token here
      console.log('Repair deleted successfully', response);
       if (error.response.status == 200){
         alert('Repair deleted!');
       }

      console.log(response);

      navigate(paths.repair);
    } catch (error) {
      console.log(error);
      if (error.response) {
        const statusCode = error.response.status;
        switch (statusCode) {
          case 400:
            alert('Bad Request: Please check your input.');
            break;
          case 401:
            alert('Cannot delete. It is in PENDING STATUS or it is not your repair!');
            break;
          case 403:
            alert('Forbidden: You do not have permission to perform this action.');
            break;
          case 404:
            alert('Not Found: The requested resource could not be found.');
            break;
          case 409:
            alert('Conflict: There is a conflict with the current state of the resource.');
            break;
          case 500:
            alert('Internal Server Error: Please try again later.');
            break;
          default:
            alert(`An error occurred: ${error.response.statusText}`);
            break;
        }
      } else if (error.request) {
        // The request was made but no response was received
        console.error('No response received:', error.request);
        alert('No response from server. Please check your network connection.');
      } else {
        // Something happened in setting up the request that triggered an Error
        console.error('Error setting up request:', error.message);
        alert('An error occurred. Please try again.');
      }
    } finally {
      //console.log(response);
      setIsLoading(false);
    }
  }, [token, navigate]);
  

  const handleSubmitUpdate = useCallback(async (event) => {
    event.preventDefault();
    setIsLoading(true);
    const formData = new FormData(event.target);
  
    try {
      const response = await updateRepair(formData, token?.data); // Pass the token here
      console.log('Repair updated successfully', response);
      alert('Repair updated!');
      navigate(paths.repair);
    } catch (error) {
      if (error.response) {
        const statusCode = error.response.status;
        switch (statusCode) {
          case 400:
            alert('Bad Request: Please check your input.');
            break;
          case 401:
            alert('Unauthorized: Please log in.');
            break;
          case 403:
            alert('Forbidden: You do not have permission to perform this action.');
            break;
          case 404:
            alert('Not Found: The requested resource could not be found.');
            break;
          case 409:
            alert('Conflict: There is a conflict with the current state of the resource.');
            break;
          case 500:
            alert('Internal Server Error: Please try again later.');
            break;
          default:
            alert(`An error occurred: ${error.response.statusText}`);
            break;
        }
      } else if (error.request) {
        // The request was made but no response was received
        console.error('No response received:', error.request);
        alert('No response from server. Please check your network connection.');
      } else {
        // Something happened in setting up the request that triggered an Error
        console.error('Error setting up request:', error.message);
        alert('An error occurred. Please try again.');
      }
    } finally {
      setIsLoading(false);
    }
  }, [token, navigate]);
  

  const handleSubmitFindByDate = useCallback(async (event) => {
    event.preventDefault();
    setIsLoading(true);
    const formData = new FormData(event.target);
  
    try {
      const response = await findRepairByDate(formData, token?.data); // Pass the token here
      console.log('Repair(s) found successfully', response);
      navigate(`${paths.repairFetchedData}?data=${JSON.stringify(response.data)}`);
      //navigate(paths.repairFetchedData, { data: response.data }); // Navigate to the new page with fetched data
      alert("Repair(s) found! Check Them!");
    } catch (error) {
      if (error.response) {
        const statusCode = error.response.status;
        switch (statusCode) {
          case 400:
            alert('Bad Request: Please check your input.');
            break;
          case 401:
            alert('Unauthorized: Please log in.');
            break;
          case 403:
            alert('Forbidden: You do not have permission to perform this action.');
            break;
          case 404:
            alert('Not Found: The requested resource could not be found.');
            break;
          case 409:
            alert('Conflict: There is a conflict with the current state of the resource.');
            break;
          case 500:
            alert('Internal Server Error: Please try again later.');
            break;
          default:
            alert(`An error occurred: ${error.response.statusText}`);
            break;
        }
      } else if (error.request) {
        // The request was made but no response was received
        console.error('No response received:', error.request);
        alert('No response from server. Please check your network connection.');
      } else {
        // Something happened in setting up the request that triggered an Error
        console.error('Error setting up request:', error.message);
        alert('An error occurred. Please try again.');
      }
    } finally {
      setIsLoading(false);
    }
  }, [token, navigate]);
  

  const handleSubmitFindByDateRange = useCallback(async (event) => {
    event.preventDefault();
    setIsLoading(true);
    const formData = new FormData(event.target);
  
    try {
      const response = await findRepairByDateRange(formData, token?.data); // Pass the token here
      console.log('Repair(s) found successfully', response);
      navigate(`${paths.repairFetchedData}?data=${JSON.stringify(response.data)}`);
      //navigate(paths.repairFetchedData, { data: response.data }); // Navigate to the new page with fetched data
      alert("Repair(s) found! Check Them!");
    } catch (error) {
      if (error.response) {
        const statusCode = error.response.status;
        switch (statusCode) {
          case 400:
            alert('Bad Request: Please check your input.');
            break;
          case 401:
            alert('Unauthorized: Please log in.');
            break;
          case 403:
            alert('Forbidden: You do not have permission to perform this action.');
            break;
          case 404:
            alert('Not Found: The requested resource could not be found.');
            break;
          case 409:
            alert('Conflict: There is a conflict with the current state of the resource.');
            break;
          case 500:
            alert('Internal Server Error: Please try again later.');
            break;
          default:
            alert(`An error occurred: ${error.response.statusText}`);
            break;
        }
      } else if (error.request) {
        // The request was made but no response was received
        console.error('No response received:', error.request);
        alert('No response from server. Please check your network connection.');
      } else {
        // Something happened in setting up the request that triggered an Error
        console.error('Error setting up request:', error.message);
        alert('An error occurred. Please try again.');
      }
    } finally {
      setIsLoading(false);
    }
  }, [token, navigate]);
  

  const handleSubmitMyRepairs = useCallback(async () => {
    setIsLoading(true);
    try {
      const response = await MyRepairs(token?.data); // Pass the token here
      console.log('Loaded your repairs successfully', response);
      console.log('Response:', response);

      navigate(`${paths.repairFetchedData}?data=${JSON.stringify(response.data)}`);
      //navigate(paths.repairFetchedData, { data: response.data }); // Navigate to the new page with fetched data
      alert("Repair(s) found! Check Them!");
      // navigate(paths.repair);
    } catch (error) {
      if (error.response) {
        const statusCode = error.response.status;
        switch (statusCode) {
          case 400:
            alert('Bad Request: Please check your input.');
            break;
          case 401:
            alert('Unauthorized: Please log in.');
            break;
          case 403:
            alert('Forbidden: You do not have permission to perform this action.');
            break;
          case 404:
            alert('Not Found: The requested resource could not be found.');
            break;
          case 409:
            alert('Conflict: There is a conflict with the current state of the resource.');
            break;
          case 500:
            alert('Internal Server Error: Please try again later.');
            break;
          default:
            alert(`An error occurred: ${error.response.statusText}`);
            break;
        }
      } else if (error.request) {
        // The request was made but no response was received
        console.error('No response received:', error.request);
        alert('No response from server. Please check your network connection.');
      } else {
        // Something happened in setting up the request that triggered an Error
        console.error('Error setting up request:', error.message);
        alert('An error occurred. Please try again.');
      }
    } finally {
      setIsLoading(false);
    }
  }, [token, navigate]);
  

  return {
    handleSubmitCreate,
    handleSubmitDelete,
    handleSubmitUpdate,
    handleSubmitFindByDate,
    handleSubmitFindByDateRange,
    handleSubmitMyRepairs,
    isLoading
  };
};

export default useRepairActions;

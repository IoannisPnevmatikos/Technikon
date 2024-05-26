import { useCallback,useState } from 'react';
import updateRepair from '../../../api/Repair/Admin/updateRepair';
import deleteRepair from '../../../api/Repair/Admin/deleteRepair';
import findRepairByDate from '../../../api/Repair/Admin/findRepairByDate';
import findRepairByDateRange from '../../../api/Repair/Admin/findRepairByDateRange';
import { paths } from '../../../constants/paths/paths'
import findRepairByOwnerTinNumber from '../../../api/Repair/Admin/findRepairByOwnerTinNumber';
import getRepairReport from '../../../api/Repair/Admin/getRepairReport';
import findAllRepairs from '../../../api/Repair/Admin/findAllRepairs';
import createRepair from '../../../api/Repair/Admin/createRepair';

const useRepairActions = (token, navigate) => {
    const [isLoading, setIsLoading] = useState(false);

    const handleSubmitCreate = useCallback(async (event, setIsLoading) => {
        event.preventDefault();
        setIsLoading(true);
        const formData = new FormData(event.target);
      
        try {
          const response = await createRepair(formData, token?.data); // Pass the token here
          console.log('Repair created successfully', response);
          alert('Repair created!');
          navigate(paths.adminRepair);
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
      

      const handleSubmitDelete = useCallback(async (event, setIsLoading) => {
        event.preventDefault();
        setIsLoading(true);
        const formData = new FormData(event.target);
      
        try {
          const response = await deleteRepair(formData, token?.data); // Pass the token here
          console.log('Repair deleted successfully', response);
          alert('Repair deleted!');
          navigate(paths.adminRepair);
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
      

      const handleSubmitUpdate = useCallback(async (event, setIsLoading) => {
        event.preventDefault();
        setIsLoading(true);
        const formData = new FormData(event.target);
      
        try {
          const response = await updateRepair(formData, token?.data); // Pass the token here
          console.log('Repair updated successfully', response);
          alert('Repair updated!');
          navigate(paths.adminRepair);
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
      

      const handleSubmitFindByDate = useCallback(async (event, setIsLoading) => {
        event.preventDefault();
        setIsLoading(true);
        const formData = new FormData(event.target);
      
        try {
          const response = await findRepairByDate(formData, token?.data); // Pass the token here
          console.log('Repair(s) found successfully', response);
          navigate(`${paths.repairFetchedDataAdmin}?data=${JSON.stringify(response.data)}`);
          alert('Repair(s) found!');
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
      

      const handleSubmitFindByDateRange = useCallback(async (event, setIsLoading) => {
        event.preventDefault();
        setIsLoading(true);
        const formData = new FormData(event.target);
      
        try {
          const response = await findRepairByDateRange(formData, token?.data); // Pass the token here
          console.log('Repair(s) found successfully', response);
          navigate(`${paths.repairFetchedDataAdmin}?data=${JSON.stringify(response.data)}`);
          alert('Repair(s) found!');
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
      

      const handleSubmitGetAllRepairs = useCallback(async () => {
        setIsLoading(true);
        try {
          const response = await findAllRepairs(token?.data); // Pass the token here
          console.log('Loaded repairs successfully', response);
          navigate(`${paths.repairFetchedDataAdmin}?data=${JSON.stringify(response.data)}`);
          alert('Loaded repairs!');
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
      

      const handleSubmitFindRepairByOwnerTinNumber = useCallback(async (event, setIsLoading) => {
        event.preventDefault();
        setIsLoading(true);
        const formData = new FormData(event.target);
      
        try {
          const response = await findRepairByOwnerTinNumber(formData, token?.data); // Pass the token here
          console.log('Loaded repairs by owner successfully', response);
          navigate(`${paths.repairFetchedDataAdmin}?data=${JSON.stringify(response.data)}`);
          alert('Loaded repairs by owner!');
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
      

      const handleSubmitGetRepairReport = useCallback(async (event, setIsLoading) => {
        event.preventDefault();
        setIsLoading(true);
        const formData = new FormData(event.target);
      
        try {
          const response = await getRepairReport(formData, token?.data); // Pass the token here
          console.log('Loaded repairs successfully', response.data);
          navigate(`${paths.repairFetchedDataReportAdmin}?data=${JSON.stringify(response.data)}`);
          alert('Loaded repairs!');
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
        handleSubmitGetAllRepairs,
        handleSubmitFindRepairByOwnerTinNumber,
        handleSubmitGetRepairReport
    };
};

export default useRepairActions;

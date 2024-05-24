import { useCallback } from 'react';
import createRepair from '../../api/Repair/User/createRepair';
import updateRepair from '../../api/Repair/User/updateRepair';
import deleteRepair from '../../api/Repair/User/deleteRepair';
import findRepairByDate from '../../api/Repair/User/findRepairByDate';
import findRepairByDateRange from '../../api/Repair/User/findRepairByDateRange';
import {paths} from '../../constants/paths/paths'
import MyRepairs from '../../api/Repair/User/MyRepairs';

const useRepairActions = (token, navigate) => {

    const handleSubmitCreate = useCallback(async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
    
        try {
          const response = await createRepair(formData, token?.data); // Pass the token here
          console.log('Repair created successfully', response);
          alert('Repair created!');
          navigate(paths.repair);
        } catch (error) {
          console.error('Repair creation failed:', error);
          alert('Repair creation failed. Please try again.');
        }
      }, [token, navigate]);

    const handleSubmitDelete = useCallback(async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
    
        try {
            const response = await deleteRepair(formData, token?.data); // Pass the token here
            console.log('Repair deleted successfully', response);
            alert('Repair deleted!');
            navigate(paths.repair);
        } catch (error) {
            console.error('Repair delete failed:', error);
            alert('Repair delete failed. Please try again.');
        }
    }, [token, navigate]);

      const handleSubmitUpdate = useCallback(async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
    
        try {
            const response = await updateRepair(formData, token?.data); // Pass the token here
            console.log('Repair updated successfully', response);
            alert('Repair updated!');
            navigate(paths.repair);
        } catch (error) {
            console.error('Repair update failed:', error);
            alert('Repair update failed. Please try again.');
        }
    }, [token, navigate]);

    const handleSubmitFindByDate = useCallback(async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
    
        try {
            const response = await findRepairByDate(formData, token?.data); // Pass the token here
            console.log('Repair(s) found successfully', response);
            alert('Repair(s) found!');
            navigate(paths.repair);
        } catch (error) {
            console.error('Repair(s) find failed:', error);
            alert('Repair(s) find failed. Please try again.');
        }
    }, [token, navigate]);

    const handleSubmitFindByDateRange = useCallback(async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
    
        try {
            const response = await findRepairByDateRange(formData, token?.data); // Pass the token here
            console.log('Repair(s) found successfully', response);
            alert('Repair(s) found!');
            navigate(paths.repair);
        } catch (error) {
            console.error('Repair(s) find failed:', error);
            alert('Repair find failed. Please try again.');
        }
    }, [token, navigate]);

    const handleSubmitMyRepairs = useCallback(async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
    
        try {
            const response = await MyRepairs(formData, token?.data); // Pass the token here
            console.log('Loaded your repairs successfully', response);
            alert('Loaded repairs!');
            navigate(paths.repair);
        } catch (error) {
            console.error('Repair load failed:', error);
            alert('Repair load failed. Please try again.');
        }
    }, [token, navigate]);

    return {
    handleSubmitCreate,
    handleSubmitDelete,
    handleSubmitUpdate,
    handleSubmitFindByDate,
    handleSubmitFindByDateRange,
    handleSubmitMyRepairs

  };


};

export default useRepairActions;
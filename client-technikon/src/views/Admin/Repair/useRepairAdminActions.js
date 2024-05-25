import { useCallback } from 'react';
import updateRepair from '../../api/Repair/Admin/updateRepair';
import deleteRepair from '../../api/Repair/Admin/deleteRepair';
import findRepairByDate from '../../api/Repair/Admin/findRepairByDate';
import findRepairByDateRange from '../../api/Repair/Admin/findRepairByDateRange';
import { paths } from '../../constants/paths/paths'
import findRepairByOwnerTinNumber from '../../api/Repair/Admin/findRepairByOwnerTinNumber';
import getRepairReport from '../../api/Repair/Admin/getRepairReport';
import findAllRepairs from '../../../api/Repair/Admin/findAllRepairs';
import createRepair from '../../api/Repair/Admin/createRepair';

const useRepairActions = (token, navigate) => {

    const handleSubmitCreate = useCallback(async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);

        try {
            const response = await createRepair(formData, token?.data); // Pass the token here
            console.log('Repair created successfully', response);
            alert('Repair created!');
            //ALLAGHWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW
            navigate(paths.adminRepair);
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
            navigate(paths.adminRepair);
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
            navigate(paths.adminRepair);
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
            navigate(paths.adminRepair);
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
            navigate(paths.adminRepair);
        } catch (error) {
            console.error('Repair(s) find failed:', error);
            alert('Repair find failed. Please try again.');
        }
    }, [token, navigate]);

    const handleSubmitGetAllRepairs = useCallback(async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);

        try {
            const response = await findAllRepairs(formData, token?.data); // Pass the token here
            console.log('Loaded repairs successfully', response);
            alert('Loaded repairs!');
            navigate(paths.adminRepair);
        } catch (error) {
            console.error('Repair load failed:', error);
            alert('Repair load failed. Please try again.');
        }
    }, [token, navigate]);

    const handleSubmitFindRepairByOwnerTinNumber = useCallback(async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);

        try {
            const response = await findRepairByOwnerTinNumber(formData, token?.data); // Pass the token here
            console.log('Loaded repairs by owner successfully', response);
            alert('Loaded repairs by owner!');
            navigate(paths.adminRepair);
        } catch (error) {
            console.error('Repairs by owner load failed:', error);
            alert('Repairs by owner load failed. Please try again.');
        }
    }, [token, navigate]);

    const handleSubmitGetRepairReport = useCallback(async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);

        try {
            const response = await getRepairReport(formData, token?.data); // Pass the token here
            console.log('Loaded repairs successfully', response);
            alert('Loaded repairs!');
            navigate(paths.adminRepair);
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
        handleSubmitGetAllRepairs,
        handleSubmitFindRepairByOwnerTinNumber,
        handleSubmitGetRepairReport

    };


};

export default useRepairActions;
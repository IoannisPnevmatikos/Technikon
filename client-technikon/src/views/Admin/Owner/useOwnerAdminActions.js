import { useCallback, useState } from 'react';
import createOwner from '../../../api/Owner/Admin/createOwner';
import deactivateOwner from '../../../api/Owner/Admin/deactivateOwner'
import deleteOwner from '../../../api/Owner/Admin/deleteOwner';
import findAllOwners from '../../../api/Owner/Admin/findAllOwners';
import findOwnerByDate from '../../../api/Owner/Admin/findOwnerByDate';
import findOwnerByEmail from '../../../api/Owner/Admin/findOwnerByEmail';
import findOwnerByIsActive from '../../../api/Owner/Admin/findOwnerByIsActive';
import { findOwnerByFirstname, findOwnerByLastname } from '../../../api/Owner/Admin/findOwnerByName';
import findOwnerByRole from '../../../api/Owner/Admin/findOwnerByRole'
import findOwnerByTin from '../../../api/Owner/Admin/findOwnerByTin';
import updateOwnerByUsername from '../../../api/Owner/Admin/updateOwnerByUsername';
import { paths } from '../../../constants/paths/paths'
import { useNavigate, redirect } from 'react-router-dom';
import findOwnerByUsername from '../../../api/Owner/Admin/findOwnerByUsername';

const useOwnerActions = (token, navigate, setOwnerData) => {
    // const navigate = useNavigate();
    const handleSubmitCreate = useCallback(async (event, setIsLoading) => {
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
            //  alert('Owner creation failed. Please try again.');
            if (error.response.status === 403) {
                alert('You are not authorized to do this.');
            } else if (error.response.status === 409) {
                alert('Owner validation failed. Check your inputs again');
            } else {
                alert('Owner creation failed. Please try again!');
            }

        }
        finally {
            setIsLoading(false);
        }
    }, [token, navigate]);


    const handleFindAllOwners = useCallback(async (event, setIsLoading) => {
        event.preventDefault();
        setIsLoading(true)
        try {
            const response = await findAllOwners(token?.data);
            // Pass the token here
            console.log('Owners found successfully', response);
            alert('Owners found!');

        } catch (error) {
            console.error('Owner search failed:', error);
            if (error.response.status === 403) {
                alert('You are not authorized to do this.');
            } else if (error.response.status === 409) {
                alert('Owners Get failed.');
            } else {
                alert('Oops. Something went wrong!');
            }
        } finally {
            setIsLoading(false);
        }

    }, [token]);

    const handleSubmitFindOwnerByDate = useCallback(async (formData) => {
        //  event.preventDefault();
        //    setIsLoading(true);
        try {
            const response = await findOwnerByDate(formData, token?.data); // Pass the token here
            console.log('Owners found successfully', response);
            alert('Owners found!');
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

        } finally {
            //   setIsLoading(false);
        }

    }, [token, setOwnerData]);

    const handleSubmitFindOwnerByEmail = useCallback(async (formData) => {
        try {
            const response = await findOwnerByEmail(formData, token?.data); // Pass the token here
            console.log('Owner found successfully', response.data);
            alert('Owner found!');
            setOwnerData(response.data);

        } catch (error) {
            console.error('Owner search failed:', error);
            if (error.response.status === 403) {
                alert('You are not authorized to do this.');
            } else if (error.response.status === 404) {
                alert('Check your inputs again');
            } else {
                alert('Owner search failed!');
            }
        }

    }, [token, navigate, setOwnerData]);


    const handleSubmitFindOwnerByIsActive = useCallback(async (formData) => {
        try {
            const response = await findOwnerByIsActive(formData, token?.data); // Pass the token here
            console.log('Owner found successfully', response);
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


    const handleSubmitFindOwnerByFirstname = useCallback(async (formData) => {
        try {
            const response = await findOwnerByFirstname(formData, token?.data);
            console.log('Owner found successfully', response);
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

    const handleSubmitFindOwnerByLastname = useCallback(async (formData) => {
        try {
            const response = await findOwnerByLastname(formData, token?.data);
            console.log('Owner found successfully', response);
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

    const handleSubmitFindOwnerByRole = useCallback(async (formData) => {
        try {
            const response = await findOwnerByRole(formData, token?.data);
            console.log('Owner found successfully', response);

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



    const handleSubmitFindOwnerByUsername = useCallback(async (formData) => {

        try {
            const response = await findOwnerByUsername(formData, token?.data);
            console.log('Owner found successfully', response);
            alert('Owner found!');
            setOwnerData(response.data);
            //  redirect(paths.ownerProfile);
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

    const handleDeactivateOwner = useCallback(async (event, setIsLoading) => {
        event.preventDefault();
        setIsLoading(true);
        const formData = new FormData(event.target);

        try {
            const response = await deactivateOwner(formData, token?.data);
            console.log('Owner deacivated successfully', response);
            alert('Owner deacivated!');
            // setOwnerData(response.data);
            navigate(paths.owner);
        } catch (error) {
            console.error('Owner search failed:', error);
            if (error.response.status === 403) {
                alert('You are not authorized to do this.');
            } else if (error.response.status === 404) {
                alert('Owner validation failed. Check your inputs again');
            } else {
                alert('Owner search failed!');
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
            const response = await updateOwnerByUsername(formData, token?.data);
            console.log('Owner UPDATED successfully', response);
            alert('Owner UPDATED!');
            event.target.reset();
            navigate(paths.owner);

        } catch (error) {
            console.error('Owner creation failed:', error);
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



    const handleSubmitDelete = useCallback(async (event, setIsLoading) => {
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
        handleFindAllOwners,
        handleSubmitFindOwnerByDate,
        handleSubmitFindOwnerByEmail,
        handleSubmitFindOwnerByIsActive,
        handleSubmitFindOwnerByFirstname,
        handleSubmitFindOwnerByLastname,
        handleSubmitFindOwnerByRole,
        handleSubmitFindOwnerByTin,
        handleSubmitFindOwnerByUsername,
        handleDeactivateOwner,
        handleSubmitDelete,
        handleSubmitUpdate,
    };
};

export default useOwnerActions;

import React, { useState } from 'react';
import { Container, Typography, Box, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { paths } from '../../constants/paths/paths';
import useToken from '../../stores/useToken';
import CreateOwnerForm from './OwnerForms/CreateOwnerForm';
import FindAllOwners from './OwnerForms/FindAllOwners';
import FindOwnerByDateForm from './OwnerForms/FindOwnerByDateForm';
import FindOwnerByEmailForm from './OwnerForms/FindOwnerByEmailForm';
import FindOwnerByIsActiveForm from './OwnerForms/FindOwnerByIsActiveForm';
import FindOwnerByTinForm from './OwnerForms/FindOwnerByTinForm';
import FindOwnerByFirstnameForm from './OwnerForms/FindOwnerByFirstnameForm';
import FindOwnerByLastnameForm from './OwnerForms/FindOwnerByLastnameForm';
import FindOwnerByRoleForm from './OwnerForms/FindOwnerByRoleForm';
import FindOwnerByUsernameForm from './OwnerForms/FindOwnerByUsernameForm';
import UpdateOwnerForm from './OwnerForms/UpdateOwnerForm';
import DeleteOwnerForm from './OwnerForms/DeleteOwnerForm';
import DeactivateOwnerForm from './OwnerForms/DeactivateOwnerForm';

import useOwnerActions from './useOwnerAdminActions';

function OwnerAdmin() {

    const [activeForm, setActiveForm] = useState('');
    const navigate = useNavigate();
    const { token } = useToken();
    const {
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
    } = useOwnerActions(token, navigate);

    const handleBackClick = () => {
        setActiveForm('');
        navigate(paths.owner);
    };

    const renderForm = () => {
        switch (activeForm) {
            case 'createOwner':
                return <CreateOwnerForm handleSubmit={handleSubmitCreate} handleBackClick={handleBackClick} />;
            case 'findAllOwners':
                return <FindAllOwners handleSubmit={handleFindAllOwners} handleBackClick={handleBackClick} />;
            case 'findOwnerByDate':
                return <FindOwnerByDateForm handleSubmit={handleSubmitFindOwnerByDate} handleBackClick={handleBackClick} />;
            case 'findOwnerByEmail':
                return <FindOwnerByEmailForm handleSubmit={handleSubmitFindOwnerByEmail} handleBackClick={handleBackClick} />;
            case 'findOwnerByIsActive':
                return <FindOwnerByIsActiveForm handleSubmit={handleSubmitFindOwnerByIsActive} handleBackClick={handleBackClick} />;
            case 'findOwnerByTin':
                return <FindOwnerByTinForm handleSubmit={handleSubmitFindOwnerByTin} handleBackClick={handleBackClick} />;
            case 'findOwnerByFirstname':
                return <FindOwnerByFirstnameForm handleSubmit={handleSubmitFindOwnerByFirstname} handleBackClick={handleBackClick} />;
            case 'findOwnerByLastname':
                return <FindOwnerByLastnameForm handleSubmit={handleSubmitFindOwnerByLastname} handleBackClick={handleBackClick} />;
            case 'findOwnerByRole':
                return <FindOwnerByRoleForm handleSubmit={handleSubmitFindOwnerByRole} handleBackClick={handleBackClick} />;
            case 'findOwnerByUsername':
                return <FindOwnerByUsernameForm handleSubmit={handleSubmitFindOwnerByUsername} handleBackClick={handleBackClick} />;
            case 'updateOwner':
                return <UpdateOwnerForm handleSubmit={handleSubmitUpdate} handleBackClick={handleBackClick} />;
            case 'deleteOwner':
                return <DeleteOwnerForm handleSubmit={handleSubmitDelete} handleBackClick={handleBackClick} />;
            case 'deactivateOwner' :
                return <DeactivateOwnerForm handleSubmit={handleDeactivateOwner} handleBackClick={handleBackClick}/>;
            default:
                return null;
        }
    };

    return (
        <Container maxWidth="md" sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', marginTop: '64px' }}>
            <Box sx={{ mt: 8, width: '100%', textAlign: 'center' }}>
                <Typography variant="h4" gutterBottom>
                    Owner Actions
                </Typography>
                {activeForm ? (
                    renderForm()
                ) : (
                    <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2, mt: 4 }}>
                        <Button variant="contained" color="primary" onClick={() => setActiveForm('createOwner')}>
                            Create Owner
                        </Button>
                        <Button variant="contained" color="primary" onClick={() => setActiveForm('findOwnerByTin')}>
                            Find Owner
                        </Button>
                        <Button variant="contained" color="primary" onClick={() => setActiveForm('updateOwner')}>
                            Update  Owner
                        </Button>
                        <Button variant="contained" color="primary" onClick={() => setActiveForm('deleteOwner')}>
                            Delete a Owner
                        </Button>
                    </Box>
                )}
            </Box>
        </Container>
    );
}

export default OwnerAdmin;
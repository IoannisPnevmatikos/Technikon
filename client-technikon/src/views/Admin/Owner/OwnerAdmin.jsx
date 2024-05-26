import React, { useState } from 'react';
import { Container, Typography, Box, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { paths } from '../../../constants/paths/paths';
import useToken from '../../../stores/useToken'
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
import OwnerProfile from '../../Owner/OwnerProfile';
import OwnerList from '../../Owner/OwnerList'

import useOwnerActions from './useOwnerAdminActions';

function OwnerAdmin() {

    const [activeForm, setActiveForm] = useState('');
    const navigate = useNavigate();
    const { token } = useToken();
    const [ownerData, setOwnerData] = useState(null);
    const {
        handleSubmitCreate,
        handleFindAllOwners,
        handleDeactivateOwner,
        handleSubmitDelete,
        handleSubmitUpdate,
    } = useOwnerActions(token, navigate);

    const { handleSubmitFindOwnerByTin,
        handleSubmitFindOwnerByEmail,
        handleSubmitFindOwnerByIsActive,
        handleSubmitFindOwnerByDate,
        handleSubmitFindOwnerByFirstname,
        handleSubmitFindOwnerByLastname,
        handleSubmitFindOwnerByRole,
        handleSubmitFindOwnerByUsername, } = useOwnerActions(token, navigate, setOwnerData);
    
        const handleBackClick = () => {
        setActiveForm('');
        navigate(paths.adminOwner);
    };

    const renderForm = () => {
        switch (activeForm) {
            case 'createOwner':
                return <CreateOwnerForm handleSubmit={handleSubmitCreate} handleBackClick={handleBackClick} />;
            case 'findAllOwners':
                return <FindAllOwners handleSubmit={handleFindAllOwners} handleBackClick={handleBackClick} />;
            case 'findOwnerByDate':
                return (<><FindOwnerByDateForm handleSubmit={handleSubmitFindOwnerByDate} handleBackClick={handleBackClick} />
                   {ownerData && (<OwnerList data={ownerData} />)}  </>);
            case 'findOwnerByEmail':
                return (<><FindOwnerByEmailForm handleSubmit={handleSubmitFindOwnerByEmail} handleBackClick={handleBackClick} />
                    {ownerData && (<OwnerProfile data={ownerData} />)}  </>);
            case 'findOwnerByIsActive':
                return (<><FindOwnerByIsActiveForm handleSubmit={handleSubmitFindOwnerByIsActive} handleBackClick={handleBackClick} />
                    {ownerData && (<OwnerList data={ownerData} />)}  </>);
            case 'findOwnerByTin':
                return (<><FindOwnerByTinForm handleSubmit={handleSubmitFindOwnerByTin} handleBackClick={handleBackClick} />
                    {ownerData && (<OwnerProfile data={ownerData} />)}    </>);
            case 'findOwnerByFirstname':
                return (<><FindOwnerByFirstnameForm handleSubmit={handleSubmitFindOwnerByFirstname} handleBackClick={handleBackClick} />
                  {ownerData && (<OwnerProfile data={ownerData} />)}    </>);
            case 'findOwnerByLastname':
                return(<> <FindOwnerByLastnameForm handleSubmit={handleSubmitFindOwnerByLastname} handleBackClick={handleBackClick} />
                      {ownerData && (<OwnerProfile data={ownerData} />)}  </>);
            case 'findOwnerByRole':
                return( <><FindOwnerByRoleForm handleSubmit={handleSubmitFindOwnerByRole} handleBackClick={handleBackClick} />
                {ownerData && (<OwnerList data={ownerData} />)}  </>);
            case 'findOwnerByUsername':
                return (<><FindOwnerByUsernameForm handleSubmit={handleSubmitFindOwnerByUsername} handleBackClick={handleBackClick} />
                {ownerData && (<OwnerProfile data={ownerData} />)}  </>);
            case 'updateOwner':
                return <UpdateOwnerForm handleSubmit={handleSubmitUpdate} handleBackClick={handleBackClick} />;
            case 'deleteOwner':
                return <DeleteOwnerForm handleSubmit={handleSubmitDelete} handleBackClick={handleBackClick} />;
            case 'deactivateOwner':
                return <DeactivateOwnerForm handleSubmit={handleDeactivateOwner} handleBackClick={handleBackClick} />;
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
                        <Button variant="contained" color="primary" onClick={() => setActiveForm('findAllOwners')}>
                            Find All Owners
                        </Button>
                        <Button variant="contained" color="primary" onClick={() => setActiveForm('findOwnerByTin')}>
                            Find Owner by TIN
                        </Button>
                        <Button variant="contained" color="primary" onClick={() => setActiveForm('findOwnerByFirstname')}>
                            Find Owner by Firstname
                        </Button>
                        <Button variant="contained" color="primary" onClick={() => setActiveForm('findOwnerByLastname')}>
                            Find Owner by Lastname
                        </Button>
                        <Button variant="contained" color="primary" onClick={() => setActiveForm('findOwnerByDate')}>
                            Find Owner by Date
                        </Button>
                        <Button variant="contained" color="primary" onClick={() => setActiveForm('findOwnerByEmail')}>
                            Find Owner by Email
                        </Button>
                        <Button variant="contained" color="primary" onClick={() => setActiveForm('findOwnerByIsActive')}>
                            Find Owner by IsActive
                        </Button>
                        <Button variant="contained" color="primary" onClick={() => setActiveForm('findOwnerByRole')}>
                            Find Owner by Role
                        </Button>
                        <Button variant="contained" color="primary" onClick={() => setActiveForm('findOwnerByActive')}>
                            Find Owner by Active
                        </Button>
                        <Button variant="contained" color="primary" onClick={() => setActiveForm('findOwnerByUsername')}>
                            Find Owner by Username
                        </Button>
                        <Button variant="contained" color="primary" onClick={() => setActiveForm('updateOwner')}>
                            Update Owner
                        </Button>

                        <Button variant="contained" color="primary" onClick={() => setActiveForm('deactivateOwner')}>
                            Deactivate Owner
                        </Button>
                        <Button variant="contained" color="primary" onClick={() => setActiveForm('deleteOwner')}>
                            Delete Owner
                        </Button>
                    </Box>
                )}
            </Box>
        </Container>
    );
}

export default OwnerAdmin;
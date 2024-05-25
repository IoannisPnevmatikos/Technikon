import React, { useState } from 'react';
import { Container, Typography, Box, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { paths } from '../../../constants/paths/paths';
import useToken from '../../../stores/useToken'
import CreatePropertyForm from './PropertyForms/CreatePropertyForm';
import FindPropertyByE9Form from './PropertyForms/FindPropertyByE9Form';
import UpdatePropertyForm from './PropertyForms/UpdatePropertyForm';
import DeletePropertyForm from './PropertyForms/DeletePropertyForm';
import FindOwnerPropertiesForm from './PropertyForms/FindOwnerPropertiesForm'; // New form component
import FindPropertiesBetweenForm from './PropertyForms/FindPropertiesBetweenForm'; // New form component
import usePropertyActions from './usePropertyAdminActions';

function PropertyAdmin() {
  const [activeForm, setActiveForm] = useState('');
  const navigate = useNavigate();
  const { token } = useToken();
  
  const {
    handleSubmitCreate,
    handleSubmitDelete,
    handleSubmitUpdate,
    handleSubmitFindByE9,
    handleSubmitFindByTin, // New function for "Find Owner Properties by TIN Number" option
    handleSubmitFindBetweenDates, // New function for "Find Properties Registered Between" option
  } = usePropertyActions(token, navigate);

  const handleBackClick = () => {
    setActiveForm('');
    navigate(paths.adminProperty);
  };

  const renderForm = () => {
    switch (activeForm) {
      case 'createProperty':
        return <CreatePropertyForm handleSubmit={handleSubmitCreate} handleBackClick={handleBackClick} />;
      case 'findPropertyByE9':
        return <FindPropertyByE9Form handleSubmit={handleSubmitFindByE9} handleBackClick={handleBackClick} />;
      case 'updateProperty':
        return <UpdatePropertyForm handleSubmit={handleSubmitUpdate} handleBackClick={handleBackClick} />;
      case 'deleteProperty':
        return <DeletePropertyForm handleSubmit={handleSubmitDelete} handleBackClick={handleBackClick} />;
      case 'findOwnerProperties':
        return <FindOwnerPropertiesForm handleSubmit={handleSubmitFindByTin} handleBackClick={handleBackClick} />; // New form
      case 'findPropertiesBetween':
        return <FindPropertiesBetweenForm handleSubmit={handleSubmitFindBetweenDates} handleBackClick={handleBackClick} />; // New form
      default:
        return null;
    }
  };

  return (
    <Container maxWidth="md" sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', marginTop: '64px' }}>
      <Box sx={{ mt: 8, width: '100%', textAlign: 'center' }}>
        <Typography variant="h4" gutterBottom>
          Property Actions
        </Typography>
        {activeForm ? (
          renderForm()
        ) : (
          <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2, mt: 4 }}>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('createProperty')}>
              Create a Property
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('findOwnerProperties')}>
              Find Owner Properties by TIN Number
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('findPropertiesBetween')}>
              Find Properties Registered Between
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('findPropertyByE9')}>
              Find a Property by E9
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('updateProperty')}>
              Update a Property
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('deleteProperty')}>
              Delete a Property
            </Button>
          </Box>
        )}
      </Box>
    </Container>
  );
}

export default PropertyAdmin;

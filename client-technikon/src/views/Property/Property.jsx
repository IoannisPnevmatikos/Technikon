import React, { useState } from 'react';
import { Container, Typography, Box, Button, CircularProgress } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { paths } from '../../constants/paths/paths';
import useToken from '../../stores/useToken';
import CreatePropertyForm from './PropertyForms/CreatePropertyForm';
import FindPropertyByE9Form from './PropertyForms/FindPropertyByE9Form';
import UpdatePropertyForm from './PropertyForms/UpdatePropertyForm';
import DeletePropertyForm from './PropertyForms/DeletePropertyForm';
import usePropertyActions from './usePropertyActions';

function Property() {
  const [activeForm, setActiveForm] = useState('');
  const navigate = useNavigate();
  const { token } = useToken();
  const [isLoading, setIsLoading] = useState(false);

  const {
    handleSubmitCreate,
    handleSubmitDelete,
    handleSubmitUpdate,
    handleSubmitFindByE9,
    handleFindMyProperties, 
  } = usePropertyActions(token, navigate);

  const onSubmitHandleFindMyProperties = () => handleFindMyProperties(setIsLoading);


  const handleBackClick = () => {
    setActiveForm('');
    navigate(paths.property);
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
            <Button variant="contained" color="primary" onClick={() => setActiveForm('createProperty')} disabled={isLoading}>
              Create a Property
            </Button>
            <Button variant="contained" color="primary" onClick={() => onSubmitHandleFindMyProperties()} disabled={isLoading}>
              {isLoading ? 'Finding My Properties...' : 'Find My Properties'}
              {isLoading && (
                <CircularProgress size={24} sx={{ position: 'absolute', top: '50%', left: '50%', marginTop: '-12px', marginLeft: '-12px' }} />
              )}
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('findPropertyByE9')} disabled={isLoading}>
              Find a Property by E9
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('updateProperty')} disabled={isLoading}>
              Update a Property
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('deleteProperty')} disabled={isLoading}>
              Delete a Property
            </Button>
          </Box>
        )}
      </Box>
    </Container>
  );
}

export default Property;

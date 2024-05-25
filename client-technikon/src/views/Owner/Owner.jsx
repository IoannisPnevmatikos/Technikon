import React, { useState } from 'react';
import { Container, Typography, Box, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { paths } from '../../constants/paths/paths';
import useToken from '../../stores/useToken';
import CreateOwnerForm from './Forms/CreateOwnerForm';
import FindOwnerByTinForm from './Forms/FindOwnerByTinForm';
import UpdateOwnerForm from './Forms/UpdateOwnerForm';
import DeleteOwnerForm from './Forms/DeleteOwnerForm';
import useOwnerActions from './useOwnerActions';

function Owner() {
  const [activeForm, setActiveForm] = useState('');
  const navigate = useNavigate();
  const { token } = useToken();
  const {
    handleSubmitCreate,
    handleSubmitDelete,
    handleSubmitUpdate,
    handleSubmitFindOwnerByTin,
    handleSubmitFindById
  } = useOwnerActions(token, navigate);

  const handleBackClick = () => {
    setActiveForm('');
    navigate(paths.owner);
  };

  const renderForm = () => {
    switch (activeForm) {
      case 'createOwnerForm':
        return <CreateOwnerForm handleSubmit={handleSubmitCreate} handleBackClick={handleBackClick} />;
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
              Form Owner
            </Button>
            <Button variant="contained" color="primary" onClick={() => handleOwnerForm()}>
              Submit Owner
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

export default Owner;
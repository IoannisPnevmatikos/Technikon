import React, { useState } from 'react';
import { Container, Typography, Box, Button, CircularProgress,TextField, MenuItem } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import FindRepairByDateForm from './RepairForms/FindRepairByDateForm';
import FindRepairByDateRangeForm from './RepairForms/FindRepairByDateRangeForm';
import useToken from '../../stores/useToken';
import UpdateRepairForm from './RepairForms/UpdateRepairForm';
import CreateRepairForm from './RepairForms/CreateRepairForm';
import DeleteRepairForm from './RepairForms/DeleteRepairForm';
import useRepairActions from './useRepairActions';
import { paths } from '../../constants/paths/paths';

function RepairActionsPage() {
  const [activeForm, setActiveForm] = useState('');
  const navigate = useNavigate();
  const { token } = useToken();
  const [isLoading, setIsLoading] = useState(false);
  const {
    handleSubmitCreate,
    handleSubmitDelete,
    handleSubmitUpdate,
    handleSubmitFindByDate,
    handleSubmitFindByDateRange,
    handleSubmitMyRepairs
  } = useRepairActions(token, navigate);


  const onSubmithandleSubmitMyRepairs = () => handleSubmitMyRepairs(setIsLoading);
  
  
  const handleBackClick = () => {
    setActiveForm('');
    navigate(paths.repair); // This will update the URL to /repairs
  };

  const renderForm = () => {
    switch (activeForm) {
      case 'createRepair':
        return <CreateRepairForm handleSubmit={handleSubmitCreate} handleBackClick={handleBackClick} />;
      case 'findRepairByDate':
        return <FindRepairByDateForm handleSubmit={handleSubmitFindByDate} handleBackClick={handleBackClick} />;
      case 'findRepairByDateRange':
        return <FindRepairByDateRangeForm handleSubmit={handleSubmitFindByDateRange} handleBackClick={handleBackClick} />;
      case 'updateRepair':
        return <UpdateRepairForm handleSubmit={handleSubmitUpdate} handleBackClick={handleBackClick} />;
      case 'deleteRepair':
        return <DeleteRepairForm handleSubmit={handleSubmitDelete} handleBackClick={handleBackClick} />;
      default:
        return null;
    }
  };

  return (
    <Container maxWidth="md" sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', marginTop: '64px' }}>
      <Box sx={{ mt: 8, width: '100%', textAlign: 'center' }}>
        <Typography variant="h4" gutterBottom>
          Repair Actions
        </Typography>
        {activeForm ? (
          renderForm()
        ) : (
          <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2, mt: 4 }}>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('createRepair')} disabled={isLoading}>
              Create Repair
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('findRepairByDate')} disabled={isLoading}>
              Find Repair by Date
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('findRepairByDateRange')} disabled={isLoading}>
              Find Repair by Date Range
            </Button>
            <Button variant="contained" color="primary" onClick={() => onSubmithandleSubmitMyRepairs()}>
              {isLoading ? 'Finding My Repairs...' : 'Find My Repairs'}
              {isLoading && (
                <CircularProgress size={24} sx={{ position: 'absolute', top: '50%', left: '50%', marginTop: '-12px', marginLeft: '-12px' }} />
              )}
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('updateRepair')} disabled={isLoading}>
              Update Repair
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('deleteRepair')} disabled={isLoading}>
              Delete Repair
            </Button>
          </Box>
        )}
      </Box>
    </Container>
  );
}

export default RepairActionsPage;
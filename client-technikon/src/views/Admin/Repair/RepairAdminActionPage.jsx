import React, { useState } from 'react';
import { Container, Typography, Box, Button, TextField, MenuItem } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import useToken from '../../../stores/useToken';
import FindRepairByDateForm from './RepairForms/FindRepairByDateForm';
import FindRepairByDateRangeForm from './RepairForms/FindRepairByDateRangeForm';
import FindRepairByOwnerTinNumberForm from './RepairForms/FindRepairByOwnerTinNumberForm';
import UpdateRepairForm from './RepairForms/UpdateRepairForm';
import CreateRepairForm from './RepairForms/CreateRepairForm';
import DeleteRepairForm from './RepairForms/DeleteRepairForm';
import GetRepairReportForm from './RepairForms/GetRepairReportForm';
import useRepairAdminActions from './useRepairAdminActions';


function RepairAdminActionsPage() {
  const [activeForm, setActiveForm] = useState('');
  const navigate = useNavigate();
  const { token } = useToken();
  const {
    handleSubmitCreate,
    handleSubmitDelete,
    handleSubmitUpdate,
    handleSubmitFindByDate,
    handleSubmitFindByDateRange,
    handleSubmitGetAllRepairs,
    handleSubmitFindRepairByOwnerTinNumber,
    handleSubmitGetRepairReport

  } = useRepairAdminActions(token, navigate);


  const handleBackClick = () => {
    setActiveForm('');
    navigate(paths.adminRepair); // This will update the URL to /repairs
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
      case 'findRepairByOwnerTinNumber':
        return <FindRepairByOwnerTinNumberForm handleSubmit={handleSubmitFindRepairByOwnerTinNumber} handleBackClick={handleBackClick} />;
      case 'getRepairReport':
        return <GetRepairReportForm handleSubmit={handleSubmitGetRepairReport} handleBackClick={handleBackClick} />;
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
            <Button variant="contained" color="primary" onClick={() => setActiveForm('createRepair')}>
              Create Repair
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('updateRepair')}>
              Update Repair
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('findRepairByDate')}>
              Find Repair by Date
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('findRepairByDateRange')}>
              Find Repair by Date Range
            </Button>
            <Button variant="contained" color="primary" onClick={() => handleSubmitGetAllRepairs()}>
              All Repairs
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('findRepairByOwnerTinNumber')}>
              Find Repair By Owner Tin Number
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('deleteRepair')}>
              Delete Repair
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('getRepairReport')}>
              Get Repair Report
            </Button>
          </Box>
        )}
      </Box>
    </Container>
  );
}

export default RepairAdminActionsPage;
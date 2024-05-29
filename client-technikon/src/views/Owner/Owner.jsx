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
import OwnerProfile from './OwnerProfile'

function Owner() {
  
  const [activeForm, setActiveForm] = useState('');
  const navigate = useNavigate();
  const { token } = useToken();
  const [ownerData, setOwnerData] = useState(null);
  const {
    handleSubmitCreate,
    handleSubmitDelete,
    handleSubmitUpdate,
  } = useOwnerActions(token, navigate);

  const {handleSubmitFindOwnerByTin} = useOwnerActions(token,navigate,setOwnerData)

  const handleBackClick = () => {
    setActiveForm('');
   // setOwnerData(null)
    navigate(paths.owner);
  };//

  const renderForm = () => {
    switch (activeForm) {
      case 'createOwner':
        return (<>
        <CreateOwnerForm handleSubmit={handleSubmitCreate} handleBackClick={handleBackClick} />
        {ownerData && (<OwnerProfile data={ownerData}/>)}
        </>);
        case 'findOwnerByTin':
        return (<><FindOwnerByTinForm handleSubmit={handleSubmitFindOwnerByTin} handleBackClick={handleBackClick} />
         {ownerData && (<OwnerProfile data={ownerData} />)}  </>);
      case 'updateOwner':
        return <UpdateOwnerForm handleSubmit={handleSubmitUpdate} handleBackClick={handleBackClick} />;
      case 'deleteOwner':
        return <DeleteOwnerForm handleSubmit={handleSubmitDelete} handleBackClick={handleBackClick} />;
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

export default Owner;
import React from 'react';
import { Container, Typography, Box, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { paths } from '../../constants/paths/paths';

function Admin() {
  const navigate = useNavigate();

  return (
    <Container maxWidth="md" sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', marginTop: '64px' }}>
      <Box sx={{ mt: 8, width: '100%', textAlign: 'center' }}>
        <Typography variant="h4" gutterBottom>
          Admin Actions
        </Typography>
        <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2, mt: 4 }}>
          <Button variant="contained" color="primary" onClick={() => navigate(paths.adminOwner)}>
            Manage Owners
          </Button>
          <Button variant="contained" color="primary" onClick={() => navigate(paths.adminProperty)}>
            Manage Properties
          </Button>
          <Button variant="contained" color="primary" onClick={() => navigate('/admin/repair')}>
            Manage Repairs
          </Button>
        </Box>
      </Box>
    </Container>
  );
}

export default Admin;

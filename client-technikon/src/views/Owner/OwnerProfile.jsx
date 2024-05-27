import React from 'react';
import { Container, Typography, Grid, Paper } from '@mui/material';

const OwnerProfile = (owner) =>{
  const obj = owner.data  
  return (
    <Container maxWidth="lg" >
      <Grid container spacing={3}>
        <Grid item xs={12}>
          <Paper >
            <Typography variant="h4">Profile Information</Typography>
            <Typography variant="subtitle1">
              <strong>First Name:</strong> {obj.firstName}
            </Typography>
            <Typography variant="subtitle1">
              <strong>Last Name:</strong> {obj.lastName}
            </Typography>
            <Typography variant="subtitle1">
              <strong>Username:</strong> {obj.username}
            </Typography>
            <Typography variant="subtitle1">
              <strong>Email:</strong> {obj.email}
            </Typography>
            <Typography variant="subtitle1">
              <strong>Address:</strong> {obj.address}
            </Typography>
            <Typography variant="subtitle1">
              <strong>Phone:</strong> {obj.phone}
            </Typography>
          </Paper>
        </Grid>
      </Grid>
    </Container>
  );
}
export default OwnerProfile
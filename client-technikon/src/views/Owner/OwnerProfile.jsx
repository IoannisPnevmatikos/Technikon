import React from 'react';
import { Container, Typography, Grid, Paper } from '@mui/material';

const OwnerProfile = (owner) =>{
    console.log(owner)
  return (
    <Container maxWidth="lg" >
      <Grid container spacing={3}>
        <Grid item xs={12}>
          <Paper >
            <Typography variant="h4">Profile Information</Typography>
            <Typography variant="subtitle1">
              <strong>First Name:</strong> {owner.firstName}
            </Typography>
            <Typography variant="subtitle1">
              <strong>Last Name:</strong> {owner.lastName}
            </Typography>
            <Typography variant="subtitle1">
              <strong>Username:</strong> {owner.username}
            </Typography>
            <Typography variant="subtitle1">
              <strong>Email:</strong> {owner.email}
            </Typography>
            <Typography variant="subtitle1">
              <strong>Address:</strong> {owner.address}
            </Typography>
            <Typography variant="subtitle1">
              <strong>Phone:</strong> {owner.phone}
            </Typography>
          </Paper>
        </Grid>
      </Grid>
    </Container>
  );
}
export default OwnerProfile
import React from 'react';
import { Box, Button, TextField, MenuItem } from '@mui/material';

const UpdatePropertyForm = ({ handleSubmit, handleBackClick }) => (
  <Box component="form" sx={{ mt: 3, width: '100%' }} onSubmit={handleSubmit}>
    <TextField
      name="id"
      label="Property ID"
      type="number"
      fullWidth
      margin="normal"
      required
      inputProps={{ pattern: "\\d*", min: "0" }}
    />
    <TextField
      name="propertyE9"
      label="Property E9"
      fullWidth
      margin="normal"
      inputProps={{ maxLength: 11, pattern: "[0-9]{11}" }}
    />
    <TextField
      name="address"
      label="Address"
      fullWidth
      margin="normal"
      inputProps={{ pattern: "[a-zA-Z0-9\\s,.-]+" }}
    />
    <TextField
      name="yearOfConstruction"
      label="Year of Construction"
      type="number"
      fullWidth
      margin="normal"
      inputProps={{ maxLength: 4 }}
    />
    <TextField
      name="typeOfProperty"
      label="Type of Property"
      select
      fullWidth
      margin="normal"
    >
      <MenuItem value="DETACHED_HOUSE">DETACHED_HOUSE</MenuItem>
      <MenuItem value="MAISONETTE">MAISONETTE</MenuItem>
      <MenuItem value="APARTMENT_BUILDING">APARTMENT_BUILDING</MenuItem>
    </TextField>
    <TextField
      name="photoURL"
      label="Photo URL"
      fullWidth
      margin="normal"
    />
    <TextField
      name="latitude"
      label="Map Location (Latitude)"
      type="number"
      fullWidth
      margin="normal"
      inputProps={{ step: "any" }}
    />
    <TextField
      name="longitude"
      label="Map Location (Longitude)"
      type="number"
      fullWidth
      margin="normal"
      inputProps={{ step: "any" }}
    />
    <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>
      Submit
    </Button>
    <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick}>
      Back
    </Button>
  </Box>
);

export default UpdatePropertyForm;

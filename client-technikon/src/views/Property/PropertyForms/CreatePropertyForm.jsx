import React from 'react';
import { Box, Button, TextField, MenuItem } from '@mui/material';

const CreatePropertyForm = ({ handleSubmit, handleBackClick }) => (
  <Box sx={{ mt: 3, width: '100%', maxHeight: 'calc(100vh - 200px)', overflowY: 'auto' }}>
    <Box component="form" sx={{ width: '100%' }} onSubmit={handleSubmit}>
      <TextField
        name="propertyE9"
        label="Property E9"
        fullWidth
        margin="normal"
        required
        inputProps={{ maxLength: 11, pattern: "[0-9]{11}" }}
      />
      <TextField
        name="address"
        label="Address"
        fullWidth
        margin="normal"
        required
        inputProps={{ pattern: "[a-zA-Z0-9\\s,.-]+" }}
      />
      <TextField
        name="yearOfConstruction"
        label="Year of Construction"
        type="number"
        fullWidth
        margin="normal"
        required
        inputProps={{ maxLength: 4 }}
      />
      <TextField
        name="typeOfProperty"
        label="Type of Property"
        select
        fullWidth
        margin="normal"
        required
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
        required
      />
      <TextField
        name="latitude"
        label="Map Location (Latitude)"
        type="number"
        fullWidth
        margin="normal"
        required
        inputProps={{ step: "any" }}
      />
      <TextField
        name="longitude"
        label="Map Location (Longitude)"
        type="number"
        fullWidth
        margin="normal"
        required
        inputProps={{ step: "any" }}
      />
      <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>
        Submit
      </Button>
      <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick}>
        Back
      </Button>
    </Box>
  </Box>
);

export default CreatePropertyForm;
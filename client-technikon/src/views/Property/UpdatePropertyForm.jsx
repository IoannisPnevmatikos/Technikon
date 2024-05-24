import React from 'react';
import { Box, Button, TextField, MenuItem } from '@mui/material';

const UpdatePropertyForm = ({ handleSubmit, handleBackClick }) => (
  <Box component="form" sx={{ mt: 3, width: '100%' }} onSubmit={handleSubmit}>
    <TextField
      name="propertyE9"
      label="Property E9"
      fullWidth
      margin="normal"
      required
      inputProps={{ maxLength: 11, pattern: "[0-9]{11}" }}
    />
    <TextField
      name="propertyName"
      label="Property Name"
      fullWidth
      margin="normal"
      required
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
      name="owner"
      label="Owner"
      fullWidth
      margin="normal"
      required
    />
    <TextField
      name="type"
      label="Type"
      select
      fullWidth
      margin="normal"
      required
    >
      <MenuItem value="Residential">Residential</MenuItem>
      <MenuItem value="Commercial">Commercial</MenuItem>
      {/* Add more options as needed */}
    </TextField>
    <TextField
      name="value"
      label="Value"
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
);

export default UpdatePropertyForm;

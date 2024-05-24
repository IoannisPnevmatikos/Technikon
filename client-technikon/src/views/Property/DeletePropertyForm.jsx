import React from 'react';
import { Box, Button, TextField, MenuItem } from '@mui/material';

const DeletePropertyForm = ({ handleSubmit, handleBackClick }) => (
  <Box component="form" sx={{ mt: 3, width: '100%' }} onSubmit={handleSubmit}>
    <TextField
      name="propertyE9"
      select
      label="Select Property E9"
      fullWidth
      margin="normal"
      required
      inputProps={{ maxLength: 11, pattern: "[0-9]{11}" }}
    >
      <MenuItem value="E9-1">E9-1</MenuItem>
      <MenuItem value="E9-2">E9-2</MenuItem>
      <MenuItem value="E9-3">E9-3</MenuItem>
      {/* Add more options as needed */}
    </TextField>
    <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>
      Delete
    </Button>
    <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick}>
      Back
    </Button>
  </Box>
);

export default DeletePropertyForm;

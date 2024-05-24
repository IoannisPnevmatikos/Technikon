import React from 'react';
import { Box, Button, TextField } from '@mui/material';

const DeletePropertyForm = ({ handleSubmit, handleBackClick }) => (
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
    <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>
      Delete
    </Button>
    <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick}>
      Back
    </Button>
  </Box>
);

export default DeletePropertyForm;

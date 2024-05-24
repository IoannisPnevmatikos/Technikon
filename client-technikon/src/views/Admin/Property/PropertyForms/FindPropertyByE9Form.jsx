import React from 'react';
import { Box, Button, TextField } from '@mui/material';

const FindPropertyByE9Form = ({ handleSubmit, handleBackClick }) => (
  <Box component="form" sx={{ mt: 3, width: '100%' }} onSubmit={handleSubmit}>
    <TextField
      name="propertyE9"
      label="Property E9"
      fullWidth
      margin="normal"
      required
      inputProps={{ maxLength: 11, pattern: "[0-9]{11}" }}
    />
    <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>
      Submit
    </Button>
    <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick}>
      Back
    </Button>
  </Box>
);

export default FindPropertyByE9Form;

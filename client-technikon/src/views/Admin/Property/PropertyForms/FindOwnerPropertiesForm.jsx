import React from 'react';
import { Box, Button, TextField } from '@mui/material';

const FindOwnerPropertiesForm = ({ handleSubmit, handleBackClick }) => (
  <Box component="form" sx={{ mt: 3, width: '100%' }} onSubmit={handleSubmit}>
    <TextField
      name="tinNumber"
      label="TIN Number"
      fullWidth
      margin="normal"
      required
      inputProps={{ maxLength: 9, pattern: "[0-9]{9}" }}
    />
    <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>
      Submit
    </Button>
    <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick}>
      Back
    </Button>
  </Box>
);

export default FindOwnerPropertiesForm;

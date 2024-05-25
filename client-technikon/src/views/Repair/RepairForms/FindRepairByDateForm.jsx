import React from 'react';
import { Box, Button, TextField, MenuItem } from '@mui/material';

const FindRepairByDateForm = ({ handleSubmit, handleBackClick }) => {
  const handleChange = (event) => {
    // Convert the date value to the desired format
    const selectedDate = event.target.value;
    console.log(selectedDate); // Move this line here
    const formattedDate = new Date(selectedDate).toISOString().split('T')[0];
    // Set the value to the input field
    event.target.value = formattedDate;
  };

  return (
    <Box component="form" sx={{ mt: 3, width: '100%' }} onSubmit={handleSubmit}>
      <TextField
        name="localDate"
        label="Local Date"
        type="date"
        fullWidth
        margin="normal"
        InputLabelProps={{
          shrink: true,
        }}
        required
        onChange={handleChange} // Changed to the defined function
      />
      <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>
        Submit
      </Button>
      <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick}>
        Back
      </Button>
    </Box>
  );
};

export default FindRepairByDateForm;

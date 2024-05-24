import React, { useState } from 'react';
import { Box, Button, TextField } from '@mui/material';

const FindPropertiesBetweenForm = ({ handleSubmit, handleBackClick }) => {
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');

  const handleFormSubmit = (event) => {
    event.preventDefault();
    if (new Date(startDate) > new Date(endDate)) {
      alert('Ending date should be later than the starting date.');
      return;
    }
    if (new Date(endDate) > new Date()) {
      alert('Ending date should not exceed the current date.');
      return;
    }
    handleSubmit(event);
  };

  return (
    <Box component="form" sx={{ mt: 3, width: '100%' }} onSubmit={handleFormSubmit}>
      <TextField
        name="startDate"
        label="Starting Date"
        type="date"
        fullWidth
        margin="normal"
        required
        InputLabelProps={{
          shrink: true,
        }}
        value={startDate}
        onChange={(e) => setStartDate(e.target.value)}
      />
      <TextField
        name="endDate"
        label="Ending Date"
        type="date"
        fullWidth
        margin="normal"
        required
        InputLabelProps={{
          shrink: true,
        }}
        value={endDate}
        onChange={(e) => setEndDate(e.target.value)}
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

export default FindPropertiesBetweenForm;

import React, { useState } from 'react';
import { Box, Button, TextField, CircularProgress } from '@mui/material';

const GetRepairReportForm = ({ handleSubmit, handleBackClick }) => {
  const [isLoading, setIsLoading] = useState(false);

  const handleChange = (event) => {
    // Convert the date value to the desired format
    const selectedDate = event.target.value;
    console.log(selectedDate); // Move this line here
    const formattedDate = new Date(selectedDate).toISOString().split('T')[0];
    // Set the value to the input field
    event.target.value = formattedDate;
  };

  const onSubmit = (event) => {
    event.preventDefault();
    setIsLoading(true);
    handleSubmit(event, setIsLoading);
  };

  return (
    <Box component="form" sx={{ mt: 3, width: '100%' }} onSubmit={onSubmit}>
      <TextField
        name="startDate"
        label="Starting Date"
        type="date"
        fullWidth
        margin="normal"
        InputLabelProps={{
          shrink: true,
        }}
        required
        onChange={handleChange} // Changed to the defined function
      />
      <TextField
        name="endDate"
        label="Ending Date"
        type="date"
        fullWidth
        margin="normal"
        InputLabelProps={{
          shrink: true,
        }}
        required
        onChange={handleChange} // Changed to the defined function
      />
      <Box sx={{ position: 'relative' }}>
        <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }} disabled={isLoading}>
          {isLoading ? 'Submitting...' : 'Submit'}
        </Button>
        {isLoading && (
          <CircularProgress
            size={24}
            sx={{
              position: 'absolute',
              top: '50%',
              left: '50%',
              marginTop: '-12px',
              marginLeft: '-12px',
            }}
          />
        )}
      </Box>
      <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick} disabled={isLoading}>
        Back
      </Button>
    </Box>
  );
};

export default GetRepairReportForm;

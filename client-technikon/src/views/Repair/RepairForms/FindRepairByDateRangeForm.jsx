import React, { useState } from 'react';
import { Box, Button, TextField } from '@mui/material';

const FindRepairByDateRangeForm = ({ handleSubmit, handleBackClick }) => {
  const [isLoading, setIsLoading] = useState(false); // Add loading state

  const handleChange = (event) => {
    // Convert the date value to the desired format
    const selectedDate = event.target.value;
    console.log(selectedDate); // Move this line here
    const formattedDate = new Date(selectedDate).toISOString().split('T')[0];
    // Set the value to the input field
    event.target.value = formattedDate;
  };

  const handleFormSubmit = async (event) => {
    event.preventDefault();
    setIsLoading(true); // Set loading state to true

    try {
      await handleSubmit(event);
      setIsLoading(false); // Reset loading state when submission is complete
    } catch (error) {
      setIsLoading(false); // Reset loading state if submission fails
      console.error('Error occurred:', error);
      alert('Error occurred. Please try again.');
    }
  };

  return (
    <Box component="form" sx={{ mt: 3, width: '100%' }} onSubmit={handleFormSubmit}>
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
        disabled={isLoading} // Disable input field while loading
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
        disabled={isLoading} // Disable input field while loading
      />
      <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }} disabled={isLoading}>
        {isLoading ? 'Loading...' : 'Submit'} {/* Change button text based on loading state */}
      </Button>
      <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick}>
        Back
      </Button>
    </Box>
  );
};

export default FindRepairByDateRangeForm;

import React, { useState } from 'react';
import { Box, Button, TextField, MenuItem, CircularProgress } from '@mui/material';

const FindPropertiesBetweenForm = ({ handleSubmit, handleBackClick }) => {
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [isLoading, setIsLoading] = useState(false);

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
    handleSubmit(event, setIsLoading);
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
    <Box sx={{ position: 'relative' }}>
          <Button
            type="submit"
            variant="contained"
            color="primary"
            fullWidth
            sx={{ mt: 2 }}
            disabled={isLoading}
          >
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
      <Button
        variant="outlined"
        color="secondary"
        fullWidth
        sx={{ mt: 2 }}
        onClick={handleBackClick}
        disabled={isLoading} // Disable the button while loading
      >
        Back
      </Button>
    </Box>
  );
};

export default FindPropertiesBetweenForm;

import React, { useState } from 'react';
import { Box, Button, TextField, CircularProgress } from '@mui/material';

const FindPropertyByE9Form = ({ handleSubmit, handleBackClick }) => {

  const [isLoading, setIsLoading] = useState(false);
  const onSubmit = (event) => handleSubmit(event, setIsLoading);

  return (<Box component="form" sx={{ mt: 3, width: '100%' }} onSubmit={onSubmit}>
    <TextField
      name="propertyE9"
      label="Property E9"
      fullWidth
      margin="normal"
      required
      inputProps={{ maxLength: 11, pattern: "[0-9]{11}" }}
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
    <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick} disabled={isLoading}>
      Back
    </Button>
  </Box>
);
};

export default FindPropertyByE9Form;

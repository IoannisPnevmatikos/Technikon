import React, { useState } from 'react';
import { Box, Button, TextField, MenuItem, CircularProgress } from '@mui/material';

const FindOwnerPropertiesForm = ({ handleSubmit, handleBackClick }) => {

  const [isLoading, setIsLoading] = useState(false);
  const onSubmit = (event) => handleSubmit(event, setIsLoading);

  return (
  <Box component="form" sx={{ mt: 3, width: '100%' }} onSubmit={onSubmit}>
    <TextField
      name="tinNumber"
      label="TIN Number"
      fullWidth
      margin="normal"
      required
      inputProps={{ maxLength: 9, pattern: "[0-9]{9}" }}
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
            disabled={isLoading}
          >
            Back
          </Button>
  </Box>
);
};
export default FindOwnerPropertiesForm;

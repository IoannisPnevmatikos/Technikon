import React, { useState } from 'react';
import { Box, Button, TextField, MenuItem, CircularProgress } from '@mui/material';

const UpdatePropertyForm = ({ handleSubmit, handleBackClick }) => {

  const [isLoading, setIsLoading] = useState(false);
  const onSubmit = (event) => handleSubmit(event, setIsLoading);

  return (<Box component="form" sx={{ mt: 3, width: '100%' }} onSubmit={onSubmit}>
    <TextField
      name="id"
      label="Property ID"
      fullWidth
      margin="normal"
      required
      inputProps={{ maxLength: 11, pattern: "[0-9]{11}" }}
    />
    <TextField
      name="propertyE9"
      label="Property E9"
      fullWidth
      margin="normal"
      inputProps={{ maxLength: 11, pattern: "[0-9]{11}" }}
    />
    <TextField
      name="address"
      label="Address"
      fullWidth
      margin="normal"
      inputProps={{ pattern: "[a-zA-Z0-9\\s,.-]+" }}
    />
    <TextField
      name="yearOfConstruction"
      label="Year of Construction"
      type="number"
      fullWidth
      margin="normal"
      inputProps={{ maxLength: 4 }}
    />
    <TextField
      name="typeOfProperty"
      label="Type of Property"
      select
      fullWidth
      margin="normal"
    >
      <MenuItem value="DETACHED_HOUSE">DETACHED_HOUSE</MenuItem>
      <MenuItem value="MAISONETTE">MAISONETTE</MenuItem>
      <MenuItem value="APARTMENT_BUILDING">APARTMENT_BUILDING</MenuItem>
    </TextField>
    <TextField
      name="photoURL"
      label="Photo URL"
      fullWidth
      margin="normal"
    />
    <TextField
      name="latitude"
      label="Map Location (Latitude)"
      type="number"
      fullWidth
      margin="normal"
      inputProps={{ step: "any" }}
    />
    <TextField
      name="longitude"
      label="Map Location (Longitude)"
      type="number"
      fullWidth
      margin="normal"
      inputProps={{ step: "any" }}
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

export default UpdatePropertyForm;

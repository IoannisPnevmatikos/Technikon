import React, { useState, useEffect }  from 'react';
import { Box, Button, TextField, CircularProgress, MenuItem, Typography } from '@mui/material';

const FindOwnerByIsActiveForm =  ({ handleSubmit, handleBackClick }) => {
    const [isLoading, setIsLoading] = useState(false);
    const [ownerData, setOwnerData] = useState(null);
    const [isActive, setIsActive] = useState(""); 
    const handleChange = (event) => {
        setIsActive(event.target.value);
      };
    const onSubmit = async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
        setIsLoading(true)
        try {
          const data = await handleSubmit(formData);
          setOwnerData(data);
          console.log(data)
        } catch (error) {
          console.error(error);
          alert('Failed to find owner');
        } finally {
          setIsLoading(false);
        }
      };
  
    return (
    <Box component="form" sx={{ width: '100%' }} onSubmit={onSubmit}>
  <TextField
        name="isActive"
        label="IsActive"
        select
        fullWidth
        margin="normal"
        required
        value={isActive}
        onChange={handleChange}
      >
        <MenuItem value="true">Active</MenuItem>
        <MenuItem value="false">InActive</MenuItem>
      </TextField>
      <Box sx={{ position: 'relative'}}>
      
        <Button
          type="submit"
          variant="contained"
          color="primary"
          fullWidth
          sx={{ mt: 2 }}
          disabled={isLoading}
        >
         
          {isLoading ? 'Submitting...' : ' View'}
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
}
export default FindOwnerByIsActiveForm
import React, { useState } from 'react';
import { Box, Button, TextField,CircularProgress } from '@mui/material';


const FindOwnerByTinForm = ({ handleSubmit, handleBackClick }) => {

  const [ownerData, setOwnerData] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  
  const onSubmit = async (event) =>{ 
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
   
  
  }

  return (
  <Box component="form" sx={{ width: '100%' }} onSubmit={onSubmit}>
    
    <TextField
      name="tin"
      label="Tin"
      fullWidth
      margin="normal"
      required
      inputProps={{ pattern: '^[0-9]{9}$', maxLength: 9, }}
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
       
        {isLoading ? 'Submitting...' : ' Go to profile'}
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


export default FindOwnerByTinForm

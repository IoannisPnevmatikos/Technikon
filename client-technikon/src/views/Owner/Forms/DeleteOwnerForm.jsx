import React, { useEffect, useState } from 'react'
import { Box, Button, TextField,CircularProgress  } from '@mui/material';
import useToken from '../../../stores/useToken'
import { jwtDecode } from "jwt-decode";

const DeleteOwnerForm = ({ handleSubmit, handleBackClick }) => {
  const token = useToken.getState().token?.data;
  const [isLoading, setIsLoading] = useState(false);
  const [userInfo, setUserInfo] = useState({ username: '' });
  const onSubmit = (event) => handleSubmit(event, setIsLoading);
  
  useEffect(() => {
    if (token) {
      try {
        console.log("Token is ", token)
        const { sub: username } = jwtDecode(token);
        console.log('Decoded values:', { username });
        setUserInfo({ username });
        console.log(userInfo.username)
      }
      catch (error) {
        console.log("Failed to decode token", error)
      }

    }

  }, [token]);

  return (
    <Box sx={{ mt: 3, width: '100%', maxHeight: 'calc(100vh - 200px)', overflowY: 'auto' }}>
      <Box component="form" sx={{ width: '100%' }} onSubmit={onSubmit}>
        <TextField
          name="tinNumber"
          label="TinNumber"
          fullWidth
          margin="normal"
          required
          inputProps={{ pattern: '^[0-9]{9}$', maxLength: 9, }}
        />
        <TextField
          label="Username"
          type="text"
          name="username"
          required
          fullWidth
          margin="normal"
          inputProps={{
            pattern: '^[A-Za-z]\\w{5,29}$',
            minLength: 6,
            maxLength: 30,
          }}
          value={userInfo.username}
        />
        <TextField
          label="Password"
          name="password"
          type="password"
          required
          fullWidth
          margin="normal"
        />

        <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}  disabled={isLoading}>
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
          />)}
        <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick} disabled={isLoading}>
          Back
        </Button>
      </Box>
    </Box>
  )
}


export default DeleteOwnerForm

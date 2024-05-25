import React, { useEffect, useState } from 'react'
import { Box, Button, TextField,CircularProgress  } from '@mui/material'
import useToken from '../../../stores/useToken'
import { jwtDecode } from "jwt-decode";

const CreateOwnerForm = ({ handleSubmit, handleBackClick }) => {

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
    <Box sx={{ mt: 1, width: '100%', maxHeight: 'calc(100vh - 200px)', overflowY: 'auto' }}>
      <Box component="form" sx={{ width: '100%' }} onSubmit={onsubmit}>
        <TextField
          label="Username"
          type="text"
          name="username"
          fullWidth
          value={userInfo.username}
        />
        <TextField
          margin="normal"
          required
          fullWidth
          name="password"
          label="Password"
          type="password"
          id="password"
        />
        <TextField
         name="email"
          label="Email"
          type="text"
          required
          fullWidth
          margin="normal"

        />

        <TextField
          name="tinNumber"
          label="Tin Number"
          fullWidth
          margin="normal"
          required
          inputProps={{ pattern: '^[0-9]{9}$', maxLength: 9, }}
        />


        <  Box sx={{ display: 'flex', width: '100%', gap: 2 }}>
          <TextField
            name="firstName"
            label="FirstName"
            type="text"
            required
            fullWidth
            margin="normal"
            inputProps={{
              pattern: "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$",
              maxLength: 30,
            }}
          />

          <TextField
            name="lastName"
            label="LastName"
            type="text"
            required
            margin="normal"
            fullWidth
            inputProps={{
              pattern: "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$",
              maxLength: 30,
            }}
          />
        </Box>
        
        <TextField
          name="phone"
          label="Phone"
          type="phone"
          required
          fullWidth
          margin="normal"
        />

        <TextField
          name="address"
          label="address"
          type="text"
          fullWidth
          margin="normal"
          required
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
            />
          )}
        <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick} disabled={isLoading}>
          Back
        </Button>
      </Box>
    </Box>
  )
};

export default CreateOwnerForm;

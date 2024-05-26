import React, { useState, useEffect }  from 'react';
import { Box, Button, TextField, CircularProgress } from '@mui/material';
import useToken from '../../../../stores/useToken';
import { jwtDecode } from "jwt-decode";

const DeleteOwnerForm= ({ handleSubmit, handleBackClick }) => {

    const token = useToken.getState().token?.data;
    const [isLoading, setIsLoading] = useState(false);
    const [userInfo, setUserInfo] = useState({ username: '' });
    const onSubmit = (event) => handleSubmit(event, setIsLoading);
    useEffect(() => {
        if (token) {
          try {   
            const { sub: username } = jwtDecode(token);
            setUserInfo({ username });
          }
          catch (error) {
            console.log("Failed to decode token", error)
          }
      
        }
      
      }, [token]);
      
return ( 

 <Box sx={{ mt: 1, width: '100%', maxHeight: 'calc(100vh - 200px)', overflowY: 'auto' }}>
    <Box component="form" sx={{ width: '100%' }} onSubmit={onSubmit}>
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
      <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick}  disabled={isLoading}>
        Back
      </Button>
    </Box>
    </Box>
    )
}


export default DeleteOwnerForm

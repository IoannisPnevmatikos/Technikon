import React, { useState } from 'react';
import { Box, Button, TextField, CircularProgress, MenuItem, Typography } from '@mui/material';

const CreateOwnerForm = ({ handleSubmit, handleBackClick }) => {
  const [isLoading, setIsLoading] = useState(false);
  const [username, setUsername] = useState('')
  const [error, setError] = useState('');


  const handleInputChange = (event) => {
    const value = event.target.value;
    setUsername(value);

    // Validate the input length
    if (value.length < 6) {
      setError('Username must be longer than 6 characters.');
    } else {
      setError('');
    }
  };

  return (
    <Box sx={{ mt: 1, width: '100%', maxHeight: 'calc(100vh - 200px)', overflowY: 'auto' }}>
      <Box component="form" sx={{ width: '100%' }} onSubmit={(event) => handleSubmit(event, setIsLoading)}>

        <TextField
          name="role"
          label="Role"
          select
          fullWidth
          margin="normal"
          required
        >
          <MenuItem value="admin">ADMIN</MenuItem>
          <MenuItem value="user">USER</MenuItem>
        </TextField>

        <TextField
          label="Username"
          type="text"
          name="username"
          fullWidth
          required
          value={username}
          onChange={handleInputChange}
        />
        {error && (
        <Typography color="error" variant="body2">
          {error}
        </Typography>
      )}
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

        <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }} disabled={isLoading}>
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
        <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick}>
          Back
        </Button>
      </Box>
    </Box>
  )
};

export default CreateOwnerForm;

import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Button, TextField, Container, Typography, Box } from '@mui/material';
import { paths } from '../../constants/paths/paths';
import useToken from '../../stores/useToken';


function LoginPage() {

  const navigate = useNavigate();
  const [username, setUsername] = useState(''); 
  const [password, setPassword] = useState('');
  const login = useToken((state) => state.login);
  const {token} = useToken();

  const handleSubmit = async (e) => {

    e.preventDefault();
    // Implement login logic here
    try {
    console.log('Logging in with', username);
    await login({ username, password });
    alert('You have successfully logged in!');
    navigate(paths.owner);

    } catch (error) {
      console.error('Login failed:', error);
      alert('Login failed. Please try again.');
      // setError(error);
    }

  };

  return (
    <Container maxWidth="sm" sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
      <Box sx={{ p: 3, boxShadow: 3, borderRadius: 2, width: '100%' }}>
        <Typography variant="h4" component="h1" gutterBottom sx={{ textAlign: 'center' }}>
          Login
        </Typography>
        <form onSubmit={handleSubmit}>
          <TextField
            label="Username"
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
            fullWidth
            margin="normal"
          />
          <TextField
            label="Password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            fullWidth
            margin="normal"
          />
          <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>
            Login
          </Button>
        </form>
        <Typography variant="body2" sx={{ mt: 2, textAlign: 'center' }}>
          Don't have an account? <Link to={paths.signup}>Sign up</Link>
        </Typography>
      </Box>
    </Container>
  );
}

export default LoginPage;

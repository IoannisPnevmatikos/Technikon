import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Button, TextField, Container, Typography, Box, CircularProgress } from '@mui/material';
import { paths } from '../../constants/paths/paths';
import { signUser } from '../../api/Signup/sign';

function SignUpPage() {
  const navigate = useNavigate();
  const [username, setUsername] = useState('');
  const [error, setError] = useState('')
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const handleInputChange = (event) => {
    const value = event.target.value;
    setUsername(value);

    if (value.length < 6) {
      setError('Username must be longer than 6 characters.');
    } else {
      setError('');
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (password !== confirmPassword) {
      alert('Passwords do not match');
      return;
    }
    setIsLoading(true);
    try {
      const response = await signUser({ username, email, password });
      if (response.status === 201) {
        console.log('Sign-up successful');
        alert('Sign-up successful!');
        navigate(paths.login);
      }
         
    } catch (error) {
      console.error('Sign-up failed:', error);
     if(error.response.status === 403){
          alert('Username or email already in use! Try again ');
        }
     else if(error.response.status === 409){
          alert('Username must be at least 6 characters and start with lowercase')
        }
        else
      alert('Sign-up failed! ');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <Container maxWidth="sm" sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
      <Box sx={{ p: 3, boxShadow: 3, borderRadius: 2, width: '100%' }}>
        <Typography variant="h4" component="h1" gutterBottom sx={{ textAlign: 'center' }}>
          Sign Up
        </Typography>
        <form onSubmit={handleSubmit}>
          <TextField
            label="Username"
            type="username"
            value={username}
            onChange={handleInputChange}
            required
            fullWidth
            margin="normal"
          />
           {error && (
        <Typography color="error" variant="body2">
          {error}
        </Typography>
      )}
          <TextField
            label="Email"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
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
          <TextField
            label="Confirm Password"
            type="password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            required
            fullWidth
            margin="normal"
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
              {isLoading ? 'Signing Up...' : 'Sign Up'}
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
        </form>
        <Typography variant="body2" sx={{ mt: 2, textAlign: 'center' }}>
          Already have an account? <Link to={paths.login}>Login</Link>
        </Typography>
      </Box>
    </Container>
  );
}

export default SignUpPage;

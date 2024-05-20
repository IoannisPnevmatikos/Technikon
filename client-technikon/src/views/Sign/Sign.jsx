import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { Button, TextField, Container, Typography, Box } from '@mui/material';

function SignUpPage() {

  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (password !== confirmPassword) {
      alert('Passwords do not match');
      return;
    }
    // Implement sign-up logic here
    console.log('Signing up with', email, password);
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
            onChange={(e) => setUsername(e.target.value)}
            required
            fullWidth
            margin="normal"
          />
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
          <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>
            Sign Up
          </Button>
        </form>
        <Typography variant="body2" sx={{ mt: 2, textAlign: 'center' }}>
          Already have an account? <Link to="/">Login</Link>
        </Typography>
      </Box>
    </Container>
  );
}

export default SignUpPage;

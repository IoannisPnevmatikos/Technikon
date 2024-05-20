import React from 'react';
import { Link } from 'react-router-dom';
import { Container, Typography, Box, Button } from '@mui/material';

function Home() {
  return (
    <Container maxWidth="md" sx={{ display: 'flex', justifyContent: 'center', marginTop: '64px' }}>
      <Box sx={{ mt: 8 }}>
        <Typography variant="h4" gutterBottom>
          Welcome to Technikon
        </Typography>
        <Typography variant="body1" paragraph>
          Technikon is a leading company in the field of technology and innovation. We specialize in creating cutting-edge solutions to meet the evolving needs of our customers.
        </Typography>
        <Typography variant="body1" paragraph>
          Our mission is to empower businesses and individuals with innovative technology solutions that drive growth and success.
        </Typography>
        <Typography variant="body1" paragraph>
          At Technikon, we pride ourselves on our commitment to excellence, creativity, and customer satisfaction. We work closely with our clients to understand their unique challenges and develop tailored solutions to help them achieve their goals.
        </Typography>
        <Typography variant="body1" paragraph>
          Whether you're a small startup or a large enterprise, Technikon has the expertise and experience to take your business to the next level. Contact us today to learn more about how we can help you succeed.
        </Typography>
        <Box sx={{ display: 'flex', justifyContent: 'center' }}>
          <Button component={Link} to="/login" variant="contained" color="primary" sx={{ mr: 2 }}>
            Login
          </Button>
          <Button component={Link} to="/signup" variant="outlined" color="primary">
            Sign Up
          </Button>
        </Box>
      </Box>
    </Container>
  );
}

export default Home;

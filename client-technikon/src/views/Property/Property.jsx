import React, { useState } from 'react';
import { Container, Typography, Box, Button, TextField, MenuItem } from '@mui/material';
import { useNavigate } from 'react-router-dom';

function Property() {
  const [activeForm, setActiveForm] = useState('');
  const navigate = useNavigate();

  const handleBackClick = () => {
    setActiveForm('');
    navigate('/properties'); // This will update the URL to /properties
  };

  const renderForm = () => {
    switch (activeForm) {
        case 'createProperty':
            return (
              <Box sx={{ mt: 3, width: '100%', maxHeight: 'calc(100vh - 200px)', overflowY: 'auto' }}>
                <Box component="form" sx={{ width: '100%' }}>
                  <TextField
                    label="Property E9"
                    fullWidth
                    margin="normal"
                    required
                  />
                  <TextField
                    label="Address"
                    fullWidth
                    margin="normal"
                    required
                  />
                  <TextField
                    label="Year of Construction"
                    type="number"
                    fullWidth
                    margin="normal"
                    required
                  />
                  <TextField
                    label="Type of Property"
                    select
                    fullWidth
                    margin="normal"
                    required
                  >
                    <MenuItem value="Residential">Residential</MenuItem>
                    <MenuItem value="Commercial">Commercial</MenuItem>
                    {/* Add more options as needed */}
                  </TextField>
                  <TextField
                    label="Photo URL"
                    fullWidth
                    margin="normal"
                    required
                  />
                  <TextField
                    label="Map Location (Latitude)"
                    type="number"
                    fullWidth
                    margin="normal"
                    required
                  />
                  <TextField
                    label="Map Location (Longitude)"
                    type="number"
                    fullWidth
                    margin="normal"
                    required
                  />
                  <TextField
                    label="Owner TIN Number"
                    fullWidth
                    margin="normal"
                    required
                  />
                  <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>
                    Submit
                  </Button>
                  <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick}>
                    Back
                  </Button>
                </Box>
              </Box>
            );                
      case 'findPropertyByE9':
        return (
          <Box component="form" sx={{ mt: 3, width: '100%' }}>
            <TextField
              label="Property E9"
              fullWidth
              margin="normal"
              required
            />
            <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>
              Submit
            </Button>
            <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick}>
              Back
            </Button>
          </Box>
        );
      case 'updateProperty':
        return (
          <Box component="form" sx={{ mt: 3, width: '100%' }}>
            <TextField
              label="Property E9"
              fullWidth
              margin="normal"
              required
            />
            <TextField
              label="Property Name"
              fullWidth
              margin="normal"
              required
            />
            <TextField
              label="Address"
              fullWidth
              margin="normal"
              required
            />
            <TextField
              label="Owner"
              fullWidth
              margin="normal"
              required
            />
            <TextField
              label="Type"
              select
              fullWidth
              margin="normal"
              required
            >
              <MenuItem value="Residential">Residential</MenuItem>
              <MenuItem value="Commercial">Commercial</MenuItem>
              {/* Add more options as needed */}
            </TextField>
            <TextField
              label="Value"
              type="number"
              fullWidth
              margin="normal"
              required
            />
            <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>
              Submit
            </Button>
            <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick}>
              Back
            </Button>
          </Box>
        );
      case 'deleteProperty':
        return (
          <Box component="form" sx={{ mt: 3, width: '100%' }}>
            <TextField
              select
              label="Select Property E9"
              fullWidth
              value=""
              onChange={() => {}}
              margin="normal"
              required
            >
              <MenuItem value="E9-1">E9-1</MenuItem>
              <MenuItem value="E9-2">E9-2</MenuItem>
              <MenuItem value="E9-3">E9-3</MenuItem>
              {/* Add more options as needed */}
            </TextField>
            <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>
              Delete
            </Button>
            <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick}>
              Back
            </Button>
          </Box>
        );
      default:
        return null;
    }
  };

  return (
    <Container maxWidth="md" sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', marginTop: '64px' }}>
      <Box sx={{ mt: 8, width: '100%', textAlign: 'center' }}>
        <Typography variant="h4" gutterBottom>
          Property Actions
        </Typography>
        {activeForm ? (
          renderForm()
        ) : (
          <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2, mt: 4 }}>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('createProperty')}>
              Create a Property
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('findPropertyByE9')}>
              Find a Property by E9
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('updateProperty')}>
              Update a Property
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('deleteProperty')}>
              Delete a Property
            </Button>
          </Box>
        )}
      </Box>
    </Container>
  );
}

export default Property;

import React, { useState } from 'react';
import { Container, Typography, Box, Button, TextField, MenuItem } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { paths } from '../../constants/paths/paths';
import createProperty from '../../api/Property/User/createProperty';
import useToken from '../../stores/useToken';

function Property() {
  const [activeForm, setActiveForm] = useState('');
  const navigate = useNavigate();
  const { token } = useToken();

  const handleBackClick = () => {
    setActiveForm('');
    navigate(paths.property);
  };

  const handleSubmitDelete = async (event) => {
    event.preventDefault();
    // Add form validation logic here
    const formData = new FormData(event.target);
    console.log(Object.fromEntries(formData.entries()));
  };

  const handleSubmitUpdate = async (event) => {
    event.preventDefault();
    // Add form validation logic here
    const formData = new FormData(event.target);
    console.log(Object.fromEntries(formData.entries()));
  };

  const handleSubmitFindByE9 = async (event) => {
    event.preventDefault();
    // Add form validation logic here
    const formData = new FormData(event.target);
    console.log(Object.fromEntries(formData.entries()).propertyE9); 
  };

  const handleSubmitCreate = async (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);

    try {
      const response = await createProperty(formData, token?.data); // Pass the token here
      console.log('Property created successfully', response);
      alert('Property created!');
      navigate(paths.property);
    } catch (error) {
      console.error('Property creation failed:', error);
      alert('Property creation failed. Please try again.');
    }
  };

  const renderForm = () => {
    switch (activeForm) {
      case 'createProperty':
        return (
          <Box sx={{ mt: 3, width: '100%', maxHeight: 'calc(100vh - 200px)', overflowY: 'auto' }}>
            <Box component="form" sx={{ width: '100%' }} onSubmit={handleSubmitCreate}>
              <TextField
                name="propertyE9"
                label="Property E9"
                fullWidth
                margin="normal"
                required
                inputProps={{ maxLength: 11, pattern: "[0-9]{11}" }}
              />
              <TextField
                name="address"
                label="Address"
                fullWidth
                margin="normal"
                required
                inputProps={{ pattern: "[a-zA-Z0-9\\s,.-]+" }}
              />
              <TextField
                name="yearOfConstruction"
                label="Year of Construction"
                type="number"
                fullWidth
                margin="normal"
                required
                inputProps={{ maxLength: 4 }}
              />
              <TextField
                name="typeOfProperty"
                label="Type of Property"
                select
                fullWidth
                margin="normal"
                required
              >
                <MenuItem value="DETACHED_HOUSE">DETACHED_HOUSE</MenuItem>
                <MenuItem value="MAISONETTE">MAISONETTE</MenuItem>
                <MenuItem value="APARTMENT_BUILDING">APARTMENT_BUILDING</MenuItem>
              </TextField>
              <TextField
                name="photoURL"
                label="Photo URL"
                fullWidth
                margin="normal"
                required
              />
              <TextField
                name="latitude"
                label="Map Location (Latitude)"
                type="number"
                fullWidth
                margin="normal"
                required
                inputProps={{ step: "any" }}
              />
              <TextField
                name="longitude"
                label="Map Location (Longitude)"
                type="number"
                fullWidth
                margin="normal"
                required
                inputProps={{ step: "any" }}
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
          <Box component="form" sx={{ mt: 3, width: '100%' }} onSubmit={handleSubmitFindByE9}>
            <TextField
              name="propertyE9"
              label="Property E9"
              fullWidth
              margin="normal"
              required
              inputProps={{ maxLength: 11, pattern: "[0-9]{11}" }}
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
          <Box component="form" sx={{ mt: 3, width: '100%' }} onSubmit={handleSubmitUpdate}>
            <TextField
              name="propertyE9"
              label="Property E9"
              fullWidth
              margin="normal"
              required
              inputProps={{ maxLength: 11, pattern: "[0-9]{11}" }}
            />
            <TextField
              name="propertyName"
              label="Property Name"
              fullWidth
              margin="normal"
              required
            />
            <TextField
              name="address"
              label="Address"
              fullWidth
              margin="normal"
              required
              inputProps={{ pattern: "[a-zA-Z0-9\\s,.-]+" }}
            />
            <TextField
              name="owner"
              label="Owner"
              fullWidth
              margin="normal"
              required
            />
            <TextField
              name="type"
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
              name="value"
              label="Value"
              type="number"
              fullWidth
              margin="normal"
              required
              inputProps={{ step: "any" }}
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
          <Box component="form" sx={{ mt: 3, width: '100%' }} onSubmit={handleSubmitDelete}>
            <TextField
              name="propertyE9"
              select
              label="Select Property E9"
              fullWidth
              value=""
              onChange={() => {}}
              margin="normal"
              required
              inputProps={{ maxLength: 11, pattern: "[0-9]{11}" }}
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

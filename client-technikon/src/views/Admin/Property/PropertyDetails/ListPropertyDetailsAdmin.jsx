// ListPropertyDetails.js

import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { Box, Typography, CircularProgress, Container, Grid, Button } from '@mui/material';
import useToken from '../../../../stores/useToken';
import {paths} from '../../../../constants/paths/paths'
import getListPropertyDetailsAdmin from '../../../../api/Property/Admin/getListPropertyDetailsAdmin';

const ListPropertyDetailsAdmin = () => {
  const { id } = useParams();
  const [properties, setProperties] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const { token } = useToken();
  const navigate = useNavigate();

  const handleOnClick = () => {
    navigate(paths.adminProperty)
  }

  useEffect(() => {
    const fetchProperties = async () => {
      try {
        const propertiesData = await getListPropertyDetailsAdmin(id, token?.data);
        setProperties(propertiesData.data);
      } catch (error) {
        console.error('Error fetching properties:', error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchProperties();
  }, [id, token]);

  if (isLoading) {
    return (
      <Box
        sx={{
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'center',
          minHeight: '100vh',
        }}
      >
        <CircularProgress />
      </Box>
    );
  }

  if (!properties || properties.length === 0) {
    return (
      <Box
        sx={{
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'center',
          minHeight: '100vh',
        }}
      >
        <Typography variant="body1">No properties found</Typography>
      </Box>
    );
  }

  return (
    <Container
      sx={{
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
        minHeight: '100vh',
        mt: 10,
        maxWidth: 'lg',
      }}
    >
      <Typography variant="h2" gutterBottom>
        Property Details
      </Typography>
      {properties.map((property, index) => (
        <Box
          key={index}
          sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            justifyContent: 'center',
            border: 1,
            borderRadius: 4,
            p: 5,
            boxShadow: 3,
            width: '100%',
            bgcolor: 'background.paper',
            maxWidth: 800,
            opacity: property.active ? 1 : 0.5,
            my: 2, // Add margin between properties
          }}
        >
          <Grid container spacing={3}>
            <Grid item xs={12} sm={4}>
              {property.photo && (
                <Box>
                  <img src={property.photo} alt="Property" style={{ width: '100%', height: 'auto', maxWidth: 400 }} />
                </Box>
              )}
            </Grid>
            <Grid item xs={12} sm={8}>
              <Typography variant="h5">Property ID: {property.propertyId}</Typography>
              <Typography variant="h5">Address: {property.address}</Typography>
              <Typography variant="h5">Type Of Property: {property.typeOfProperty}</Typography>
              <Typography variant="h5">Year Of Construction: {property.yearOfConstruction}</Typography>
              <Typography variant="h5">
                Map Location: (Longitude: {property.mapLocation.longitude} | Latitude: {property.mapLocation.latitude})
              </Typography>
              <Typography variant="h5">Registration Date: {property.registrationDate}</Typography>
            </Grid>
          </Grid>
        </Box>
      ))}
       <Button onClick={handleOnClick} variant="outlined" sx={{ mb: 2 }}>Back to Property Menu</Button>
    </Container>
  );
};

export default ListPropertyDetailsAdmin;

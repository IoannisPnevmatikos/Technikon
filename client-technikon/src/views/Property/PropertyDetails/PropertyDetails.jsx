// PropertyDetails.js

import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { Box, Typography, CircularProgress, Container, Grid } from '@mui/material';
import getPropertyDetails from '../../../api/Property/getPropertyDetails';
import useToken from '../../../stores/useToken';

const PropertyDetails = () => {
  const { id } = useParams();
  const [property, setProperty] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const { token } = useToken();

  useEffect(() => {
    const fetchPropertyDetails = async () => {
      try {
        const propertyDetails = await getPropertyDetails(id, token?.data);
        setProperty(propertyDetails);
      } catch (error) {
        console.error('Error fetching property details:', error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchPropertyDetails();
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

  if (!property) {
    return (
      <Box
        sx={{
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'center',
          minHeight: '100vh',
        }}
      >
        <Typography variant="body1">Property not found</Typography>
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
        mt: 2, // Reduced margin-top to bring it closer to the top of the page
        maxWidth: 'lg',
      }}
    >
      <Typography variant="h2" gutterBottom>
        Property Details
      </Typography>
      <Box
        sx={{
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          justifyContent: 'center',
          border: 1,
          borderRadius: 4,
          p: 5, // Moderate padding
          boxShadow: 3,
          width: '100%', // Make the box take the full width of the container
          bgcolor: 'background.paper',
          maxWidth: 800, // Reduced maxWidth
        }}
      >
        <Grid container spacing={3}>
          <Grid item xs={12} sm={4}>
            {property.data.photo && (
              <Box>
                <img src={property.data.photo} alt="Property" style={{ width: '100%', height: 'auto', maxWidth: 400 }} />
              </Box>
            )}
          </Grid>
          <Grid item xs={12} sm={8}>
            <Typography variant="h5">Property ID: {property.data.propertyId}</Typography>
            <Typography variant="h5">Address: {property.data.address}</Typography>
            <Typography variant="h5">Type Of Property: {property.data.typeOfProperty}</Typography>
            <Typography variant="h5">Year Of Construction: {property.data.yearOfConstruction}</Typography>
            <Typography variant="h5">
              Map Location: (Longitude: {property.data.mapLocation.longitude} | Latitude: {property.data.mapLocation.latitude})
            </Typography>
            <Typography variant="h5">Registration Date: {property.data.registrationDate}</Typography>
          </Grid>
        </Grid>
      </Box>
    </Container>
  );
};

export default PropertyDetails;

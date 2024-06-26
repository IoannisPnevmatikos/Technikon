import { Box, Container, Typography, Link, Grid } from '@mui/material';

function Footer() {
  return (
    <Box
      component="footer"
      sx={{
        py: 2,
        px: 2,
        backgroundColor: (theme) => theme.palette.grey[200],
        width: '100%',
        position: 'fixed',
        bottom: 0,
        left: 0,
        right: 0,
        height: '48px', // Fixed height for the footer
        zIndex: 1000,
      }}
    >
      <Container maxWidth="lg">
        <Grid container spacing={4} justifyContent="space-between">
          <Grid item xs={12} sm={4}>
            <Typography variant="body2" color="textSecondary">
              © {new Date().getFullYear()} My App. All rights reserved.
            </Typography>
          </Grid>
        </Grid>
      </Container>
    </Box>
  );
}

export default Footer;

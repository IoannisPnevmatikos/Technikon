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
        height: '48px', // Set a fixed height for the footer
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
          <Grid item xs={12} sm={8}>
            <Box sx={{ display: 'flex', justifyContent: 'center' }}>
              <Link href="/" variant="body2" color="textSecondary" sx={{ mx: 1 }}>
                Home
              </Link>
              <Link href="/admin" variant="body2" color="textSecondary" sx={{ mx: 1 }}>
                Admin
              </Link>
              <Link href="/owner" variant="body2" color="textSecondary" sx={{ mx: 1 }}>
                Owner
              </Link>
              <Link href="/property" variant="body2" color="textSecondary" sx={{ mx: 1 }}>
                Properties
              </Link>
              <Link href="/repair" variant="body2" color="textSecondary" sx={{ mx: 1 }}>
                Repairs
              </Link>
              <Link href="/signup" variant="body2" color="textSecondary" sx={{ mx: 1 }}>
                Sign Up
              </Link>
              <Link href="/login" variant="body2" color="textSecondary" sx={{ mx: 1 }}>
                Login
              </Link>
            </Box>
          </Grid>
        </Grid>
      </Container>
    </Box>
  );
}

export default Footer;

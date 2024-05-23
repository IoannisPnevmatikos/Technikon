import React, { useState } from 'react';
import { Container, Typography, Box, Button, TextField, MenuItem } from '@mui/material';
import { useNavigate } from 'react-router-dom';

function RepairActionsPage() {
  const [activeForm, setActiveForm] = useState('');
  const navigate = useNavigate();

  const handleBackClick = () => {
    setActiveForm('');
    navigate('/repairs'); // This will update the URL to /repairs
  };

  const renderForm = () => {
    switch (activeForm) {
      case 'createRepair':
        return (
          <Box component="form" sx={{ mt: 3, width: '100%' }}>
            <TextField
              label="Local Date"
              type="date"
              fullWidth
              margin="normal"
              InputLabelProps={{
                shrink: true,
              }}
              required
            />
            <TextField
              label="Short Description"
              fullWidth
              margin="normal"
              required
            />
            <TextField
              label="Type of Repair"
              select
              fullWidth
              margin="normal"
              required
            >
              <MenuItem value="Type1">Type1</MenuItem>
              <MenuItem value="Type2">Type2</MenuItem>
              {/* Add more options as needed */}
            </TextField>
            <TextField
              label="Status of Repair"
              select
              fullWidth
              margin="normal"
              required
            >
              <MenuItem value="Status1">Status1</MenuItem>
              <MenuItem value="Status2">Status2</MenuItem>
              {/* Add more options as needed */}
            </TextField>
            <TextField
              label="Cost"
              type="number"
              fullWidth
              margin="normal"
              required
            />
            <TextField
              label="Description Text"
              fullWidth
              margin="normal"
              required
            />
            <TextField
              label="Property"
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
      case 'findRepairByDate':
        return (
          <Box component="form" sx={{ mt: 3, width: '100%' }}>
            <TextField
              label="Local Date"
              type="date"
              fullWidth
              margin="normal"
              InputLabelProps={{
                shrink: true,
              }}
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
      case 'findRepairByDateRange':
        return (
          <Box component="form" sx={{ mt: 3, width: '100%' }}>
            <TextField
              label="Starting Date"
              type="date"
              fullWidth
              margin="normal"
              InputLabelProps={{
                shrink: true,
              }}
              required
            />
            <TextField
              label="Ending Date"
              type="date"
              fullWidth
              margin="normal"
              InputLabelProps={{
                shrink: true,
              }}
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
      case 'updateRepair':
        return (
          <Box component="form" sx={{ mt: 3, width: '100%' }}>
            <TextField
              label="Local Date"
              type="date"
              fullWidth
              margin="normal"
              InputLabelProps={{
                shrink: true,
              }}
              required
            />
            <TextField
              label="Short Description"
              fullWidth
              margin="normal"
              required
            />
            <TextField
              label="Type of Repair"
              select
              fullWidth
              margin="normal"
              required
            >
              <MenuItem value="Type1">Type1</MenuItem>
              <MenuItem value="Type2">Type2</MenuItem>
              {/* Add more options as needed */}
            </TextField>
            <TextField
              label="Status of Repair"
              select
              fullWidth
              margin="normal"
              required
            >
              <MenuItem value="Status1">Status1</MenuItem>
              <MenuItem value="Status2">Status2</MenuItem>
              {/* Add more options as needed */}
            </TextField>
            <TextField
              label="Cost"
              type="number"
              fullWidth
              margin="normal"
              required
            />
            <TextField
              label="Description Text"
              fullWidth
              margin="normal"
              required
            />
            <TextField
              label="Property"
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
        case 'deleteRepair':
        return (
          <Box component="form" sx={{ mt: 3, width: '100%' }}>
            <TextField
              select
              label="Select Repair ID"
              fullWidth
              value=""
              onChange={() => {}}
              margin="normal"
              required
            >
              <MenuItem value="ID1">ID1</MenuItem>
              <MenuItem value="ID2">ID2</MenuItem>
              <MenuItem value="ID3">ID3</MenuItem>
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
          Repair Actions
        </Typography>
        {activeForm ? (
          renderForm()
        ) : (
          <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2, mt: 4 }}>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('createRepair')}>
              Create a Repair
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('findRepairByDate')}>
              Find a Repair by Date
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('findRepairByDateRange')}>
              Find a Repair by Date Range
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('myRepairs')}>
              My Repairs
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('updateRepair')}>
              Update a Repair
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('deleteRepair')}>
              Delete a Repair
            </Button>
            <Button variant="contained" color="primary" onClick={() => setActiveForm('getAllRepairs')}>
              Get All my Repairs
            </Button>
          </Box>
        )}
      </Box>
    </Container>
  );
}

export default RepairActionsPage;
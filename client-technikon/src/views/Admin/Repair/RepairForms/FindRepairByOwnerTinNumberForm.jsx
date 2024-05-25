import React from 'react';
import { Box, Button, TextField, MenuItem } from '@mui/material';


const handleKeyDown = (event) => {
    // Prevent input of period (".")
    if (event.key === '.') {
        event.preventDefault();
    }
};

const FindRepairByOwnerTinNumberForm = ({ handleSubmit, handleBackClick }) => (
    <Box component="form" sx={{ mt: 3, width: '100%' }} onSubmit={handleSubmit}>
        <TextField
            name="ownerTinNumber"
            label="OwnerTinNumber"
            type="number"
            fullWidth
            margin="normal"
            required
            inputProps={{
                inputMode: 'numeric',
                pattern: '^[0-9]+$',
                onKeyDown: handleKeyDown // Call handleKeyDown event handler
            }}
        />
        <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>
            Search
        </Button>
        <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick}>
            Back
        </Button>
    </Box>
);

export default FindRepairByOwnerTinNumberForm;
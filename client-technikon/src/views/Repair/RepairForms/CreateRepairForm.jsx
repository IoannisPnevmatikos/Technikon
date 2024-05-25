import React, { useState } from 'react';
import { Box, Button, TextField, MenuItem, CircularProgress } from '@mui/material';
import { createRepair } from '../api/Repair/User/createRepair'; // Import the createRepair function

const handleKeyDown = (event) => {
    // Prevent input of period (".")
    if (event.key === '.') {
        event.preventDefault();
    }
};

const CreateRepairForm = ({ handleSubmit, handleBackClick }) => {
    const [isLoading, setIsLoading] = useState(false); // State to track loading status

    const onSubmit = async (event) => {
        event.preventDefault();
        setIsLoading(true); // Set loading to true when form is submitted
        try {
            await handleSubmit(event); // Call the handleSubmit function passed as prop
            alert('Repair created!');
            // Handle navigation or any other success action
        } catch (error) {
            console.error('Repair creation failed:', error);
            alert('Repair creation failed. Please try again.');
            // Handle error state or display error message
        } finally {
            setIsLoading(false); // Reset loading state
        }
    };

    return (
        <Box sx={{ mt: 3, width: '100%' }}>
            <form onSubmit={onSubmit}>
                {/* Your form fields */}
                <TextField
                    name="localDate"
                    label="Local Date"
                    type="date"
                    fullWidth
                    margin="normal"
                    InputLabelProps={{
                        shrink: true,
                    }}
                    required
                />
                {/* Other text fields */}
                <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>
                    {isLoading ? <CircularProgress size={24} /> : 'Submit'}
                </Button>
                <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick} disabled={isLoading}>
                    Back
                </Button>
            </form>
        </Box>
    );
};

export default CreateRepairForm;

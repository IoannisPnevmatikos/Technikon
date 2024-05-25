import React, { useState } from 'react';
import { Box, Button, TextField, CircularProgress } from '@mui/material';

const handleKeyDown = (event) => {
    // Prevent input of period (".")
    if (event.key === '.') {
        event.preventDefault();
    }
};

const DeleteRepairForm = ({ handleSubmit, handleBackClick }) => {
    const [isLoading, setIsLoading] = useState(false); // State to track loading status

    const onSubmit = async (event) => {
        event.preventDefault();
        setIsLoading(true); // Set loading to true when form is submitted
        try {
            await handleSubmit(event); // Call the handleSubmit function passed as prop
            alert('Repair deleted!');
            // Handle navigation or any other success action
        } catch (error) {
            console.error('Repair deletion failed:', error);
            alert('Repair deletion failed. Please try again.');
            // Handle error state or display error message
        } finally {
            setIsLoading(false); // Reset loading state
        }
    };

    return (
        <Box sx={{ mt: 3, width: '100%' }}>
            <form onSubmit={onSubmit}>
                <TextField
                    name="repairId"
                    label="RepairId"
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
                    {isLoading ? <CircularProgress size={24} /> : 'Delete'}
                </Button>
                <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick} disabled={isLoading}>
                    Back
                </Button>
            </form>
        </Box>
    );
};

export default DeleteRepairForm;

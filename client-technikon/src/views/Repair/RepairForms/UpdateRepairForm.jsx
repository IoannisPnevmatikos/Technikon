import React, { useState } from 'react';
import { Box, Button, TextField, MenuItem } from '@mui/material';

const handleKeyDown = (event) => {
    // Prevent input of period (".")
    if (event.key === '.') {
        event.preventDefault();
    }
};

const UpdateRepairForm = ({ handleSubmit, handleBackClick }) => {
    const [isLoading, setIsLoading] = useState(false); // Add loading state

    const handleFormSubmit = async (event) => {
        event.preventDefault();
        setIsLoading(true); // Set loading state to true

        try {
            await handleSubmit(event);
            setIsLoading(false); // Reset loading state when submission is complete
        } catch (error) {
            setIsLoading(false); // Reset loading state if submission fails
            console.error('Error occurred:', error);
            alert('Error occurred. Please try again.');
        }
    };

    return (
        <Box component="form" sx={{ mt: 3, width: '100%' }} onSubmit={handleFormSubmit}>
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
                disabled={isLoading} // Disable input field while loading
            />
            <TextField
                name="shortDescription"
                label="Short Description"
                fullWidth
                margin="normal"
                required
                disabled={isLoading} // Disable input field while loading
            />
            <TextField
                name="typeOfRepair"
                label="Type of Repair"
                select
                fullWidth
                margin="normal"
                required
                disabled={isLoading} // Disable input field while loading
            >
                <MenuItem value="PAINTING">Painting</MenuItem>
                <MenuItem value="INSULATION">Insulation</MenuItem>
                <MenuItem value="FRAMES">Frames</MenuItem>
                <MenuItem value="PLUMING">Pluming</MenuItem>
                <MenuItem value="ELECTRICAL_WORK">Electrical Work</MenuItem>
                {/* Add more options as needed */}
            </TextField>
            <TextField
                name="statusOfRepair"
                label="Status of Repair"
                select
                fullWidth
                margin="normal"
                required
                disabled={isLoading} // Disable input field while loading
            >
                <MenuItem value="SCHDULED">Scheduled</MenuItem>
                <MenuItem value="IN_PROGRESS">In Progress</MenuItem>
                <MenuItem value="COMPLETE">Complete</MenuItem>
                <MenuItem value="PENDING">Pending</MenuItem>
                {/* Add more options as needed */}
            </TextField>
            <TextField
                name="cost"
                label="Cost"
                type="number"
                fullWidth
                margin="normal"
                required
                disabled={isLoading} // Disable input field while loading
                inputProps={{
                    inputMode: 'decimal',
                    step: 0.01, // Allow up to 2 decimal places
                    max: 999999, // Maximum value allowed
                    title: 'Please enter a number between 0 and 999999.99', // Tooltip for validation guidance
                }}
            />
            <TextField
                name="descriptionText"
                label="Description Text"
                fullWidth
                margin="normal"
                required
                disabled={isLoading} // Disable input field while loading
            />
            <TextField
                name="repairId"
                label="RepairId"
                fullWidth
                margin="normal"
                required
                type="number" // Set type to number
                inputProps={{
                    step: 1, // Set step to 1 to allow only integer values
                    onKeyDown: handleKeyDown
                }}
                disabled={isLoading} // Disable input field while loading
            />
            <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }} disabled={isLoading}>
                {isLoading ? 'Loading...' : 'Submit'} {/* Change button text based on loading state */}
            </Button>
            <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick} disabled={isLoading}>
                Back
            </Button>
        </Box>
    );
};

export default UpdateRepairForm;

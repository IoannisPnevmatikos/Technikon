import React, { useState } from 'react';
import { Box, Button, TextField, MenuItem, CircularProgress } from '@mui/material';

const handleKeyDown = (event) => {
    // Prevent input of period (".")
    if (event.key === '.') {
        event.preventDefault();
    }
};

const CreateRepairForm = ({ handleSubmit, handleBackClick }) => {
    const [isLoading, setIsLoading] = useState(false);

    const onSubmit = (event) => handleSubmit(event, setIsLoading);

    return (
        <Box component="form" sx={{ mt: 3, width: '100%' }} onSubmit={onSubmit}>
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
            <TextField
                name="shortDescription"
                label="Short Description"
                fullWidth
                margin="normal"
                required
            />
            <TextField
                name="typeOfRepair"
                label="Type of Repair"
                select
                fullWidth
                margin="normal"
                required
            >
                <MenuItem value="PAINTING">Painting</MenuItem>
                <MenuItem value="INSULATION">Insulation</MenuItem>
                <MenuItem value="FRAMES">Frames</MenuItem>
                <MenuItem value="PLUMING">Pluming</MenuItem>
                <MenuItem value="ELECTRICAL_WORK">Electrical Work</MenuItem>
            </TextField>
            <TextField
                name="statusOfRepair"
                label="Status of Repair"
                select
                fullWidth
                margin="normal"
                required
            >
                <MenuItem value="SCHDULED">Scheduled</MenuItem>
                <MenuItem value="IN_PROGRESS">In Progress</MenuItem>
                <MenuItem value="COMPLETE">Complete</MenuItem>
                <MenuItem value="PENDING">Pending</MenuItem>
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
            />
            <TextField
                name="propertyId"
                label="PropertyId"
                fullWidth
                margin="normal"
                required
                inputProps={{
                    pattern: "[0-9]{11}",
                    maxLength: 11,
                    inputMode: 'numeric',
                    onKeyDown: handleKeyDown // Call handleKeyDown event handler
                }}
            />
            <Box sx={{ position: 'relative' }}>
                <Button
                    type="submit"
                    variant="contained"
                    color="primary"
                    fullWidth
                    sx={{ mt: 2 }}
                    disabled={isLoading}
                >
                    {isLoading ? 'Submitting...' : 'Submit'}
                </Button>
                {isLoading && (
                    <CircularProgress
                        size={24}
                        sx={{
                            position: 'absolute',
                            top: '50%',
                            left: '50%',
                            marginTop: '-12px',
                            marginLeft: '-12px',
                        }}
                    />
                )}
            </Box>
            <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick} disabled={isLoading}>
                Back
            </Button>
        </Box>
    );
};

export default CreateRepairForm;

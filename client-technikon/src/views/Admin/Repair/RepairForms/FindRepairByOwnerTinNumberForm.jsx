import React, { useState } from 'react';
import { Box, Button, TextField, CircularProgress } from '@mui/material';

const handleKeyDown = (event) => {
    // Prevent input of period (".")
    if (event.key === '.') {
        event.preventDefault();
    }
};

const FindRepairByOwnerTinNumberForm = ({ handleSubmit, handleBackClick }) => {
    const [isLoading, setIsLoading] = useState(false);

    const onSubmit = (event) => {
        event.preventDefault();
        setIsLoading(true);
        handleSubmit(event, setIsLoading);
    };

    return (
        <Box component="form" sx={{ mt: 3, width: '100%' }} onSubmit={onSubmit}>
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
            <Box sx={{ position: 'relative' }}>
                <Button
                    type="submit"
                    variant="contained"
                    color="primary"
                    fullWidth
                    sx={{ mt: 2 }}
                    disabled={isLoading}
                >
                    {isLoading ? 'Searching...' : 'Search'}
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
            <Button
                variant="outlined"
                color="secondary"
                fullWidth
                sx={{ mt: 2 }}
                onClick={handleBackClick}
                disabled={isLoading}
            >
                Back
            </Button>
        </Box>
    );
};

export default FindRepairByOwnerTinNumberForm;

import React, { useEffect, useRef } from 'react';
import Box from '@mui/material/Box';

const MyRepairsForm = ({ handleSubmit, handleBackClick }) => {
    const formRef = useRef(null);

    useEffect(() => {
        // Trigger the form submission after the component mounts
        if (formRef.current) {
            formRef.current.submit();
        }
    }, []);

    return (
        <Box
            component="form"
            ref={formRef}
            sx={{ mt: 3, width: '100%' }}
            onSubmit={handleSubmit}
        >
            {/* Form content goes here */}
        </Box>
    );
};

export default MyRepairsForm;

import React, { useState, useEffect } from 'react';
import { Box, Button, TextField, CircularProgress, Typography } from '@mui/material';
import DatePicker from "react-datepicker";
import { Link, useNavigate } from 'react-router-dom';
import "react-datepicker/dist/react-datepicker.css";

const FindOwnerByDateForm = ({ handleSubmit, handleBackClick }) => {
    const navigate = useNavigate();
    const [isLoading, setIsLoading] = useState(false);
    const [ownerData, setOwnerData] = useState(null);
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);


    const onSubmit = async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);

        setIsLoading(true)
        const response = await handleSubmit(formData, setIsLoading);
        console.log(response)
        try {
            const data = await handleSubmit(formData);
            setOwnerData(data);
            console.log(data)
       
        } catch (error) {
            console.error(error);
            alert('Failed to find owner');
        } finally {
            setIsLoading(false);
        }
    };

    return (<Box component="form" sx={{ width: '100%' }} onSubmit={onSubmit}>
        <Typography variant="h6">Choose a date:</Typography>
        <DatePicker selected={startDate}    name="startDate"
        label="StartDate" onChange={(date) => setStartDate(date)} />
        <DatePicker selected={endDate}    name="endDate"
        label="EndDate" onChange={(date) => setEndDate(date)} />
        <Box sx={{ position: 'relative' }}>
            <Button
             
                type="submit"
                variant="contained"
                color="primary"
                fullWidth
                sx={{ mt: 2 }}
                disabled={isLoading}
            >
                {isLoading ? 'Submitting...' : ' Get Owners'}
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
}
export default FindOwnerByDateForm
import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { Typography, CircularProgress, Button, Grid, Card } from '@mui/material';
import { experimentalStyled as styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import useToken from '../../../../stores/useToken';
import findAllOwners from '../../../../api/Owner/Admin/findAllOwners';
import { paths } from '../../../../constants/paths/paths'

const FindAllOwners = ({ handleSubmit, handleBackClick }) => {
    const Item = styled(Paper)(({ theme }) => ({
        backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
        ...theme.typography.body2,
        padding: theme.spacing(1),
        textAlign: 'left',
        color: theme.palette.text.secondary,
    }));

    const [owners, setOwners] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const { token } = useToken();
    const navigate = useNavigate();

    const handleOnClick = () => {
        navigate(paths.adminOwner)
    }

    useEffect(() => {
        const fetchOwners = async () => {
            try {
                const ownersData = await findAllOwners(token?.data);
                setOwners(ownersData.data);
            } catch (error) {
                console.error('Error fetching owners:', error);
            } finally {
                setIsLoading(false);
            }
        };

        fetchOwners();
    }, [token]);

    if (isLoading) {
        return (
            <Box
                sx={{
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                    minHeight: '100vh',
                }}
            >
                <CircularProgress />
            </Box>
        );
    }

    if (!owners || owners.length === 0) {
        return (
            <Box
                sx={{
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                    minHeight: '100vh',
                }}
            >
                <Typography variant="body1">No owners found</Typography>
            </Box>
        );
    }



    return (<>
        <Typography variant="h3" gutterBottom>
            All Owners
        </Typography>
        <Box sx={{ flexGrow: 1 }}>
        <Grid container rowSpacing={{ xs: 1, md: 2 }} columnSpacing={{ xs: 4, sm: 8, md: 12 }}>
            {owners.map((owner, index) => (
                <Grid xs={12} sm={6} md={4} key={index}>
                    <Item>
                        <Card>
                            <Typography variant="h6" >Username: {owner.username}</Typography>
                            <Typography variant="h6" >TIN number: {owner.tinNumber}</Typography>
                            <Typography variant="h6">First name: {owner.firstName}</Typography>
                            <Typography variant="h6">Last name: {owner.lastName}</Typography>
                            <Typography variant="h6" sx={{ fontSize: 14 }}>Email: {owner.email}</Typography>
                            <Typography variant="h6">Address: {owner.address}</Typography>
                            <Typography variant="h6">Phone: {owner.phone}</Typography>
                        </Card>
                    </Item>
                </Grid>
            ))}

        </Grid>
        <Button onClick={handleOnClick} variant="outlined" sx={{ mb: 2 }}>Back to Owners Menu</Button>
        </Box>
    </>)

};

export default FindAllOwners
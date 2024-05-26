import { Grid, Box, Button, Typography,Card } from '@mui/material';
import { experimentalStyled as styled } from '@mui/material/styles';
import Paper from '@mui/material/Paper';

const OwnerList = (list) =>{
    const array = list.data 
    console.log(array)

    const Item = styled(Paper)(({ theme }) => ({
        backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
        ...theme.typography.body2,
        padding: theme.spacing(1),
        textAlign: 'left',
        color: theme.palette.text.secondary,
    }));

    const handleOnClick = () => {
        navigate(paths.adminOwner)
    }

    return(<>
        <Box sx={{ flexGrow: 1 }}>
        <Grid container rowSpacing={{ xs: 1, md: 2 }} columnSpacing={{ xs: 4, sm: 8, md: 12 }}>
            {array.map((owner, index) => (
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

        </Box>

    </>);
}
export default OwnerList
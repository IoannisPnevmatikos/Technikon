import React, {useState} from 'react';
import { Box, Button, TextField, CircularProgress } from '@mui/material';

const DeactivateOwnerForm = ({ handleSubmit, handleBackClick }) => {
    const [isLoading, setIsLoading] = useState(false);
    return (<Box sx={{ mt: 1, width: '100%', maxHeight: 'calc(100vh - 200px)', overflowY: 'auto' }}>
        <Box component="form" sx={{ width: '100%' }} onSubmit={(event) => {handleSubmit(event, setIsLoading)}} >
            <TextField
                label="Username"
                type="text"
                name="username"
                fullWidth
            required
            />
             <Button
          type="submit"
          variant="contained"
          color="primary"
          fullWidth
          sx={{ mt: 2 }}
          disabled={isLoading}
        >
     {isLoading ? 'Submitting...' : 'Confirm Deactivation'}

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
      <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick}  disabled={isLoading}>
        Back
      </Button>
        </Box>
    </Box>)

}

export default DeactivateOwnerForm
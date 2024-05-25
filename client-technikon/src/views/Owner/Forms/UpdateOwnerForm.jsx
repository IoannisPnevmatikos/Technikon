import React from 'react';
import { Box, Button, TextField } from '@mui/material';

const UpdateOwnerForm = ({ handleSubmit, handleBackClick }) => (
<Box sx={{ mt: 3, width: '100%', maxHeight: 'calc(100vh - 200px)', overflowY: 'auto' }}>
    <Box component="form" sx={{ width: '100%' }} onSubmit={handleSubmit}>
      <TextField
        name="tinNumber"
        label="Tin Number"
        fullWidth
        margin="normal"
        required
        inputProps={{ pattern: '^[0-9]{9}$', maxLength: 9, }}
      />
      <TextField
        label="Username"
        type="text"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        required
        fullWidth
        margin="normal"
        inputProps={{
          pattern: '^[A-Za-z]\\w{5,29}$',
          minLength: 6,
          maxLength: 30,
        }}
      />
      <TextField
        label="Password"
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        required
        fullWidth
        margin="normal"
      />
      <TextField
        name="address"
        label="address"
        type="text"
        select
        fullWidth
        margin="normal"
        required
      >
        <TextField
          label="FirstName"
          type="text"
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
          required
          fullWidth
          margin="normal"
          inputProps={{
            pattern: "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$",
            maxLength: 30,
          }}
        />
      </TextField>
      <TextField
        label="LastName"
        type="text"
        value={lastName}
        onChange={(e) => setLastName(e.target.value)}
        required
        fullWidth
        margin="normal"
        inputProps={{
          pattern: "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$",
          maxLength: 30,
        }}
      />

      <TextField
        label="Email"
        type="text"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        required
        fullWidth
        margin="normal"
        inputProps={{
          pattern: "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",
        }}
      />

      <TextField
        label="Phone"
        type="phone"
        value={phone}
        onChange={(e) => setPhone(e.target.value)}
        required
        fullWidth
        margin="normal"
      />
      <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>
        Submit
      </Button>
      <Button variant="outlined" color="secondary" fullWidth sx={{ mt: 2 }} onClick={handleBackClick}>
        Back
      </Button>
    </Box>
  </Box>
);


export default UpdateOwnerForm

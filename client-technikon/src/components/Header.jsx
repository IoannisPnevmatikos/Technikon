import { Link } from "react-router-dom";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import { paths } from "./../constants/paths/paths";
import useToken from "../stores/useToken";

const Header = () => {
  const { token, logout } = useToken();

  const handleLogout =  () => {
    logout();
    alert('You have successfully logged out.');
  };

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="fixed" sx={{ width: '100%', height: '64px' }}>
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
          >
            <MenuIcon />
          </IconButton>
          <Button color="inherit" component={Link} to={paths.home} sx={{ flexGrow: 1 }}>
            Home
          </Button>
          <Button color="inherit" component={Link} to={paths.admin} sx={{ flexGrow: 1 }}>
            Admin
          </Button>
          <Button color="inherit" component={Link} to={paths.owner} sx={{ flexGrow: 1 }}>
            Owner
          </Button>
          <Button color="inherit" component={Link} to={paths.property} sx={{ flexGrow: 1 }}>
            Properties
          </Button>
          <Button color="inherit" component={Link} to={paths.repair} sx={{ flexGrow: 1 }}>
            Repairs
          </Button>
          {token ? (
            <Button color="inherit" onClick={handleLogout} sx={{ flexGrow: 1}}>
              Logout
            </Button>
          ) : (
            <>
              <Button color="inherit" component={Link} to={paths.signup} sx={{ flexGrow: 1 }}>
                Sign Up
              </Button>
              <Button color="inherit" component={Link} to={paths.login} sx={{ flexGrow: 1 }}>
                Login
              </Button>
            </>
          )}
        </Toolbar>
      </AppBar>
    </Box>
  );
};

export default Header;

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
import logout from "../api/Logout/logout";

const Header = () => {
  
  const {token} = useToken();
  function handleLogout() {
    logout();
    navigate(paths.owner);
  }

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
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            <Link to={paths.home}>Home</Link>
          </Typography>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            <Link to={paths.admin}>Admin</Link>
          </Typography>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            <Link to={paths.owner}>Owner</Link>
          </Typography>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            <Link to={paths.property}>Properties</Link>
          </Typography>     
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            <Link to={paths.repair}>Repairs</Link>
          </Typography>     
      
           {token ==null ? (
            <>
             <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            <Link to={paths.signup}>Sign Up</Link>
          </Typography>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            <Link to={paths.login}>Login</Link>
          </Typography>
            </>
           ):(<>
                 <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            <Link to={paths.owner}>Profile</Link>
          </Typography>  
          <Button type="submit" id="logOutBtn" variant="contained" color="primary" size="small" sx={{ mt: 2 }} onClick={handleLogout}>
            LogOut
          </Button></>)
          }

          {/* 
           {user && (
            <>
              <Link to="/profile" id="profileIcon">
                <FontAwesomeIcon icon={faUser} />
              </Link>
              <Link
                to="/"
                className="button"
                id="logOutBtn"
                onClick={handleLogout}
              >
                <strong>logOut</strong>
              </Link>{' '}
            </>
          )}
          {!user && (
            <div className="buttons">
              <Link to="/login" className="button" id="loginBtn">
                <strong>Log in</strong>
              </Link>

              <Link to="/signUp" className="button" id="signUpBtn">
                <strong>Sign up</strong>
              </Link>
            </div>
          )}
          
          */}
         
        </Toolbar>
      </AppBar>
    </Box>
  );
};

export default Header;

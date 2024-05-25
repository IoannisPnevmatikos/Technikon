import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MainLayout from "./layouts/MainLayout";
import Home from "./views/Home/Home";
import Login from "./views/Login/Login";
import Sign from "./views/Sign/Sign";
import Admin from "./views/Admin/Admin";
import Owner from "./views/Owner/Owner";
import RepairActionsPage from "./views/Repair/RepairActionsPage";
import Property from "./views/Property/Property"
import PropertyAdmin from "./views/Admin/Property/PropertyAdmin";
import { paths } from "./constants/paths/paths";
import PropertyDetails from "./views/Property/PropertyDetails/PropertyDetails";

function App() {
  return (
    <Router>
      <Routes>
        <Route element={<MainLayout />}>
          <Route path={paths.home} element={<Home />} />
          <Route path={paths.login} element={<Login />} />
          <Route path={paths.signup} element={<Sign />} />
          <Route path={paths.admin} element={<Admin />} />
          <Route path={paths.owner} element={<Owner />} />
          <Route path={paths.repair} element={<RepairActionsPage />} />
          <Route path={paths.property} element={<Property />} />
          <Route path={paths.adminProperty} element={<PropertyAdmin />} />
          <Route path="property/:id" element={<PropertyDetails />} />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;

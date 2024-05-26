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
import RepairAdminActionsPage from "./views/Admin/Repair/RepairAdminActionPage";
import PropertyResDetails from "./views/Property/PropertyDetails/PropertyResDetails";
import ListPropertyDetails from "./views/Property/PropertyDetails/ListPropertyDetails";
import PropertyResDetailsAdmin from "./views/Admin/Property/PropertyDetails/PropertyResDetailsAdmin";
import ListPropertyDetailsAdmin from "./views/Admin/Property/PropertyDetails/ListPropertyDetailsAdmin";
import ReportDetailsAdmin from "./views/Admin/Property/PropertyDetails/ReportDetailsAdmin";

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
          <Route path={paths.adminRepair} element={<RepairAdminActionsPage />} />
          <Route path={paths.propertyResult} element={<PropertyResDetails />} />
          <Route path={paths.listPropertyResult} element={<ListPropertyDetails />} />
          <Route path={paths.propertyResultAdmin} element={<PropertyResDetailsAdmin />} />
          <Route path={paths.listPropertyResultAdmin} element={<ListPropertyDetailsAdmin />} />
          <Route path={paths.reportPropertytAdmin} element={<ReportDetailsAdmin />} />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;

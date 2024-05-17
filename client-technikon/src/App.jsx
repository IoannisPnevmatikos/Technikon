import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MainLayout from "./layouts/MainLayout";
import Home from "./views/Home/Home";
import Admin from "./views/Admin";
import Owner from "./views/Owner";

function App() {
  return (
    <Router>
      <Routes>
        <Route element={<MainLayout />}>
          <Route path="/" element={<Home />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/owner" element={<Owner />} />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;

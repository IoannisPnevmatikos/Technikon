import { create } from "zustand";
import login from "../api/Login/login";
import logout from "../api/Logout/logout";

const useToken = create((set) => ({
  token: null,
  login: async (credentials) => {
    try {
      const token = await login(credentials);
      set({ token });
      localStorage.setItem('token', token.data);
      return { status: 200, token }; // Return status and token on success
    } catch (error) {
      console.error('Login failed:', error);
      return { status: 401, error }; // Return status and error on failure
    }
  },
  logout: async () => {
    await logout();
    set({ token: null })
    localStorage.setItem('token', null);

  },
}));

export default useToken;

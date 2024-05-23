import { create } from "zustand";
import login from "../api/Login/login"

 const useToken = create((set) => ({
  token: null,
  login: async (credentials) => {
    try {
      const token = await login(credentials);
      console.log(token)
      set((state) => ({token: state.token}));
   
    } catch (error) {
      console.error('Login failed:', error);
    }  },  
  logout: () => set({ token: null }),
}));

export default useToken;
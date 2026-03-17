"use client";

import { createContext, useContext, useState, ReactNode, useEffect } from "react";
import { signupApi, loginApi } from "@/services/authService";

interface AuthContextType {
  token: string | null;
  isAuthenticated: boolean;
  signupAndLogin: (name: string, email: string, password: string) => Promise<void>;
  login: (email: string, password: string) => Promise<void>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [token, setToken] = useState<string | null>(null);

  // 자동로그인: 로컬 스토리지에 토큰이 있으면 바로 set
  useEffect(() => {
    const savedToken = localStorage.getItem("accessToken");
    if (savedToken) setToken(savedToken);
  }, []);

  const signupAndLogin = async (name: string, email: string, password: string) => {
    await signupApi(name, email, password);
    const res = await loginApi(email, password);
    if (res.data) {
      setToken(res.data.accessToken);
      localStorage.setItem("accessToken", res.data.accessToken);
    }
    
  };

  const login = async (email: string, password: string) => {
    const res = await loginApi(email, password);
    if (res.data) {
      setToken(res.data.accessToken);
      localStorage.setItem("accessToken", res.data.accessToken);
    }
  };

  const logout = () => {
    setToken(null);
    localStorage.removeItem("accessToken");
  };

  return (
    <AuthContext.Provider value={{ token, isAuthenticated: !!token, signupAndLogin, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) throw new Error("useAuth must be used within AuthProvider");
  return context;
}
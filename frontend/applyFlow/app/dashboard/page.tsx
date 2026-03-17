"use client";

import { useAuth } from "@/context/AuthContext";

const DashboardPage = () => {
  const { isAuthenticated, logout } = useAuth();

  if (!isAuthenticated) return <p>You are not logged in</p>;

  return (
    <div>
      <h1>Welcome to Dashboard!</h1>
      <button onClick={logout}>Logout</button>
    </div>
  );
}
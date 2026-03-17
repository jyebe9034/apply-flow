import { apiFetch } from "./api";

export interface AuthResponse {
  accessToken: string;
  tokenString: string;
}

export const signupApi = async (
  name: string,
  email: string,
  password: string,
) => {
  return apiFetch("/api/auth/signup", {
    method: "POST",
    body: JSON.stringify({
      name,
      email,
      password,
    }),
  });
};

export const loginApi = async (
  email: string,
  password: string,
)=> {
  return apiFetch<AuthResponse>("/api/auth/login", {
    method: "POST",
    body: JSON.stringify({
      email,
      password,
    }),
  });
};

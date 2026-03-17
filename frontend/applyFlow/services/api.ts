import { env } from "@/config/env";

export interface ApiResponse<T = void> {
  success: boolean;
  message: string;
  data: T | null;
}

export async function apiFetch<T = void>(
  path: string, 
  options?: RequestInit
): Promise<ApiResponse<T>> {

  const token =
    typeof window !== "undefined"
      ? localStorage.getItem("accessToken")
      : null;

  const res = await fetch(`${env.API_URL}${path}`, {
    ...options,
    headers: {
      "Content-type": "application/json",
      ...(token && { Authorization: `Bearer ${token}` }),
      ...(options?.headers ?? {}),
    },
  });

  const json: ApiResponse<T> = await res.json();

  if (!res.ok) {
    throw new Error(json.message);
  }

  return json;
}

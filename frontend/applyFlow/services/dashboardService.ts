import { apiFetch } from "./api"
import { DashboardResponse } from "@/types/dashboard"

export const getDashboardApi = async () => {
  return apiFetch<DashboardResponse>("/api/dashboard");
}
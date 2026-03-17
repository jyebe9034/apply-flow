import { apiFetch } from "./api";
import { JobResponse, JobCreateRequest, JobUpdateRequest } from "@/types/job";

export interface PageResoonse<T> {
  content: T[];
  totalElement: number;
  totalPages: number;
  number: number;
  size: number;
}

export const getJobsApi = async (page = 0, size = 10) => {
  return apiFetch<PageResoonse<JobResponse>>(
    `/api/jobs?page=${page}&size=${size}`
  );
};

export const getJobApi = async (id: number) => {
  return apiFetch<JobResponse>(`/api/jobs/${id}`);
};

export const createJobApi = async (request: JobCreateRequest) => {
  return apiFetch<JobResponse>("/api/jobs", {
    method: "POST",
    body: JSON.stringify(request),
  });
};

export const updateJobApi = async (id: number, request: JobUpdateRequest) => {
  return apiFetch<JobResponse>(`/api/jobs/${id}`, {
    method: "PATCH",
    body: JSON.stringify(request),
  });
};

export const deleteJobApi = async (id: number) => {
  return apiFetch(`/api/jobs/${id}`, {
    method: "DELETE",
  });
};
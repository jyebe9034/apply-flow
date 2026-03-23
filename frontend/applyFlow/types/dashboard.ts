import { JobResponse, JobStatus } from "./job";

export interface DashboardResponse {
  totalCount: number;
  statusCount: Partial<Record<JobStatus, number>>;
  recentJobs: JobResponse[];
  upcomingInterviews: JobResponse[];
}
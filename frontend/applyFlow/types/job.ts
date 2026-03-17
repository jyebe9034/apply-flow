export type JobStatus = 
  | "APPLIED"
  | "DOCUMENT_PASSED"
  | "INTERVIEW"
  | "OFFER"
  | "REJECTED";

export type JobPlatform = 
  | "LINKEDIN"
  | "INDEED"
  | "DIRECT_APPLY"
  | "OTHER";

export interface JobResponse {
  id : number;
  companyName: string;
  position: string;
  status: JobStatus;
  appliedAt: string;
  jobUrl: string | null;
  salary: number | null;
  location: string | null;
  platform: JobPlatform | null;
  contactName: string | null;
  contactEmail: string | null;
  contactPhone: string| null;
  interviewDateTime: string | null;
  memo: string | null;
}

export interface JobCreateRequest {
  companyName: string;
  position: string;
  status: JobStatus;
  appliedAt: string;
  jobUrl?: string;
  salary?: number;
  location?: string;
  platform?: JobPlatform;
  contactName?: string;
  contactEmail?: string;
  contactPhone?: string;
  interviewDateTime?: string;
  memo?: string;
}

export interface JobUpdateRequest {
  position?: string;
  status?: JobStatus;
  jobUrl?: string;
  salary?: number;
  location?: string;
  platform?: JobPlatform;
  contactName?: string;
  contactEmail?: string;
  contactPhone?: string;
  interviewDateTime?: string;
  memo?: string;
}

export const JOB_STATUS_LABEL: Record<JobStatus, string> = {
  APPLIED: "Applied",
  DOCUMENT_PASSED: "Document Passed",
  INTERVIEW: "Interview",
  OFFER: "Offer",
  REJECTED: "Rejected",
};

export const JOB_PLATFORM_LABEL: Record<JobPlatform, string> = {
  LINKEDIN: "LinkedIn",
  INDEED: "Indeed",
  DIRECT_APPLY: "Direct Apply",
  OTHER: "Other"
}


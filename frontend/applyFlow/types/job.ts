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

export const STATUS_COLOR: Record<JobStatus, string> = {
  APPLIED: "bg-blue-50 text-blue-600 border-blue-100",
  DOCUMENT_PASSED: "bg-purple-50 text-purple-600 border-purple-100",
  INTERVIEW: "bg-yellow-50 text-yellow-600 border-yellow-100",
  OFFER: "bg-green-50 text-green-600 border-green-100",
  REJECTED: "bg-red-50 text-red-500 border-red-100",
};

export const JOB_STATUSES: JobStatus[] = [
  "APPLIED",
  "DOCUMENT_PASSED",
  "INTERVIEW",
  "OFFER",
  "REJECTED",
];

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


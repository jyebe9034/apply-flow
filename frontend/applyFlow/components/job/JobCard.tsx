"use client";

import { useRouter } from "next/navigation";
import { JobResponse, JOB_STATUS_LABEL, JobStatus } from "@/types/job";

interface JobCardProps {
  job: JobResponse;
  onDelete: (id: number) => void;
}

const STATUS_COLOR: Record<JobStatus, string> = {
  APPLIED: "bg-blue-50 text-blue-600 border-blue-100",
  DOCUMENT_PASSED: "bg-purple-50 text-purple-600 border-purple-100",
  INTERVIEW: "bg-yellow-50 text-yellow-600 border-yellow-100",
  OFFER: "bg-green-50 text-green-600 border-green-100",
  REJECTED: "bg-red-50 text-red-500 border-red-100",
};

const JobCard = ({ job, onDelete }: JobCardProps) => {
  const router = useRouter();

  return (
    <div className="bg-white border border-gray-200 rounded-lg p-5 hover:border-gray-300 transition-colors duration-200">
      <div className="flex items-start justify-between gap-4">
        <div className="flex-1 min-w-0">
          <h3 className="text-sm font-medium text-gray-900 truncate">
            {job.companyName}
          </h3>
          <p className="text-sm text-gray-500 mt-0.5 truncate">{job.position}</p>
        </div>
        <span
          className={`shrink-0 text-xs font-medium px-2.5 py-1 rounded-full border ${STATUS_COLOR[job.status]}`}
        >
          {JOB_STATUS_LABEL[job.status]}
        </span>
      </div>

      <div className="mt-4 flex items-center justify-between">
        <div className="flex items-center gap-3 text-xs text-gray-400">
          <span>{job.appliedAt}</span>
          {job.location && <span>· {job.location}</span>}
          {job.platform && <span>· {job.platform}</span>}
        </div>

        <div className="flex items-center gap-2">
          <button
            onClick={() => router.push(`/jobs/${job.id}`)}
            className="text-xs text-gray-500 hover:text-blue-600 transition-colors duration-200"
          >
            View
          </button>
          <span className="text-gray-200">|</span>
          <button
            onClick={() => router.push(`/jobs/${job.id}/edit`)}
            className="text-xs text-gray-500 hover:text-blue-600 transition-colors duration-200"
          >
            Edit
          </button>
          <span className="text-gray-200">|</span>
          <button
            onClick={() => onDelete(job.id)}
            className="text-xs text-gray-500 hover:text-blue-600 transition-colors duration-200"
          >
            Delete
          </button>
        </div>
      </div>
    </div>
  );
};

export default JobCard;
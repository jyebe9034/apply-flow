"use client";

import { useEffect, useState } from "react";
import { useParams, useRouter } from "next/navigation";
import { JobResponse, JOB_STATUS_LABEL, JOB_PLATFORM_LABEL, JobStatus } from "@/types/job";
import { getJobApi, deleteJobApi } from "@/services/jobService";

const STATUS_COLOR: Record<JobStatus, string> = {
  APPLIED: "bg-blue-50 text-blue-600 border-blue-100",
  DOCUMENT_PASSED: " bg-purple-50 text-purple-600 border-purple-100",
  INTERVIEW: "bg-yellow-50 text-yellow-600 border-yellow-100",
  OFFER: "bg-green-50 text-green-600 border-green-100",
  REJECTED: "bg-red-50 text-red-500 border-red-100",
};

const Section = ({ label, value }: {label: string; value?: string | number | null }) => {
  if (!value) return null;
  return (
    <div className="space-y-1">
      <p className="text-xs font-medium text-gray-400 uppercase tracking-wider">{label}</p>
      <p className="text-sm text-gray-900">{value}</p>
    </div>
  );
};

export default function JobDetailPage() {
  const { id } = useParams();
  const router = useRouter();
  const [job, setJob] = useState<JobResponse | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchJob = async () => {
      try {
        const res = await getJobApi(Number(id));
        if (res.data) setJob(res.data);
      } catch (err: any) {
        setError(err?.message || "Fail to load job.");
      } finally {
        setLoading(false);
      }
    };
    fetchJob();
  }, [id]);

  const handleDelete = async () => {
    if (!confirm("Are you sure you want to delete this application?")) return;
    try {
      await deleteJobApi(Number(id));
      router.push("/jobs");
    } catch (err: any) {
      alert(err?.message || "Failed to delete.");
    }
  };

  if (loading) {
    return (
      <div className="flex items-center justify-center py-20">
        <p className="text-sm text-gray-400">Loding...</p>
      </div>
    );
  }

  if (error || !job) {
    return (
      <div className="flex items-center justify-center py-20">
        <p className="text-sm text-red-500">{error || "Job not found."}</p>
      </div>
    );
  }

  return (
    <div className="max-w-2xl mx-auto px-4 py-10">

      {/* Header */}
      <div className="flex items-start justify-between mb-8">
        <div>
          <h1 className="text-2xl font-light text-gray-900 tracking-tight">
            {job.companyName}
          </h1>
          <p className="text-sm text-gray-500 mt-1">{job.position}</p>
        </div>
        <span className={`text-xs font-medium px-2.5 py-1 rounded-full border ${STATUS_COLOR[job.status]}`}>
          {JOB_STATUS_LABEL[job.status]}
        </span>
      </div>

      {/* Basic Info */}
      <div className="bg-white border border-gray-200 rounded-lg p-6 space-y-4 mb-4">
        <p className="text-xs font-medium text-gray-400 uppercase tracking-wider mb-2">Basic Info</p>
        <div className="grid grid-cols-2 gap-4">
          <Section label="Applied Date" value={job.appliedAt} />
          <Section label="Location" value={job.location} />
          <Section label="Salary" value={job.salary} />
          <Section label="Platform" value={job.platform ? JOB_PLATFORM_LABEL[job.platform] : null} />
        </div>
        {job.jobUrl && (
          <div className="space-y-1">
            <p className="text-xs font-medium text-gray-400 uppercase tracking-wider">Job URL</p>
            <a
              href={job.jobUrl}
              target="_blank"
              rel="noopener noerferrer"
              className="text-sm text-blue-600 hover:underline break-all"
            >
              {job.jobUrl}
            </a>
          </div>
        )}
      </div>

      {/* Contact Info */}
      {(job.contactName || job.contactEmail || job.contactPhone) && (
        <div className="bg-white border border-gray-200 rounded-lg p-6 space-y-4 mb-4">
          <p className="text-xs font-medium text-gray-400 uppercase tracking-wider mb-2">Contact</p>
          <div className="grid grid-cols-2 gap-4">
            <Section label="Name" value={job.contactName} />
            <Section label="Email" value={job.contactEmail} />
            <Section label="Phone" value={job.contactPhone} />
          </div>
        </div>
      )}

      {/* Interview & Memo */}
      {(job.interviewDateTime || job.memo) && (
        <div className="bg-white border border-gray-200 rounded-lg p-6 space-y-4 mb-4">
          <p className="text-xs font-medium text-gray-400 uppercase tracking-wider mb-2">Notes</p>
          <Section label="Interview Date & Time" value={job.interviewDateTime} />
          <Section label="Memo" value={job.memo} />
        </div>
      )}

      {/* Action Button */}
      <div className="flex gap-3 mt-6">
        <button
          onClick={() => router.push("/jobs")}
          className="flex-1 bg-white border border-gray-200 hover:border-gray-400 text-gray-700 rounded-lg px-4 py-3 text-sm font-medium transition-colors duration-200"
        >
          ← Back to List
        </button>
        <button
          onClick={() => router.push(`/jobs/${id}/edit`)}
          className="flex-1 bg-blue-600 hover:bg-blue-700 text-white rounded-lg px-4 py-3 text-sm font-medium transition-colors duration-200"
        >
          Edit
        </button>
        <button
          onClick={handleDelete}
          className="bg-white border border-red-200 hover:bg-red-50 text-red-500 rounded-lg px-4 py-3 text-sm font-medium transition-colors duration-200"
        >
          Delete
        </button>
      </div>
      
    </div>
  );
}
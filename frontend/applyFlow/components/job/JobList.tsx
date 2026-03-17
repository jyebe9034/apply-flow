"use client";

import { useState, useEffect, useCallback } from "react";
import { useRouter } from "next/navigation";
import { JobResponse } from "@/types/job";
import { getJobsApi, deleteJobApi } from "@/services/jobService";
import JobCard from "./JobCard";

const JobList = () => {
  const router = useRouter();
  const [jobs, setJobs] = useState<JobResponse[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  const fetchJobs = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      const res = await getJobsApi(page);
      if (res.data) {
        setJobs(res.data.content);
        setTotalPages(res.data.totalPages);
      }
    } catch (err: any) {
      setError(err?.message || "Failed to load jobs.");
    } finally {
      setLoading(false);
    }
  }, [page]);

  useEffect(() => {
    fetchJobs();
  }, [fetchJobs]);

  const handelDelete = async (id: number) => {
    if (!confirm("Are you sure you want to delete this application?")) return;
    try {
      await deleteJobApi(id);
      fetchJobs();
    } catch (err: any) {
      alert(err?.message || "Failed to delete");
    }
  };

  if (loading) {
    return (
      <div className="flex items-center justify-center py-20">
        <p className="text-sm text-gray-400">Loading...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="flex items-center justify-center py-20">
        <p className="text-sm text-red-500">{error}</p>
      </div>
    );
  }

  return (
    <div className="max-w-3xl mx-auto px-4 py-10">
      {/* Header */}
      <div className="flex items-center justify-between mb-8">
        <div>
          <h1 className="text-2xl font-light text-gray-900 tracking-tight">
            Applications
          </h1>
          <p className="text-sm text-gray-500 mt-1 font-light">
            {jobs.length} application{jobs.length !== 1 ? "s" : ""} tracked
          </p>
        </div>
        <button
          onClick={() => router.push("/jobs/new")}
          className="bg-blue-600 hover:bg-blue-700 text-white rounded-lg px-4 py-2.5 text-sm font-medium transition-colors duration-200"
        >
          + New Application
        </button>
      </div>

      {/* Empty state */}
      {jobs.length === 0 ? (
        <div className="text-center py-20 border border-dashed border-gray-200 rounded-lg">
          <p className="text-sm text-gray-400 mb-4">No applications yet.</p>
          <button
            onClick={() => router.push("/jobs/new")}
            className="text-sm text-blue-600 hover:underline"
          >
            Add your first application →
          </button>
        </div>
      ) : (
        <div className="space-y-3">
          {jobs.map((job) => (
            <JobCard key={job.id} job={job} onDelete={handelDelete} />
          ))}
        </div>
      )}

      {/* Pagination */}
      {totalPages > 1 && (
        <div className="flex items-center justify-center gap-2 mt-8">
          <button
            onClick={() => setPage((p) => Math.max(0, p-1))}
            disabled={page === 0}
            className="text-sm text-gray-500 hover:text-blue-600 disabled:opacity-30 disabled:cursor-not-allowed transition-colors duration-200"
          >
            ← Prev
          </button>
          <span className="text-sm text-gray-400">
            {page + 1} / {totalPages}
          </span>
          <button
            onClick={() => setPage((p) => Math.min(totalPages - 1, p + 1))}
            disabled={page === totalPages - 1}
            className="text-sm text-gray-500 hover:text-blue-600 disabled:opacity-30 disabled:cursor-not-allowed transition-colors duration-200"
          >
            Next →
          </button>
        </div>
      )}
    </div>
  );
};

export default JobList;
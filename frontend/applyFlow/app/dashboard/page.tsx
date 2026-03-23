"use client";

import { getDashboardApi } from "@/services/dashboardService";
import { DashboardResponse } from "@/types/dashboard";
import { JOB_STATUSES, STATUS_COLOR, JOB_STATUS_LABEL } from "@/types/job";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

export default function DashboardPage() {
  const router = useRouter();
  const [data, setData] = useState<DashboardResponse | null> (null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchDashboard = async () => {
      try {
        const res = await getDashboardApi();
        if (res.data) setData(res.data);
      } catch (err: any) {
        setError(err?.message || "Failed to load dashboard.");
      } finally {
        setLoading(false);
      }
    };
    fetchDashboard();
  }, []);

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
            Dashboard
          </h1>
          <p className="text-sm text-gray-500 mt-1 font-light">
            {data?.totalCount ?? 0} total applications
          </p>
        </div>
        <button
          onClick={() => router.push("/jobs/new")}
          className="bg-blue-600 hover:bg-blue-700 text-white rounded-lg px-4 py-2.5 text-sm font-medium transition-colors duration-200"
        >
          + New Application
        </button>
      </div>

      {/* Statistics card by status */}
      <div className="grid grid-cols-3 gap-3 mb-8 sm:grid-cols-5">
        {JOB_STATUSES.map((status) => (
          <div
            key={status}
            className="bg-white border border-gray-200 rounded-lg p-4 text-center"
          >
            <p className="text-2xl font-light text-gray-900">
              {data?.statusCount[status] ?? 0}
            </p>
            <span
              className={`inline-block mt-2 text-xs font-medium px-2 py-0.5 rounded-full border ${STATUS_COLOR[status]}`}
            >
              {JOB_STATUS_LABEL[status]}
            </span>
          </div>
        ))}
      </div>

      <div className="grid grid-cols-1 gap-6 sm:grid-cols-2">

        {/* Recent applied list */}
        <div className="bg-white border border-gray-200 rounded-lg p-5">
          <div className="flex items-center justify-between mb-4">
            <h2 className="text-sm font-medium text-gray-900">Recent Applications</h2>
            <button
              onClick={() => router.push("/jobs")}
              className="text-xs text-blue-600 hover:underline"
            >
              View all →
            </button>
          </div>

          {data?.recentJobs.length === 0 ? (
            <p className="text-sm text-gray-400 text-center py-6">No applications yet.</p>
          ) : (
            <div className="space-y-3">
              {data?.recentJobs.map((job) => (
                <div
                  key={job.id}
                  onClick={() => router.push(`/jobs/${job.id}`)}
                  className="flex items-center justify-between cursor-pointer hover:bg-gray-50 rounded-lg px-2 py-1.5 transition-colors duration-200"
                >
                  <div className="min-w-0">
                    <p className="text-sm font-medium text-gray-900 truncate">
                      {job.companyName}
                    </p>
                    <p className="text-xs text-gray-400 truncate">{job.position}</p>
                  </div>
                  <span
                    className={`shrink-0 ml-2 text-xs font-medium px-2 py-0.5 rounded-full border ${STATUS_COLOR[job.status]}`}
                  >
                    {JOB_STATUS_LABEL[job.status]}
                  </span>
                </div>
              ))}
            </div>
          )}
        </div>

        {/* Upcoming interviews */}
        <div className="bg-white border border-gray-200 rounded-lg p-5">
          <h2 className="text-sm font-medium text-gray-900 mb-4">Upcoming Interviews</h2>

          {data?.upcomingInterviews.length === 0 ? (
            <p className="text-sm text-gray-400 text-center py-6">No upcoming interviews.</p>
          ) : (
            <div className="space-y-3">
              {data?.upcomingInterviews.map((job) => (
                <div
                  key={job.id}
                  onClick={() => router.push(`/jobs/${job.id}`)}
                  className="flex items-center justify-between cursor-pointer hover:bg-gray-50 rounded-lg px-2 py-1.5 transition-colors duration-200"
                >
                  <div className="min-w-0">
                    <p className="text-sm font-medium text-gray-900 truncate">
                      {job.companyName}
                    </p>
                    <p className="text-xs text-gray-400 truncate">
                      {job.interviewDateTime?.replace("T", " ").slice(0, 16)}
                    </p>
                  </div>
                  <span
                    className={`shrink-0 ml-2 text-xs font-medium px-2 py-0.5 rounded-full border ${STATUS_COLOR[job.status]}`}
                  >
                    {JOB_STATUS_LABEL[job.status]}
                  </span>
                </div>
              ))}
            </div>
          )}
        </div>
      </div>

    </div>
  );
}
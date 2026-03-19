"use client";

import { useEffect, useState } from "react";
import { useParams } from "next/navigation";
import { JobResponse } from "@/types/job";
import { getJobApi } from "@/services/jobService";
import JobForm from "@/components/job/JobForm";

export default function EditJobPage() {
  const { id } = useParams();
  const [job, setJob] = useState<JobResponse | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<String | null>(null);

  useEffect(() => {
    const fetchJob = async () => {
      try {
        const res = await getJobApi(Number(id));
        if (res.data) setJob(res.data);
      } catch (err: any) {
        setError(err?.message || "Failed to load job.");
      } finally {
        setLoading(false);
      }
    };
    fetchJob();
  }, [id]);

  if (loading) {
    return (
      <div className="flex items-center justify-center py-20">
        <p className="text-sm text-gray-400">Loading...</p>
      </div>
    )
  }

  if (error || !job) {
    return (
      <div className="flex items-center justify-center py-20">
        <p className="text-sm text-red-500">{error || "Job not found."}</p>
      </div>
    );
  }

  return <JobForm initialData={job} />;
}
"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import {
  JobCreateRequest,
  JobUpdateRequest,
  JobResponse,
  JobStatus,
  JobPlatform,
  JOB_STATUS_LABEL,
  JOB_PLATFORM_LABEL,
} from "@/types/job";
import { createJobApi, updateJobApi } from "@/services/jobService";

interface JobFormProps {
  initialData?: JobResponse;
}

const JOB_STATUSES: JobStatus[] = [
  "APPLIED",
  "DOCUMENT_PASSED",
  "INTERVIEW",
  "OFFER",
  "REJECTED",
];

const JOB_PLATFORMS: JobPlatform[] = [
  "LINKEDIN",
  "INDEED",
  "DIRECT_APPLY",
  "OTHER",
];

const inputClass =  
  "w-full bg-white border border-gray-200 rounded-lg px-4 py-3 text-sm text-gray-900 placeholder:text-gray-400 focus:outline-none focus:border-blue-500 transition-colors duration-200";

const labelClass = 
  "text-xs font-medium text-gray-500 uppercase tracking-wider";

const JobForm = ({ initialData }: JobFormProps) => {
  const isEdit = !!initialData;
  const router = useRouter();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [showOptional, setShowOptional] = useState(false);

  const [form, setForm] = useState({
    companyName: initialData?.companyName ?? "",
    position: initialData?.position ?? "",
    status: initialData?.status ?? "APPLIED",
    appliedAt: initialData?.appliedAt ?? "",
    jobUrl: initialData?.jobUrl ?? "",
    salary: initialData?.salary?.toString() ?? "",
    location: initialData?.location ?? "",
    platform: initialData?.platform ?? "",
    contactName: initialData?.contactName ?? "",
    contactEmail: initialData?.contactEmail ?? "",
    contactPhone: initialData?.contactPhone ?? "",
    interviewDateTime: initialData?.interviewDateTime ?? "",
    memo: initialData?.memo ?? "",
  });

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>
  ) => {
    const { name, value } = e.target;
    setError(null);
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e: React.SyntheticEvent<HTMLFormElement>) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      if (isEdit && initialData) {
        const request: JobUpdateRequest = {
          position: form.position,
          status: form.status as JobStatus,
          jobUrl: form.jobUrl || undefined,
          salary: form.salary ? Number(form.salary) : undefined,
          location: form.location || undefined,
          platform: (form.platform as JobPlatform) || undefined,
          contactName: form.contactName || undefined,
          contactEmail: form.contactEmail || undefined,
          contactPhone: form.contactPhone || undefined,
          interviewDateTime: form.interviewDateTime || undefined,
          memo: form.memo || undefined,
        };
        await updateJobApi(initialData.id, request);
      } else {
        const request: JobCreateRequest = {
          companyName: form.companyName,
          position: form.position,
          status: form.status as JobStatus,
          appliedAt: form.appliedAt,
          jobUrl: form.jobUrl || undefined,
          salary: form.salary ? Number(form.salary) : undefined,
          location: form.location || undefined,
          platform: (form.platform as JobPlatform) || undefined,
          contactName: form.contactName || undefined,
          contactEmail: form.contactEmail || undefined,
          contactPhone: form.contactPhone || undefined,
          interviewDateTime: form.interviewDateTime || undefined,
          memo: form.memo || undefined,
        };
        await createJobApi(request);
      }
      router.push("/jobs");
    } catch (err: any) {
      setError(err?.message || "Something went wrong.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-xl mx-auto px-4 py-10">
      <div className="mb-8">
        <h1 className="text-2xl font-light text-gray-900 tracking-tight">
          {isEdit ? "Edit Application" : "New Application"}
        </h1>
        <p className="text-sm text-gray-500 mt-1 font-light">
          {isEdit
            ? "Update your job application details."
            : "Track a new job application."}
        </p>
      </div>

      <form onSubmit={handleSubmit} className="space-y-4">
        {/* 필수 필드 */}
        {!isEdit && (
          <div className="space-y-1">
            <label className={labelClass}>Company Name *</label>
            <input
              name="companyName"
              placeholder="e.g. Google"
              value={form.companyName}
              onChange={handleChange}
              required
              className={inputClass}
            />
          </div>
        )}

        <div className="space-y-1">
          <label className={labelClass}>Position *</label>
          <input
            name="position"
            placeholder="e.g. Frontend Engineer"
            value={form.position}
            onChange={handleChange}
            required
            className={inputClass}
          />
        </div>

        <div className="space-y-1">
          <label className={labelClass}>Status *</label>
          <select
            name="status"
            value={form.status}
            onChange={handleChange}
            required
            className={inputClass}
          >
            <option value="">Select status</option>
            {JOB_STATUSES.map((s) => (
              <option key={s} value={s}>
                {JOB_STATUS_LABEL[s]}
              </option>
            
            ))}
          </select>
        </div>

        {!isEdit && (
          <div className="space-y-1">
            <label className={labelClass}>Applied Date *</label>
            <input
              name="appliedAt"
              type="date"
              value={form.appliedAt}
              onChange={handleChange}
              required
              className={inputClass}
            />
          </div>
        )}

        {/* 선택 필드 토글 */}
        <button
          type="button"
          onClick={() => setShowOptional((prev) => !prev)}
          className="flex items-center gap-2 text-sm text-grey-500 hover:text-blue-600 transition-colors duration-200 mt-2"
        >
          <span>{showOptional ? "▲" : "▼"}</span>
          <span>{showOptional ? "Hide optional fields" : "Show optional fields"}</span>
        </button>

        {/* 선택 필드 */}
        {showOptional && (
          <div className="space-y-4 pt-2">
            <div className="space-y-1">
              <label className={labelClass}>Job URL</label>
              <input
                name="jobUrl"
                placeholder="https://..."
                value={form.jobUrl}
                onChange={handleChange}
                className={inputClass}
              />
            </div>

            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-1">
                <label className={labelClass}>Salary</label>
                <input
                  name="salary"
                  type="number"
                  placeholder="e.g. 50000"
                  value={form.salary}
                  onChange={handleChange}
                  className={inputClass}
                />
              </div>
              <div className="space-y-1">
                <label className={labelClass}>Location</label>
                <input 
                  name="location"
                  placeholder="e.g. Eschborn"
                  value={form.location}
                  onChange={handleChange}
                  className={inputClass}
                />
              </div>
            </div>

            <div className="space-y-1">
              <label className={labelClass}>Platform</label>
              <select
                name="platform"
                value={form.platform}
                onChange={handleChange}
                className={inputClass}
              >
                <option value="">Select platform</option>
                {JOB_PLATFORMS.map((p) => (
                  <option key={p} value={p}>
                    {JOB_PLATFORM_LABEL[p]}
                  </option>
                ))}
              </select>
            </div>

            <div className="grid grid-cols-3 gap-4">
              <div className="space-y-1">
                <label className={labelClass}>Contact Name</label>
                <input
                  name="contactName"
                  placeholder="Jane Doe"
                  value={form.contactName}
                  onChange={handleChange}
                  className={inputClass}
                />
              </div>
              <div className="space-y-1">
                <label className={labelClass}>Contact Email</label>
                <input
                  name="contactEmail"
                  type="email"
                  placeholder="jane@company.com"
                  value={form.contactEmail}
                  onChange={handleChange}
                  className={inputClass}
                />
              </div>
              <div className="space-y-1">
                <label className={labelClass}>Contact Phone</label>
                <input
                  name="contactPhone"
                  placeholder="010-0000-0000"
                  value={form.contactPhone}
                  onChange={handleChange}
                  className={inputClass}
                />
              </div>
            </div>

            <div className="space-y-1">
              <label className={labelClass}>Interview Date & Time</label>
              <input
                name="interviewDateTime"
                type="datetime-local"
                value={form.interviewDateTime}
                onChange={handleChange}
                className={inputClass}
              />
            </div>

            <div className="space-y-1">
              <label className={labelClass}>Memo</label>
              <textarea
                name="memo"
                placeholder="Any notes about this application..."
                value={form.memo}
                onChange={handleChange}
                rows={4}
                className={inputClass}
              />
            </div>
          </div>
        )}

        {error && (
          <p className="text-xs text-red-500 bg-red-50 border border-red-200 rounded-lg px-4 py-3">
            {error}
          </p>
        )}

        <div className="flex gap-3 pt-2">
          <button
            type="button"
            onClick={() => router.back()}
            className="flex-1 bg-white border border-gray-200 hover:border-gray-400 text-gray-700 rounded-lg px-4 py-3 text-sm font-medium transition-colors duration-200"
          >
            Cancel
          </button>
          <button
            type="submit"
            disabled={loading}
            className="flex-1 bg-blue-600 hover:bg-blue-700 disabled:opacity-50 disabled:cursor-not-allowed text-white rounded-lg px-4 py-3 text-sm font-medium transition-colors duration-200"
          >
            {loading
              ? isEdit
                ? "Saving..."
                : "Creating..."
              : isEdit
                ? "Save Changes"
                : "Create Application"}
          </button>
        </div>
      </form>
    </div>
  );
};

export default JobForm;
"use client";

import { useState, SyntheticEvent, ChangeEvent } from "react";
import { useRouter } from "next/navigation";
import { useAuth } from "@/context/AuthContext";
import Link from "next/link";

const SignupForm = () => {
  const [form, setForm] = useState({name: "", email: "", password: ""});
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const router = useRouter();
  const { signupAndLogin } = useAuth();

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setError(null);
    setForm(prev => ({ ...prev, [name]: value}));
  };

  const handleSubmit = async (e: SyntheticEvent<HTMLFormElement>) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      await signupAndLogin(form.name, form.email, form.password);
      router.push("/login");
    } catch (err: any) {
      alert(err?.message || "Signup failed. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gray-50 flex items-center justify-center px-4">
      <div className="w-full max-w-sm">
        
        {/* Header */}
        <div className="mb-10">
          <p className="font-mono text-md text-indigo-400 font-semibold tracking widget text-text-secondary uppercase mb-6">
            Apply Flow
          </p>
          <h1 className="text-3xl font-light text-gray-900 tracking-tight">
            Create account
          </h1>
          <p className="text-gray-500 text-sm mt-2 font-light">
            Start tracking your job applications today
          </p>
        </div>

        { /* Form */}
        <form onSubmit={handleSubmit} className="space-y-4">
          <div className="space-y-1">
            <label className="text-xs font-medium text-gray-500 uppercase tracking-wider">
              Name
            </label>
            <input 
              name="name"
              placeholder="Hannah Lim"
              value={form.name}
              type="text"
              onChange={handleChange}
              required
              className="w-full bg-white border border-gray-200 rounded-lg px-4 py-3 text-sm text-gray-900 placeholder:text-gray-500/50 focus:outline-none focus:border-blue-500 transition-colors duration-200"
            />
          </div>

          <div className="space-y-1">
            <label className="text-xs font-medium text-gray-500 uppercase tracking-wider">
              Email
            </label>
            <input 
              name="email"
              placeholder="you@example.com"
              value={form.email}
              type="email"
              onChange={handleChange}
              required
              className="w-full bg-white border border-gray-200 rounded-lg px-4 py-3 text-sm text-gray-900 placeholder:text-gray-500/50 focus:outline-none focus:border-blue-500 transition-colors duration-200"
            />
          </div>

          <div className="space-y-1">
            <label className="text-xs font-medium text-gray-500 uppercase traking-wider">
              Password
            </label>
            <input
              name="password"
              placeholder="••••••••"
              value={form.password}
              type="password"
              onChange={handleChange}
              required
              className="w-full bg-white border border-gray-200 rounded-lg px-4 py-3 text-sm text-gray-900 placeholder:text-gray-500/50 focus:outline-none focus:border-blue-500 transition-colors duration-200"
            />
            <p className="text-xs text-gray-500/70 mt-1">
              Min 8 caracters with uppercase,number, and special character
            </p>
          </div>

          {/* Error message */}
          {error && (
            <p className="text-xs text-red-500 bg-red-50 border border-red-200 rounded-lg px-4 py-3">
              {error}
            </p>
          )}

          <button
            type="submit"
            disabled={loading}
            className="w-full bg-blue-600 hover:bg-blue-700 disabled:opacity-50 disabled:cursor-not-allowed text-white rounded-lg px-4 py-3 text-sm font-medium transition-colors duration-200 mt-2"
          >
            {loading ? "Creating account..." : "Create account"}
          </button>
        </form>

        {/* Footer */}
        <p className="text-center text-sm text-gray-500 mt-8 font-light">
          Already have an account?{" "}
          <Link href="/login" className="text-blue-600 hover:underline font-medium">
            Sign in
          </Link>
        </p>

      </div>
    </div>
  );
}

export default SignupForm;
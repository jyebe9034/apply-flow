# ApplyFlow 📋

> A job application tracking platform to help you manage and organize your job search efficiently.

---

## 🚀 Live Demo

🔗 [https://applyflow.ink](https://applyflow.ink)

---

## 📌 Features

- **Authentication** - Sign up and log in with JWT-based authentication
- **Job Application CRUD** - Create, read, update, and delete job applications
- **Status Tracking** - Track application status (Applied, Document Passed, Interview, Offer, Rejected)
- **Dashboard** - View statistics by status, recent applications, and upcoming interviews
- **Optional Fields** - Add contact info, interview schedule, salary, memo, and more

---

## 🛠 Tech Stack

### Frontend
| Technology | Version |
|---|---|
| Next.js | 16.1.6 |
| React | 19.2.3 |
| TypeScript | ^5 |
| Tailwind CSS | ^4 |

### Backend
| Technology | Version |
|---|---|
| Spring Boot | 4.0.3 |
| Spring Security | 7.0.3 |
| JPA / Hibernate | 7.2.4 |
| QueryDSL | 5.0.0 |
| JWT | 0.11.5 |

### Database & Infrastructure
| Technology | Description |
|---|---|
| PostgreSQL 16 | Primary database |
| Docker / Docker Compose | Containerization |
| AWS EC2 | Cloud hosting |
| Nginx | Reverse proxy |
| Let's Encrypt | SSL certificate |
| GitHub Actions | CI/CD pipeline |

--- 

## 🏗 Architecture
```
Client (Browser)
↓
Nginx (Port 80/443)
↓
┌───┴───┐
↓       ↓
Next.js  Spring Boot
(3000)   (8080)
↓
PostgreSQL
(5432)
```
---

## 📁 Project Structure
```
apply-flow/
├── .github/
│   └── workflows/
│       └── deploy.yml        # GitHub Actions CI/CD
├── backend/
│   └── applyflow/
│       ├── src/
│       │   ├── main/java/com/hannah/applyflow/
│       │   │   ├── user/             # Authentication and User management
│       │   │   ├── job/              # Job application
│       │   │   ├── dashboard/        # Dashboard
│       │   │   └── global/           # Common (Exception, Security)
│       │   └── test/                 # Unit tests
│       ├── docker-compose.yml        # Local development
│       └── Dockerfile
├── frontend/
│   └── applyFlow/
│       ├── app/              # Next.js pages
│       ├── components/       # React components
│       ├── services/         # API calls
│       ├── types/            # TypeScript types
│       └── Dockerfile
├── nginx/
│   └── nginx.conf
├── docker-compose.prod.yml   # Production
└── .env.example
```

---

## ⚙️ Getting Started

### Prerequisites
- Docker & Docker Compose
- Node.js 20+
- Java 21
- PostgreSQL 16

### Local Development

**1. Clone the repository**
```bash
git clone https://github.com/jyebe9034/apply-flow.git
cd apply-flow
```

**2. Set up environment variables**
```bash
cp .env.example .env
# Fill in the values in .env
```

**3. Start the database**
```bash
docker compose up -d
```

**4. Start the backend**
```bash
cd backend/applyflow
./gradlew bootRun
```

**5. Start the frontend**
```bash
cd frontend/applyFlow
npm install
npm run dev
```

**6. Open the browser**
http://localhost:3000

---

### Production Deployment

```bash
docker compose -f docker-compose.prod.yml up -d --build
```

---

## 🔐 Environment Variables

Copy `.env.example` to `.env` and fill in the values.
DB_NAME=                     # PostgreSQL database name
DB_USER=                     # PostgreSQL username
DB_PASSWORD=                 # PostgreSQL password
JWT_SECRET=                  # JWT secret key
API_URL=                     # Backend API URL
SPRING_DATASOURCE_URL=       # Spring datasource URL
SPRING_DATASOURCE_USERNAME=  # Spring datasource username
SPRING_DATASOURCE_PASSWORD=  # Spring datasource password

---

## 🧪 Testing

```bash
cd backend/applyflow
./gradlew test
```

Unit tests are written for:
- `AuthService` - Authentication logic
- `JobService` - Job application CRUD and access control
- `DashboardService` - Dashboard statistics and filtering

---

## 🔄 CI/CD

GitHub Actions automatically deploys to AWS EC2 on every push to the `main` branch.
Push to main
↓
GitHub Actions
↓
SSH into EC2
↓
git pull
↓
Docker rebuild & restart

---

## 👩‍💻 Author

- GitHub: [@jyebe9034](https://github.com/jyebe9034)
  

# 🔐 OTP Snippet Share

A lightweight Spring Boot web application that allows users to paste a text/code snippet, generate a one-time OTP, and share it securely. The receiver can use the OTP to view the snippet — once, before it expires.

> ✅ Built with: Spring Boot + H2 + Thymeleaf  
> ✅ Live app: [https://quickcode.up.railway.app](https://quickcode.up.railway.app)  
> ✅ Instant sharing with OTP and expiration logic

---

## ✨ Features

- ✅ Submit a text/code snippet
- 🔐 Generate a short OTP (6-character)
- 🕐 OTP expires in 10 minutes
- ☝️ OTP is one-time usable
- 🧠 H2 file-based database (not in-memory)
- 📋 Copy OTP & snippet using a custom toast-style button
- 🎨 Colorful and mobile-friendly UI
- 🌐 Deployed on Railway

---

## 🚀 Deployment Instructions

### 📦 Railway (Free Hosting)

1. Push this project to a public GitHub repository
2. Go to https://railway.app → Sign in with GitHub
3. Click "New Project" → "Deploy from GitHub Repo"
4. Select your repo and configure:

| Setting        | Value                          |
|----------------|--------------------------------|
| Build Command  | ./gradlew build                |
| Start Command  | java -jar build/libs/app.jar   |

5. Ensure your H2 database uses file mode (`jdbc:h2:file:./data/snippetdb`)
6. Done! Your app is now live.

✅ Live Demo: [https://quickcode.up.railway.app](https://quickcode.up.railway.app)

---

### 🧪 Local Development

1. Clone the repo
2. Run:

```bash
./gradlew bootRun

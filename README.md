# 🤖 SigmaGPT

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.3-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Groq](https://img.shields.io/badge/Groq-LLaMA_3.3_70B-F55036?style=for-the-badge&logo=meta&logoColor=white)
![H2](https://img.shields.io/badge/H2-In--Memory_DB-0000BB?style=for-the-badge&logo=databricks&logoColor=white)

> A full-stack AI chatbot that delivers blazing-fast responses using **Groq's LLaMA 3.3 70B** model — built with **Spring Boot**, complete with persistent chat history, a REST API, and a sleek dark-themed web interface.

---

## 🎯 What is SigmaGPT?

**SigmaGPT** is a lightweight, production-ready AI chatbot backend that connects to [Groq](https://groq.com/)'s ultra-fast inference API. It provides a simple yet powerful conversational interface where users can ask questions and receive intelligent responses powered by the **LLaMA 3.3 70B Versatile** model.

**Why Groq?** Groq's custom LPU (Language Processing Unit) hardware delivers up to **18x faster** inference than traditional GPU-based solutions, making SigmaGPT responses feel near-instantaneous.

---

## ✨ Features

- 🧠 **AI Chat** — Real-time conversational AI powered by Groq's LLaMA 3.3 70B
- 💾 **Chat History** — Every conversation is automatically saved with timestamps
- 📡 **REST API** — Clean RESTful endpoints for chat and history retrieval
- 🎨 **Web Interface** — Built-in dark-themed chat UI with auto-scrolling and keyboard shortcuts
- ⚡ **Ultra-Fast** — Sub-second response times via Groq's hardware-accelerated inference
- 🔒 **Secure Config** — API keys managed through environment variables, never hardcoded

---

## 🏗️ Architecture

```
                     ┌─────────────────────────┐
                     │     BROWSER CLIENT       │
                     │   index.html (Dark UI)   │
                     └────────────┬────────────┘
                                  │
                     ┌────────────▼────────────┐
                     │  SPRING BOOT  :8081      │
                     │                          │
                     │    ChatController        │
                     │    GET /chat?message=     │
                     │    GET /history           │
                     │                          │
                     │   ┌──────┐  ┌─────────┐  │
                     │   │ Groq │  │ H2 DB   │  │
                     │   │ API  │  │ In-Mem   │  │
                     │   └──────┘  └─────────┘  │
                     └──────────────────────────┘
```

**How it works:**

1. User types a message in the web UI or calls the `/chat` API
2. `ChatController` forwards the message to the **Groq API** (LLaMA 3.3 model)
3. The AI response + user message are saved to the **H2 database** with a timestamp
4. The response is returned to the client in real-time

---

## 📁 Project Structure

```
sigmagpt/
├── pom.xml                               # Maven build config & dependencies
├── .env.example                          # API key template
├── .gitignore                            # Ignored files & directories
├── mvnw / mvnw.cmd                       # Maven wrapper (Linux / Windows)
│
├── src/main/java/SIGMAGPT/
│   ├── SigmagptApplication.java          # Entry point — bootstraps Spring Boot
│   ├── ChatController.java               # REST controller — /chat & /history
│   ├── ChatMessage.java                  # JPA entity — message data model
│   └── ChatRepository.java              # Repository — database operations
│
├── src/main/resources/
│   ├── application.properties            # Server, DB, and API configuration
│   └── static/
│       └── index.html                    # Frontend chat interface
│
└── src/test/                             # Unit & integration tests
```

---

## 🚀 Getting Started

### Prerequisites

| Requirement | Version | Link |
|---|---|---|
| Java JDK | 17+ | [Download](https://adoptium.net/) |
| Groq API Key | — | [Get Free Key](https://console.groq.com/keys) |

> **Note:** Maven is **not** required — the project includes a Maven wrapper (`mvnw`).

---

### Step 1 — Clone the Repository

```bash
git clone https://github.com/AnkuKumar146/sigmagpt.git
cd sigmagpt
```

### Step 2 — Configure Your API Key

```bash
cp .env.example .env
```

Edit the `.env` file and add your Groq API key:

```
GROQ_API_KEY=gsk_your_actual_api_key_here
```

> ⚠️ **Never commit your `.env` file.** It is already added to `.gitignore`.

### Step 3 — Run the Application

**Linux / macOS:**

```bash
export $(cat .env | xargs) && ./mvnw spring-boot:run
```

**Windows (PowerShell):**

```powershell
$env:GROQ_API_KEY="gsk_your_actual_api_key_here"
.\mvnw.cmd spring-boot:run
```

**Windows (CMD):**

```cmd
set GROQ_API_KEY=gsk_your_actual_api_key_here
mvnw.cmd spring-boot:run
```

### Step 4 — Open in Browser

```
http://localhost:8081
```

You'll see the SigmaGPT chat interface — start chatting! 🎉

---

## 📡 API Reference

### `GET` /chat — Send a Message

Send a message to the AI and receive a response.

```
GET http://localhost:8081/chat?message={your_message}
```

| Parameter | Type | Default | Required | Description |
|---|---|---|---|---|
| `message` | string | `Hello` | No | The message to send to the AI |

**Example:**

```bash
curl "http://localhost:8081/chat?message=What%20is%20Java"
```

**Response:**

```
Java is a high-level, object-oriented programming language developed by
Sun Microsystems. It follows the principle of "write once, run anywhere"...
```

---

### `GET` /history — Retrieve Chat History

Fetch all previously saved conversations.

```
GET http://localhost:8081/history
```

**Example:**

```bash
curl http://localhost:8081/history
```

**Response:**

```json
[
  {
    "id": 1,
    "userMessage": "What is Java?",
    "aiResponse": "Java is a high-level, object-oriented programming language...",
    "timestamp": "2026-08-14T20:30:00"
  }
]
```

---

### 🗄️ H2 Database Console

Access the built-in database admin panel at:

```
http://localhost:8081/h2-console
```

| Setting | Value |
|---|---|
| JDBC URL | `jdbc:h2:mem:sigmadb` |
| Username | `sa` |
| Password | *(leave empty)* |

---

## 🛠️ Tech Stack

| Layer | Technology | Purpose |
|---|---|---|
| Language | Java 17 | Core application logic |
| Framework | Spring Boot 3.2.3 | Web server, DI, auto-configuration |
| AI Model | LLaMA 3.3 70B via Groq | Natural language generation |
| Database | H2 (in-memory) | Chat history persistence |
| ORM | Spring Data JPA / Hibernate | Object-relational mapping |
| HTTP Client | RestTemplate | Groq API communication |
| Build Tool | Apache Maven (wrapper) | Dependency management and builds |
| Frontend | HTML + CSS + JavaScript | Chat user interface |

---

## 🗺️ Roadmap

- [ ] 🔄 **Streaming Responses** — Real-time token-by-token output using SSE
- [ ] 🧠 **Conversation Context** — Multi-turn memory for coherent conversations
- [ ] 🐘 **Persistent Database** — Migrate from H2 to PostgreSQL or MySQL
- [ ] 🔐 **Authentication** — User accounts with JWT-based session management
- [ ] 🐳 **Docker Support** — One-command deployment with Docker Compose
- [ ] ⚛️ **Modern Frontend** — React or Next.js UI with markdown rendering
- [ ] 📊 **Usage Analytics** — Track usage metrics and popular queries
- [ ] 🌍 **Multi-Model Support** — Switch between different LLMs (Mixtral, Gemma, etc.)

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

1. **Fork** the repository
2. **Create** your branch — `git checkout -b feature/awesome-feature`
3. **Commit** your changes — `git commit -m "Add awesome feature"`
4. **Push** to the branch — `git push origin feature/awesome-feature`
5. **Open** a Pull Request

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

**Made with ❤️ by [Anku Kumar](https://github.com/AnkuKumar146)**

⭐ **Star this repo if you found it useful!**

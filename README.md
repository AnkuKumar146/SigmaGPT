<![CDATA[<div align="center">

<!-- Hero Banner -->
<img src="https://capsule-render.vercel.app/api?type=waving&color=0:0d1117,50:10a37f,100:1a7f64&height=220&section=header&text=SigmaGPT&fontSize=80&fontColor=ffffff&animation=fadeIn&fontAlignY=35&desc=AI-Powered%20Chatbot%20%7C%20Spring%20Boot%20%2B%20Groq&descSize=18&descAlignY=55&descColor=cccccc" width="100%"/>

<br/>

<p>
  <a href="https://openjdk.org/"><img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17"/></a>
  <a href="https://spring.io/projects/spring-boot"><img src="https://img.shields.io/badge/Spring_Boot-3.2.3-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot"/></a>
  <a href="https://groq.com/"><img src="https://img.shields.io/badge/Groq-LLaMA_3.3_70B-F55036?style=for-the-badge&logo=meta&logoColor=white" alt="Groq"/></a>
  <a href="https://www.h2database.com/"><img src="https://img.shields.io/badge/H2-In--Memory_DB-0000BB?style=for-the-badge&logo=databricks&logoColor=white" alt="H2"/></a>
</p>

<p>
  <a href="https://github.com/AnkuKumar146/sigmagpt/stargazers"><img src="https://img.shields.io/github/stars/AnkuKumar146/sigmagpt?style=social" alt="Stars"/></a>
  <a href="https://github.com/AnkuKumar146/sigmagpt/network/members"><img src="https://img.shields.io/github/forks/AnkuKumar146/sigmagpt?style=social" alt="Forks"/></a>
  <a href="https://github.com/AnkuKumar146/sigmagpt/issues"><img src="https://img.shields.io/github/issues/AnkuKumar146/sigmagpt?style=social" alt="Issues"/></a>
</p>

<br/>

<p><em>A full-stack AI chatbot that delivers blazing-fast responses using <strong>Groq's LLaMA 3.3 70B</strong> model — built with <strong>Spring Boot</strong>, complete with persistent chat history, a REST API, and a sleek dark-themed web interface.</em></p>

<br/>

[🚀 Get Started](#-getting-started) · [📡 API Docs](#-api-reference) · [🏗️ Architecture](#️-architecture) · [🗺️ Roadmap](#️-roadmap) · [🤝 Contribute](#-contributing)

<br/>

</div>

---

## 🎯 What is SigmaGPT?

**SigmaGPT** is a lightweight, production-ready AI chatbot backend that connects to [Groq](https://groq.com/)'s ultra-fast inference API. It provides a simple yet powerful conversational interface where users can ask questions and receive intelligent responses powered by the **LLaMA 3.3 70B Versatile** model.

> 💡 **Why Groq?** Groq's custom LPU™ (Language Processing Unit) hardware delivers up to **18x faster** inference than traditional GPU-based solutions, making SigmaGPT responses feel near-instantaneous.

---

## ✨ Features

<table>
  <tr>
    <td align="center" width="140"><strong>🧠</strong><br/><strong>AI Chat</strong></td>
    <td>Real-time conversational AI powered by Groq's LLaMA 3.3 70B — one of the most capable open-source LLMs available</td>
  </tr>
  <tr>
    <td align="center"><strong>💾</strong><br/><strong>Chat History</strong></td>
    <td>Every conversation is automatically persisted to an H2 in-memory database with timestamps</td>
  </tr>
  <tr>
    <td align="center"><strong>📡</strong><br/><strong>REST API</strong></td>
    <td>Clean, well-defined RESTful endpoints for programmatic chat and history retrieval</td>
  </tr>
  <tr>
    <td align="center"><strong>🎨</strong><br/><strong>Web Interface</strong></td>
    <td>Built-in dark-themed chat UI with auto-scrolling, keyboard shortcuts, and responsive design</td>
  </tr>
  <tr>
    <td align="center"><strong>⚡</strong><br/><strong>Ultra-Fast</strong></td>
    <td>Sub-second response times leveraging Groq's hardware-accelerated LPU inference engine</td>
  </tr>
  <tr>
    <td align="center"><strong>🔒</strong><br/><strong>Secure</strong></td>
    <td>API keys managed through environment variables — never exposed in source code</td>
  </tr>
</table>

---

## 🏗️ Architecture

```
                          ┌─────────────────────────────┐
                          │      🌐  BROWSER CLIENT     │
                          │    index.html (Dark UI)      │
                          └──────────┬──────────────────┘
                                     │
                          ┌──────────▼──────────────────┐
                          │    SPRING BOOT  :8081        │
                          │                              │
                          │  ┌────────────────────────┐  │
                          │  │    ChatController       │  │
                          │  │                         │  │
                          │  │  GET /chat?message=...  │  │
                          │  │  GET /history           │  │
                          │  └───┬────────────────┬────┘  │
                          │     │                │        │
                          │     ▼                ▼        │
                          │ ┌────────┐   ┌─────────────┐  │
                          │ │ Groq   │   │ H2 Database │  │
                          │ │ API    │   │ (In-Memory) │  │
                          │ │ ☁️     │   │ 💾          │  │
                          │ └────────┘   └─────────────┘  │
                          │                              │
                          └──────────────────────────────┘
```

**Request Flow:**
1. User types a message in the web interface or calls the `/chat` API
2. `ChatController` receives the request and forwards it to the **Groq API** with the LLaMA 3.3 model
3. The AI response is saved to the **H2 database** along with the user's message and a timestamp
4. The response is returned to the client in real-time

---

## 📁 Project Structure

```
sigmagpt/
│
├── 📄 pom.xml                                # Maven build config & dependencies
├── 📄 .env.example                           # API key template
├── 📄 .gitignore                             # Ignored files & directories
├── 🔧 mvnw / mvnw.cmd                       # Maven wrapper (Linux/Windows)
│
├── 📂 src/main/
│   ├── 📂 java/SIGMAGPT/
│   │   ├── ☕ SigmagptApplication.java       # 🚀 Entry point — bootstraps Spring Boot
│   │   ├── ☕ ChatController.java            # 🎮 REST controller — /chat & /history
│   │   ├── ☕ ChatMessage.java               # 📦 JPA entity — message data model
│   │   └── ☕ ChatRepository.java            # 🗃️ Repository — database operations
│   │
│   └── 📂 resources/
│       ├── 📄 application.properties         # ⚙️ Server, DB, and API configuration
│       └── 📂 static/
│           └── 📄 index.html                 # 🎨 Frontend chat interface
│
└── 📂 src/test/                              # 🧪 Unit & integration tests
```

---

## 🚀 Getting Started

### Prerequisites

| Requirement | Version | Link |
|---|---|---|
| ☕ Java JDK | 17+ | [Download](https://adoptium.net/) |
| 🔑 Groq API Key | — | [Get Free Key](https://console.groq.com/keys) |

> **Note:** Maven is **not** required — the project includes a Maven wrapper (`mvnw`).

### Step 1 → Clone the Repository

```bash
git clone https://github.com/AnkuKumar146/sigmagpt.git
cd sigmagpt
```

### Step 2 → Configure Your API Key

```bash
cp .env.example .env
```

Edit the `.env` file with your Groq API key:

```env
GROQ_API_KEY=gsk_your_actual_api_key_here
```

> [!CAUTION]
> **Never commit your `.env` file to version control.** It is already added to `.gitignore` for your protection.

### Step 3 → Run the Application

<details>
<summary><strong>🐧 Linux / macOS</strong></summary>

```bash
export $(cat .env | xargs) && ./mvnw spring-boot:run
```
</details>

<details>
<summary><strong>🪟 Windows (PowerShell)</strong></summary>

```powershell
$env:GROQ_API_KEY="gsk_your_actual_api_key_here"
.\mvnw.cmd spring-boot:run
```
</details>

<details>
<summary><strong>🪟 Windows (CMD)</strong></summary>

```cmd
set GROQ_API_KEY=gsk_your_actual_api_key_here
mvnw.cmd spring-boot:run
```
</details>

### Step 4 → Open in Browser

```
🌐 http://localhost:8081
```

You'll see the SigmaGPT chat interface — start chatting! 🎉

---

## 📡 API Reference

### `GET` /chat — Send a Message

Send a message to the AI and receive a response.

```http
GET http://localhost:8081/chat?message={your_message}
```

| Parameter | Type     | Default   | Required | Description                    |
|-----------|----------|-----------|----------|--------------------------------|
| `message` | `string` | `"Hello"` | No       | The message to send to the AI  |

<details>
<summary><strong>📋 Example Request & Response</strong></summary>

**Request:**
```bash
curl "http://localhost:8081/chat?message=Explain%20quantum%20computing%20in%20simple%20terms"
```

**Response:**
```
Quantum computing uses quantum bits (qubits) instead of regular bits. While a 
regular bit can only be 0 or 1, a qubit can be both 0 and 1 at the same time 
(superposition). This allows quantum computers to process many possibilities 
simultaneously, making them incredibly powerful for certain types of problems...
```
</details>

---

### `GET` /history — Retrieve Chat History

Fetch all previously saved chat conversations.

```http
GET http://localhost:8081/history
```

<details>
<summary><strong>📋 Example Request & Response</strong></summary>

**Request:**
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
  },
  {
    "id": 2,
    "userMessage": "Explain quantum computing",
    "aiResponse": "Quantum computing uses quantum bits (qubits)...",
    "timestamp": "2026-08-14T20:31:15"
  }
]
```
</details>

---

### 🗄️ H2 Database Console

Access the built-in database admin panel:

```
🌐 http://localhost:8081/h2-console
```

| Setting       | Value                 |
|---------------|-----------------------|
| JDBC URL      | `jdbc:h2:mem:sigmadb` |
| Username      | `sa`                  |
| Password      | *(leave empty)*       |

---

## 🛠️ Tech Stack

<div align="center">

| Layer | Technology | Purpose |
|:---:|:---|:---|
| **Language** | Java 17 | Core application logic |
| **Framework** | Spring Boot 3.2.3 | Web server, DI, auto-configuration |
| **AI Model** | LLaMA 3.3 70B via Groq | Natural language generation |
| **Database** | H2 (in-memory) | Chat history persistence |
| **ORM** | Spring Data JPA / Hibernate | Object-relational mapping |
| **HTTP Client** | RestTemplate | Groq API communication |
| **Build Tool** | Apache Maven (wrapper) | Dependency management & builds |
| **Frontend** | HTML + CSS + JavaScript | Chat user interface |

</div>

---

## 🗺️ Roadmap

- [ ] 🔄 **Streaming Responses** — Real-time token-by-token output using SSE
- [ ] 🧠 **Conversation Context** — Multi-turn memory for coherent conversations
- [ ] 🐘 **Persistent Database** — Migrate from H2 to PostgreSQL / MySQL
- [ ] 🔐 **Authentication** — User accounts with JWT-based session management
- [ ] 🐳 **Docker Support** — One-command deployment with Docker Compose
- [ ] ⚛️ **Modern Frontend** — React / Next.js UI with markdown rendering
- [ ] 📊 **Usage Analytics** — Track usage metrics and popular queries
- [ ] 🌍 **Multi-Model Support** — Switch between different LLMs (Mixtral, Gemma, etc.)

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

```
1. Fork the repository
2. Create your branch        →  git checkout -b feature/awesome-feature
3. Commit your changes       →  git commit -m "Add awesome feature"
4. Push to the branch        →  git push origin feature/awesome-feature
5. Open a Pull Request       →  🎉
```

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:0d1117,50:10a37f,100:1a7f64&height=120&section=footer" width="100%"/>

**Made with ❤️ by [Anku Kumar](https://github.com/AnkuKumar146)**

⭐ **Star this repo if you found it useful!** ⭐

<br/>

<a href="https://github.com/AnkuKumar146"><img src="https://img.shields.io/badge/GitHub-AnkuKumar146-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"/></a>

</div>
]]>

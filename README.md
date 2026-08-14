<![CDATA[<div align="center">

# 🤖 SigmaGPT

**An AI-powered chatbot built with Spring Boot and the Groq API**

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.3-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Groq](https://img.shields.io/badge/Groq-LLaMA_3.3-F55036?style=for-the-badge&logo=meta&logoColor=white)](https://groq.com/)
[![H2 Database](https://img.shields.io/badge/H2-Database-0000BB?style=for-the-badge&logo=databricks&logoColor=white)](https://www.h2database.com/)
[![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)](LICENSE)

---

*A lightweight, full-stack conversational AI application that leverages Groq's ultra-fast inference engine to deliver real-time responses powered by the LLaMA 3.3 70B model — complete with persistent chat history and a clean web interface.*

</div>

---

## ✨ Features

| Feature | Description |
|---|---|
| 🧠 **AI Chat** | Real-time conversational AI powered by Groq's LLaMA 3.3 70B model |
| 💾 **Chat History** | Persistent storage of all conversations using H2 in-memory database |
| 🌐 **REST API** | Clean RESTful endpoints for chat and history retrieval |
| 🎨 **Web UI** | Built-in dark-themed chat interface served as a static page |
| ⚡ **Fast Inference** | Ultra-low latency responses via Groq's hardware-accelerated API |
| 🔒 **Secure Config** | API keys managed via environment variables, never hardcoded |

---

## 🏗️ Architecture

```
┌──────────────────────────────────────────────────────┐
│                    CLIENT (Browser)                  │
│               index.html — Chat Interface            │
└──────────────┬───────────────────────┬───────────────┘
               │  GET /chat?message=   │  GET /history
               ▼                       ▼
┌──────────────────────────────────────────────────────┐
│               SPRING BOOT APPLICATION                │
│                                                      │
│  ┌──────────────────────────────────────────────┐    │
│  │           ChatController (REST)               │    │
│  │  • /chat  → forwards to Groq API             │    │
│  │  • /history → retrieves saved conversations  │    │
│  └─────────┬──────────────────┬─────────────────┘    │
│            │                  │                       │
│            ▼                  ▼                       │
│  ┌────────────────┐  ┌──────────────────┐            │
│  │  Groq API      │  │  ChatRepository  │            │
│  │  (LLaMA 3.3)   │  │  (JPA / H2 DB)   │            │
│  └────────────────┘  └──────────────────┘            │
└──────────────────────────────────────────────────────┘
```

---

## 📁 Project Structure

```
sigmagpt/
├── .env.example                          # Environment variable template
├── .gitignore                            # Git ignore rules
├── pom.xml                               # Maven project configuration
├── mvnw / mvnw.cmd                       # Maven wrapper scripts
├── src/
│   ├── main/
│   │   ├── java/SIGMAGPT/
│   │   │   ├── SigmagptApplication.java  # Application entry point
│   │   │   ├── ChatController.java       # REST controller (chat + history)
│   │   │   ├── ChatMessage.java          # JPA entity model
│   │   │   └── ChatRepository.java       # Data access layer
│   │   └── resources/
│   │       ├── application.properties    # Server & database configuration
│   │       └── static/
│   │           └── index.html            # Frontend chat interface
│   └── test/                             # Unit tests
└── target/                               # Compiled output (auto-generated)
```

---

## 🚀 Getting Started

### Prerequisites

- **Java 17** or higher — [Download](https://adoptium.net/)
- **Groq API Key** — [Get one free](https://console.groq.com/keys)

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/sigmagpt.git
cd sigmagpt
```

### 2. Configure Environment Variables

Create a `.env` file from the provided template:

```bash
cp .env.example .env
```

Then open `.env` and add your Groq API key:

```env
GROQ_API_KEY=gsk_your_actual_api_key_here
```

> [!IMPORTANT]
> Never commit your `.env` file. It is already included in `.gitignore`.

### 3. Run the Application

**Linux / macOS:**
```bash
export $(cat .env | xargs) && ./mvnw spring-boot:run
```

**Windows (PowerShell):**
```powershell
$env:GROQ_API_KEY="gsk_your_actual_api_key_here"; .\mvnw.cmd spring-boot:run
```

### 4. Open in Browser

Navigate to:

```
http://localhost:8081
```

You should see the SigmaGPT chat interface ready to go! 🎉

---

## 📡 API Reference

### Send a Chat Message

```http
GET /chat?message={your_message}
```

| Parameter | Type     | Default   | Description                    |
|-----------|----------|-----------|--------------------------------|
| `message` | `string` | `"Hello"` | The message to send to the AI  |

**Example:**
```bash
curl "http://localhost:8081/chat?message=What%20is%20Java?"
```

**Response:**
```
Java is a high-level, object-oriented programming language...
```

---

### Retrieve Chat History

```http
GET /history
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

### H2 Database Console

The in-memory database console is accessible at:

```
http://localhost:8081/h2-console
```

| Setting       | Value                 |
|---------------|-----------------------|
| JDBC URL      | `jdbc:h2:mem:sigmadb` |
| Username      | `sa`                  |
| Password      | *(leave blank)*       |

---

## 🛠️ Tech Stack

| Layer        | Technology                                                        |
|--------------|-------------------------------------------------------------------|
| **Language** | Java 17                                                           |
| **Framework**| Spring Boot 3.2.3 (Web, Data JPA)                                |
| **AI Model** | LLaMA 3.3 70B Versatile via [Groq API](https://groq.com/)       |
| **Database** | H2 (in-memory, runtime)                                          |
| **ORM**      | Spring Data JPA / Hibernate                                       |
| **Build**    | Apache Maven (with wrapper)                                       |
| **Frontend** | Vanilla HTML, CSS, JavaScript (served as Spring Boot static page) |

---

## 🗺️ Roadmap

- [ ] Add streaming responses for real-time token output
- [ ] Implement conversation context (multi-turn memory)
- [ ] Switch to a persistent database (PostgreSQL / MySQL)
- [ ] Add user authentication and session management
- [ ] Deploy with Docker containerization
- [ ] Build a modern React/Next.js frontend

---

## 🤝 Contributing

Contributions are welcome! Here's how to get started:

1. **Fork** the repository
2. **Create** a feature branch: `git checkout -b feature/amazing-feature`
3. **Commit** your changes: `git commit -m 'Add amazing feature'`
4. **Push** to the branch: `git push origin feature/amazing-feature`
5. **Open** a Pull Request

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

<div align="center">

**Built with ❤️ by [Roshan](https://github.com/your-username)**

*If you found this project useful, consider giving it a ⭐!*

</div>
]]>

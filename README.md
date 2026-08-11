# 🤖 SigmaGPT - Full-Stack AI Chat Application

SigmaGPT is a full-stack, real-time AI chat application powered by Java Spring Boot, Groq AI API, and a dynamic frontend. It allows users to chat with a high-speed Llama-3 model and automatically persists all chat history in an in-memory database.

---

## 🧰 Tech Stack

| Layer | Technology |
| :--- | :--- |
| **Frontend** | HTML5, CSS3, JavaScript (Fetch API / Async) |
| **Backend Framework** | Java (JDK 17+), Spring Boot 3, REST APIs |
| **Database** | H2 In-Memory Database (Spring Data JPA / Hibernate) |
| **AI Integration** | Groq API (`llama-3.3-70b-versatile`) |
| **Build Tool** | Apache Maven |
| **Architecture** | Client-Server REST Architecture |

---

## 📂 Project Structure

```text
SigmaGPT/
├── src/
│   ├── main/
│   │   ├── java/com/sigmagpt/
│   │   │   ├── SigmagptApplication.java   # Main Spring Boot Entry Point
│   │   │   ├── ChatController.java         # REST APIs (/chat & /history)
│   │   │   ├── ChatMessage.java            # JPA Database Entity
│   │   │   └── ChatRepository.java         # Data Access Object (DAO)
│   │   └── resources/
│   │       ├── static/
│   │       │   └── index.html             # Dynamic Chat Interface
│   │       └── application.properties     # DB & Server Port Configurations
├── pom.xml                                # Maven Dependencies
└── README.md                              # Project Documentation

# Telai

Telai is a learning project at the intersection of **telemetry** and **artificial intelligence**.
The goal is to run AI agents behind a Spring Boot API, observe what they do (traces, latency,
token usage, model/provider metadata), and expose that through a React frontend.

## Stack

| Layer | Technology |
|-------|------------|
| Frontend | React |
| Backend | Spring Boot |
| AI | LangChain4j (Ollama) |
| Observability | OpenTelemetry |
| Database | PostgreSQL + Flyway |
| Infra | Docker Compose |

The API lives in `api/`. It currently exposes a chat endpoint powered by a LangChain4j
`@AiService`, talking to a local Ollama model. Persistence is PostgreSQL (started from
`infra/compose.yaml`), with Flyway for schema migrations and OpenTelemetry for traces.

The React frontend lives in `web/` (Vite + TypeScript). In development, Vite proxies
`/chat` to the API so the browser does not need CORS.

## Layout

```
api/     Spring Boot API (LangChain4j, JPA, Flyway, OpenTelemetry)
infra/   Docker Compose (PostgreSQL)
docs/    Architecture and feature docs
web/     React + Vite frontend
```

See [docs/README.md](docs/README.md) for how documentation is written.

## Running the API

Requires Java 26, Maven, Docker, and a running [Ollama](https://ollama.com) instance
with the chat model pulled (default: `qwen3:8b`).

```bash
cd api
./mvnw spring-boot:run
```

Spring Boot Docker Compose support starts PostgreSQL from `infra/compose.yaml`.
Ollama is expected at `http://localhost:11434` (override with `OLLAMA_BASE_URL`).

## Running the frontend

Requires Node.js 22+ and a running API on port 8080 if you want proxied requests
to succeed.

```bash
cd web
npm install
npm run dev
```

The app is served at `http://localhost:5173`. See [web/README.md](web/README.md)
for env vars and other scripts.

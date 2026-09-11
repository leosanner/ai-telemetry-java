import { apiBaseUrl } from '@/lib/http.ts'

export default function App() {
  const apiTarget =
    apiBaseUrl === '' ? 'same origin (Vite proxy → :8080)' : apiBaseUrl

  return (
    <div className="shell">
      <header className="masthead">
        <span className="trace" aria-hidden="true" />
        <p className="wordmark">Telai</p>
      </header>

      <main>
        <h1>Telemetry for AI agents</h1>
        <p>
          The React app is wired to the Spring Boot API. Chat and traces land
          here next.
        </p>
        <p className="meta">API: {apiTarget}</p>
      </main>
    </div>
  )
}

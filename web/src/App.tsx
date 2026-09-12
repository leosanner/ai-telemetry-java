import { Chat } from '@/chat/Chat.tsx'

export default function App() {
  return (
    <div className="shell">
      <header className="masthead">
        <span className="trace" aria-hidden="true" />
        <p className="wordmark">Telai</p>
      </header>
      <Chat />
    </div>
  )
}

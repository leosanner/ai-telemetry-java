import {
  useEffect,
  useId,
  useRef,
  useState,
  type FormEvent,
  type KeyboardEvent,
} from 'react'
import { ApiError } from '@/lib/http.ts'
import { PROMPT_MAX, sendChat, type ChatReply } from '@/chat/api.ts'

function formatError(cause: unknown): string {
  if (cause instanceof ApiError) {
    if (cause.status === 400) {
      return 'The message could not be sent. Keep it under 200 characters.'
    }
    if (cause.status === 502 || cause.status === 503 || cause.status === 504) {
      return 'Could not reach the API. Is it running on port 8080?'
    }
    if (cause.status >= 500) {
      return 'The agent could not answer. The API returned an error.'
    }
    return `The request failed (${cause.status}).`
  }
  return 'Could not reach the API. Is it running on port 8080?'
}

export function Chat() {
  const promptId = useId()
  const [prompt, setPrompt] = useState('')
  const [pending, setPending] = useState(false)
  const [asked, setAsked] = useState<string | null>(null)
  const [reply, setReply] = useState<ChatReply | null>(null)
  const [error, setError] = useState<string | null>(null)
  const abortRef = useRef<AbortController | null>(null)

  useEffect(() => {
    return () => {
      abortRef.current?.abort()
    }
  }, [])

  const trimmed = prompt.trim()
  const canAsk = trimmed.length > 0 && !pending

  async function ask(event: FormEvent) {
    event.preventDefault()
    if (!canAsk) {
      return
    }

    abortRef.current?.abort()
    const abort = new AbortController()
    abortRef.current = abort

    setPending(true)
    setError(null)
    setAsked(trimmed)

    try {
      const nextReply = await sendChat(trimmed, abort.signal)
      setReply(nextReply)
    } catch (cause) {
      if (abort.signal.aborted) {
        return
      }
      setReply(null)
      setError(formatError(cause))
    } finally {
      if (!abort.signal.aborted) {
        setPending(false)
      }
    }
  }

  function onPromptKeyDown(event: KeyboardEvent<HTMLTextAreaElement>) {
    if (event.key === 'Enter' && (event.metaKey || event.ctrlKey)) {
      event.currentTarget.form?.requestSubmit()
    }
  }

  return (
    <main className="chat">
      <section
        className="chat-readout"
        aria-live="polite"
        aria-busy={pending}
      >
        {pending ? (
          <p className="chat-status">Waiting for the agent.</p>
        ) : error ? (
          <p className="chat-error" role="alert">
            {error}
          </p>
        ) : reply ? (
          <article className="chat-reply">
            {asked ? <p className="chat-asked">{asked}</p> : null}
            <p className="chat-answer">
              {reply.text === ''
                ? 'The agent returned an empty reply.'
                : reply.text}
            </p>
            {reply.tools.length > 0 ? (
              <ul className="chat-tools">
                {reply.tools.map((tool) => (
                  <li key={`${tool.name}:${tool.arguments}`}>
                    Called {tool.name}
                  </li>
                ))}
              </ul>
            ) : null}
          </article>
        ) : (
          <p className="chat-status">Ask a question to see the agent’s reply.</p>
        )}
      </section>

      <form className="chat-composer" onSubmit={ask}>
        <div className="chat-fields">
          <label htmlFor={promptId}>Message</label>
          <textarea
            id={promptId}
            name="prompt"
            rows={3}
            maxLength={PROMPT_MAX}
            value={prompt}
            disabled={pending}
            autoComplete="off"
            placeholder="What do you want to know?"
            onChange={(event) => setPrompt(event.target.value)}
            onKeyDown={onPromptKeyDown}
          />
          <div className="chat-bar">
            <p className="chat-count">
              {prompt.length} / {PROMPT_MAX}
            </p>
            <button type="submit" disabled={!canAsk}>
              {pending ? 'Asking…' : 'Ask'}
            </button>
          </div>
        </div>
      </form>
    </main>
  )
}

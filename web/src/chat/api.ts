import { api } from '@/lib/http.ts'

export const PROMPT_MAX = 200

export type ChatTool = {
  name: string
  arguments: string
  result: string
}

export type ChatReply = {
  text: string
  tools: ChatTool[]
}

type ChatResponseJson = {
  tools?: ChatTool[]
  response?: {
    response?: string
  }
}

function preferredLanguage(): string {
  return navigator.language.toLowerCase().startsWith('pt')
    ? 'portuguese'
    : 'english'
}

export async function sendChat(
  prompt: string,
  signal?: AbortSignal,
): Promise<ChatReply> {
  const body = await api<ChatResponseJson>('/chat', {
    method: 'POST',
    body: JSON.stringify({
      prompt,
      language: preferredLanguage(),
    }),
    signal,
  })

  return {
    text: body.response?.response?.trim() ?? '',
    tools: body.tools ?? [],
  }
}

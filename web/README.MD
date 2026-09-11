# Telai web

React + Vite frontend for Telai. In development it talks to the Spring Boot API
through a Vite proxy, so the browser stays on one origin.

## Scripts

```bash
npm install
npm run dev      # http://localhost:5173
npm run build
npm run preview
npm run lint
```

`npm run dev` proxies `/chat` to `http://localhost:8080` (the default Spring Boot
port). Leave `VITE_API_BASE_URL` empty for that. Set it to an API origin only when
the UI is served separately from the API.

Copy `.env.example` to `.env.local` if you need a local override.

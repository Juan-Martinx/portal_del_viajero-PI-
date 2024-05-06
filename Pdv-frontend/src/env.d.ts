// Define the type of the environment variables.
declare interface Env {
  readonly NODE_ENV: string;
  readonly NG_APP_API: string;
}

declare interface ImportMeta {
  readonly env: Env;
}

// Para acceder a las variables de entorno se usa import.meta.env.NG_APP_API
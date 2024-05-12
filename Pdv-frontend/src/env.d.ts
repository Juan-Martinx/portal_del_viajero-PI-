// Define the type of the environment variables.
declare interface Env {
  readonly NODE_ENV: string;
  readonly NG_APP_API: string;
}

declare interface ImportMeta {
  readonly env: Env;
}

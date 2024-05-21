// Define the type of the environment variables.
declare interface Env {
  readonly NODE_ENV: string;
  readonly NG_APP_API: string;
  readonly NG_APP_OAUTH2_AUTHORIZE_URI: string;
  readonly NG_APP_OAUTH2_REDIRECT_URI: string;
  readonly NG_APP_OAUTH2_CLIENT_ID: string;
  readonly NG_APP_OAUTH2_SCOPE: string;
  readonly NG_APP_OAUTH2_RESPONSE_TYPE: string;
  readonly NG_APP_OAUTH2_RESPONSE_MODE: string;
  readonly NG_APP_OAUTH2_CODE_CHALLENGE_METHOD: string;
  readonly NG_APP_OAUTH2_STATE: string;
  readonly NG_APP_OAUTH2_NONCE: string;
  readonly NG_APP_OAUTH2_TOKEN_URI: string;
  readonly NG_APP_OAUTH2_GRANT_TYPE: string;
  readonly NG_APP_OUATH2_RESOURCE_URL: string;
  readonly NG_APP_OAUTH2_LOGOUT_URL: string;
  readonly NG_APP_OAUTH2_SECRET_PKCE: string;
  readonly NG_APP_OAUTH_API: string;
}

declare interface ImportMeta {
  readonly env: Env;
}
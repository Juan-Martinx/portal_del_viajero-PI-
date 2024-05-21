export const environment = {
    api: import.meta.env.NG_APP_API,
    oauth_api: import.meta.env.NG_APP_OAUTH_API,
    authorizeUri: import.meta.env.NG_APP_OAUTH2_AUTHORIZE_URI,
    client_id: import.meta.env.NG_APP_OAUTH2_CLIENT_ID,
    redirect_uri:import.meta.env.NG_APP_OAUTH2_REDIRECT_URI,
    scope: import.meta.env.NG_APP_OAUTH2_SCOPE,
    grant_type: import.meta.env.NG_APP_OAUTH2_GRANT_TYPE,
    response_type: import.meta.env.NG_APP_OAUTH2_RESPONSE_TYPE,
    response_mode: import.meta.env.NG_APP_OAUTH2_RESPONSE_MODE,
    code_challenge_method: import.meta.env.NG_APP_OAUTH2_CODE_CHALLENGE_METHOD,
    state: import.meta.env.NG_APP_OAUTH2_STATE,
    nonce: import.meta.env.NG_APP_OAUTH2_NONCE,
    token_uri: import.meta.env.NG_APP_OAUTH2_TOKEN_URI,
    resource_url: import.meta.env.NG_APP_OUATH2_RESOURCE_URL,
    logout_url: import.meta.env.NG_APP_OAUTH2_LOGOUT_URL,
    secret_pkce: import.meta.env.NG_APP_OAUTH2_SECRET_PKCE
};
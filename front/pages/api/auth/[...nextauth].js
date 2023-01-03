import NextAuth from "next-auth"
import Keycloak from 'next-auth/providers/keycloak'
import axios from "axios";

const KEYCLOACK_AUTHORIZATION_REFRESH_URL = 'http://localhost:28080/auth/realms/tarask/protocol/openid-connect/token';

async function refreshAccessToken(token) {

  const params = new URLSearchParams();
  params.append('client_id', "front-client");
  params.append( 'client_secret', "2d45b653-7adc-42cc-82c8-e06260eae59c",);
  params.append('refresh_token', token.refreshToken);
  params.append('grant_type', "refresh_token");

  let res = await axios.post(KEYCLOACK_AUTHORIZATION_REFRESH_URL, params);

  const refreshedToken = res;

  if (res.data) {
    token.refreshToken = res.data.refresh_token;
    token.accessToken = res.data.access_token;
    token.expires_at = Date.now() + res.data.expires_in * 1000;
    return token;
  }

  return token;
}

export const authOptions = {
  // Configure one or more authentication providers
  providers: [
    Keycloak({
      clientId: "front-client",
      clientSecret: "2d45b653-7adc-42cc-82c8-e06260eae59c",
      issuer: "http://localhost:28080/auth/realms/tarask",
    }),
  ],
  session: {
    jwt: true,
  },
  callbacks: {
    async session({ session, token, user}) {
      session.accessToken = token.accessToken;
      session.user.id = token.id;
      session.error = token.error;

      return session;
    },
    async jwt({ token, user, account, profile, isNewUser }) {

      if (user) {
        token.id = user.id;
      }

      if (account) {
        token.accessToken = account.access_token;
        token.expires_at = account.expires_at * 1000;
        token.refreshToken = account.refresh_token;
      }

      if (Date.now() < token.expires_at) {
        return token;
      }

      return refreshAccessToken(token);
    },
  },
}

export default NextAuth(authOptions)

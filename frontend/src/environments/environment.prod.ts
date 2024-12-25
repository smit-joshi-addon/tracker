import packageInfo from '../../package.json';

export const environment = {
  apiUrl: 'http://localhost:8080',
  appVersion: packageInfo.version,
  production: true
};

import packageInfo from '../../package.json';

export const environment = {
  apiUrl: '/api',
  appVersion: packageInfo.version,
  production: true
};

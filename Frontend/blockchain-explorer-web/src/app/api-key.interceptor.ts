import { HttpInterceptorFn } from '@angular/common/http';

const API_KEY = 'blockchain-explorer-2025-secure-key';

export const apiKeyInterceptor: HttpInterceptorFn = (req, next) => {
  // Add API key only for backend requests
  if (req.url.startsWith('http://localhost:8080')) {
    req = req.clone({
      setHeaders: {
        'X-API-Key': API_KEY,
      },
    });
  }

  return next(req);
};

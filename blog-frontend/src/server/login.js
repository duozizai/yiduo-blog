import { post } from './api.js';

export const loginData = p => post('/auth/login', p);
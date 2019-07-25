import { get,post } from './api.js';

export const loginData = p => post('/auth/login', p);

export const loginUserData = p => get('/auth/me', p);

export const usersList = p => get('/users/search',p);

export const blogList = p => get('/blogs/search',p);

export const userDetail = (id,p) => get(`/users/${id}`,p);
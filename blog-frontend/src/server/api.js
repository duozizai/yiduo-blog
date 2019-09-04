/**axios封装
 * 请求拦截、相应拦截、错误统一处理
 */
import axios from 'axios';
import router from '@/router';
import store from '@/store'
import { Message } from 'element-ui';

axios.defaults.baseURL = `${process.env.VUE_APP_API_URL}`;
// 请求超时时间
axios.defaults.timeout = 10000;
// post请求头
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
// 请求拦截器
axios.interceptors.request.use(
    config => {
        config.withCredentials = true // 允许携带token ,这个是解决跨域产生的相关问题
        config.timeout = 10000
        let token = localStorage.getItem('token')
        if (token) {
            config.headers = {
                'Authorization': token,
                'Content-Type': 'application/json;charset=UTF-8'
            }
        }
        store.commit('auth/showLoad', true);
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);
// 响应拦截器
axios.interceptors.response.use(
    response => {
        if (response.data.code == 401) {//未登录
            router.push({
                path: '/login',
                query: { backUrl: router.history.current.fullPath }
            });
            store.commit('auth/setLogined', false);
        };
        store.commit('auth/showLoad', false);
        return Promise.resolve(response);
    },
    error => {
        if (error.response.status === 401) { //未登录
            if (error.response.data.message !== '用户名或密码错误') {
                router.push({
                    path: '/login',
                    query: { backUrl: router.history.current.query.backUrl != undefined ? router.history.current.query.backUrl : router.history.current.fullPath }
                });
                store.commit('auth/setLogined', false);
            } else {
                Message({
                    type: 'error',
                    message: `${error.response.data.message}`
                });
            }
        }
        if (error.response.status === 500) {
            Message({
                type: 'error',
                message: `${error.response.data}`
            });
        }
        if (error.response.status === 404) {
            Message({
                type: 'error',
                message: `${error.response.data}`
            });
        }
        Message({
            type: 'error',
            message: `status ${error.response.status} data ${JSON.stringify(error.response.data)}`
        });
        return Promise.reject(error.response);
    }
);
/**
 * get方法，对应get请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function get(url, params) {
    return new Promise((resolve, reject) => {
        axios.get(`${url}`, { params: params })
            .then(res => {
                resolve(res.data);
            })
            .catch(err => {
                // Toast('响应异常');
                reject(err)
            })
    });
}
/**
 * post方法，对应post请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function post(url, params) {
    return new Promise((resolve, reject) => {
        axios.post(`${url}`, JSON.stringify(params))
            .then(res => {
                resolve(res.data);
            })
            .catch(err => {
                // Toast('响应异常');
                reject(err)
            })
    });
}

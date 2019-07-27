import Vue from 'vue'
import routerCenter from 'vue-router'
import Layout from '@/views/layout/Layout'
import Login from '@/views/index/login'
import Signup from '@/views/index/signup'
import Vuex from 'vuex';
Vue.use(routerCenter)
Vue.use(Vuex)
const Router = new routerCenter({
  routes: [
    {
      path: '/',
      name: 'index',
      title:'index',
      component: Layout,
      children: [
        {
          path: '/',
          name: 'Index',
          component: () => import('@/views/index/index')
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },{
      path: '/singup',
      name: 'singup',
      component: Signup
    }, {
      path: '/users',
      name: 'user',
      title:'user',
      component: Layout,
      children: [
        {
          path: '/users',
          name: 'User',
          component: () => import('@/views/user/index')
        },{
          path: '/users/:id',
          name: 'User',
          component: () => import('@/views/user/detail')
        }
      ]
    }, {
      path: '/blogs',
      name: 'blog',
      title:'blog',
      component: Layout,
      children: [
        {
          path: '/blogs',
          name: 'Blog',
          component: () => import('@/views/blog/index')
        },
        {
          path: '/blogs/add',
          name: 'Blog',
          component: () => import('@/views/blog/add')
        }
      ]
    },
  ]
});
// 判断是否需要登录权限 以及是否登录
Router.beforeEach((to, from, next) => {
  // store.commit('auth/setPrevUrl',from.fullPath);
  const token = localStorage.getItem('token');
	if(to.path !== '/login'){
      //获取当前用户会话
      if(token === undefined){
        this.$route.push('/')
      }else{
        next(true)
      }
  }else{
    next(true)
  }
});

export default Router

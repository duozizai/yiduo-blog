import Vue from 'vue'
import routerCenter from 'vue-router'
import Layout from '@/views/layout/Layout'
import Login from '@/views/index/login'
Vue.use(routerCenter)

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
    }
  ]
});
// 判断是否需要登录权限 以及是否登录
// Router.beforeEach((to, from, next) => {
//   store.commit('auth/setPrevUrl',from.fullPath);
// 	if (to.matched.some(res => res.meta.requireAuth)) {
// 		if (VueCookies.isKey("user_session")) {
// 			next()
// 		} else {
//       Toast('请先登陆...');
// 			next({
// 				path: '/login',
// 				query: {backUrl: to.fullPath}
// 			})
// 		}
// 	} else {
// 		next()
// 	}
// });

export default Router
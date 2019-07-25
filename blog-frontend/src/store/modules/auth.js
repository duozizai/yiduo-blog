import getters from './auth/getters';
import actions from './auth/actions';
import mutations from './auth/mutations';


const state = {
  token: '',
  showLoading:false,
  prevUrl:'/',
  isLogin: false
}

 



export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
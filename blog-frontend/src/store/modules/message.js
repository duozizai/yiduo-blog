const state = {
    title: '',
    status:false,
    type: 'success'
  }
  
const mutations = {
    setMessage (message,data) {
        message.status = data.status,
        message.title = data.title
        message.type = data.type
    }
}
export default {
namespaced: true,
state,
mutations
}
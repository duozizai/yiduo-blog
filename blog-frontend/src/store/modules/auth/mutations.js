// mutations
export default {
    setLogined (state,token) {
        state.token = token
    },
    showLoad (state, status) {
        state.showLoading = status
    },
    setPrevUrl (state, url) {
        state.prevUrl = url
    }
}
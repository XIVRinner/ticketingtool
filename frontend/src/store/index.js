import Vue from 'vue'
import Vuex from 'vuex'
import { isNullOrUndefined } from 'util'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    showMenu : false,
    token : ""
  },
  mutations: {
    mutateToken(state, token){
      state.token = 'Bearer ' + token
    }
  },
  actions: {
    storeToken({commit}, token) {
      if(isNullOrUndefined(token)){
        console.error("INVALID_TOKEN");
      }
      commit('mutateToken', token);
      localStorage.setItem('token', token)
    }
  },
  modules: {
  }
})

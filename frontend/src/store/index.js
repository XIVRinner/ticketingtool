import Vue from 'vue'
import Vuex from 'vuex'
import { isNullOrUndefined } from 'util'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    showMenu : false,
    token : "",
    user : "",
    userData : { }
  },
  mutations: {
    mutateToken(state, token){
      state.token = 'Bearer ' + token
    },
    mutateUser(state, user) {
      state.user = user;
    },
    mutateGroups(state, groups){
      state.userData.groups = groups;
    },
    mutateId(state, id){
      state.userData.id = id;
    }
  },
  actions: {
    storeToken({commit}, token) {
      if(isNullOrUndefined(token)){
        console.error("INVALID_TOKEN");
      }
      commit('mutateToken', token);
      localStorage.setItem('token', token)
    },
    storeUser({commit}, user){
      if(isNullOrUndefined(user)){
        console.error("INVALID_USER")
      }
      commit('mutateUser', user);
      localStorage.setItem('user', user);
    },
    storeGroups({commit}, groups){
      if(isNullOrUndefined(groups)){
        console.error("INVALID_GROUPS")
      }
      commit('mutateGroups', groups);
      localStorage.setItem('userData.groups', groups);
    },
    storeId({commit}, id){
      if(isNullOrUndefined(id)){
        console.error("INVALID_ID")
      }
      commit('mutateId', id);
      localStorage.setItem('userData.id', id);
    }

  },
  modules: {
  }
})

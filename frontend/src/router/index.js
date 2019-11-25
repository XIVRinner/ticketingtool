import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'
import Tickets from '../views/Tickets'
import Changes from '../views/Changes'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path: '/home',
    name: 'home',
    component: Home
  },
  {
    path : '/tickets',
    name : 'tickets',
    component : Tickets
  },
  {
    path : '/changes',
    name : 'changes',
    component : Changes
  },

]

const router = new VueRouter({
  routes
})

export default router

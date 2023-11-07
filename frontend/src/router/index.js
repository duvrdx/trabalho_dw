// Composables
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    component: () => import('@/layouts/default/Default.vue'),
    children: [
      {
        path: '',
        name: 'Home',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "home" */ '@/views/Home.vue'),
      },
      {
        path: '/epic',
        name: 'epic',
        component: () => import('@/views/Epic.vue'),
      },
      {
        path: '/project',
        name: 'project',
        component: () => import('@/views/Project.vue'),
      },
      {
        path: '/userstory',
        name: 'userstory',
        component: () => import('@/views/UserStory.vue'),
      },
    ],
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router

// Composables
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    component: () => import('@/layouts/default/Default.vue'),
    children: [
      {
        path: '/:pathMatch(.*)*',
        redirect: { name: 'project' },
      },
      {
        path: '/project',
        name: 'project',
        component: () => import('@/views/Project.vue'),
      },
      {
        path: '/epic',
        name: 'epic',
        component: () => import('@/views/Epic.vue'),
      },
      {
        path: '/userstory',
        name: 'userstory',
        component: () => import('@/views/UserStory.vue'),
      },
      {
        path: '/task',
        name: 'task',
        component: () => import('@/views/Task.vue'),
      },
    ],
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router

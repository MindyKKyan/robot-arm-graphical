import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import HomeView from "../views/HomeView.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    redirect: "/init",
  },
  {
    path: "/position",
    name: "position",
    component: () => import("../views/pointTable.vue"),
    meta: {
      title: "position",
      keepAlive: false,
    },
  },

  //这里是路由设置
  {
    path: "/blockly",
    name: "blockly",
    component: () => import("@/views/blockly/blocklyView.vue"),
    meta: {
      title: "blockly",
      keepAlive: false,
    },
  },

  {
    path: "/init",
    name: "initHome",
    component: () => import("../views/InitHome.vue"),
    meta: {
      title: "initHome",
      keepAlive: false,
    },
  },

  {
    path: "/help",
    name: "help",
    component: () => import("../views/HelpPage.vue"),
    meta: {
      title: "help",
      keepAlive: false,
    },
  },
  {
    path: "/hello",
    name: "hello",
    component: () => import("../components/HelloWorld.vue"),
    meta: {
      title: "hello",
      keepAlive: false,
    },
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  linkActiveClass: "active",
});

export default router;

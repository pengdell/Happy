import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/MemberView.vue"),
    children: [
      {
        path: "singin",
        name: "signIn",
        component: () => import("@/components/user/MemberLogin.vue"),
      },
      {
        path: "singup",
        name: "signUp",
        component: () => import("@/components/user/MemberRegister.vue"),
      },
      {
        path: "userDetail",
        name: "userDetail",
        component: () => import("@/components/user/MemberDetail.vue"),
      },
      {
        path: "userModify",
        name: "userModify",
        component: () => import("@/components/user/MemberModify.vue"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/BoardView.vue"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardList",
        component: () => import("@/components/board/BoardList.vue"),
      },
      {
        path: "write",
        name: "boardRegister",
        component: () => import("@/components/board/BoardRegister.vue"),
      },
      {
        path: "detail/:no",
        name: "boardDetail",
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      {
        path: "modify/:no",
        name: "boardModify",
        component: () => import("@/components/board/BoardModify.vue"),
      },
      {
        path: "delete/:no",
        name: "boardDelete",
        component: () => import("@/components/board/BoardDelete.vue"),
      },
    ],
  },
  {
    path: "/house",
    name: "house",
    component: () => import("@/views/HouseView.vue"),
  },
  {
    path: "/house/:gugunCode/:dongName",
    name: "houseinterest",
    component: () => import("@/views/HouseView.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

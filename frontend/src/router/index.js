import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";


Vue.use(VueRouter);

const routes = [
  {
    path: "/home",
    name: "home",
    component: Home
  },
  // {
  //   path: "/",
  //   name: "publicContent",
  //   component: () => import("../views/Public.vue")
  // },
  {
    path: "/signin",
    name: "sigin",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/Signin.vue")
  },
  {
    path: "/profile",
    name: "profile",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/Profile.vue")
  },
  {
    path: "/admin/signin",
    name: "AdminSignin",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/AdminLogin.vue")
  },
  {
    path: "/vote",
    name: "Vote",
    component: () =>
      import("../views/Vote.vue")
  },
  {
    path: "/candidateDetail",
    name: "CandidateDetail",
    component: () =>
      import("../views/CandidateDetail.vue")
  },
  {
    path: "/candidateprofile",
    name: "CandidateProfile",
    component: () =>
      import("../views/CandidateProfile.vue")
  },
  {
    path: "/voteResult",
    name: "VoteResult",
    component: () =>
      import("../views/VoteResult.vue")
  },
  {
    path: "/hashchecker",
    name: "Hashchecker",
    component: () =>
      import("../views/Hashchecker.vue")
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});
router.beforeEach((to, from, next) => {
  const publicPages = ["/signin", "/home", "/register", "/admin/signin", "/voteResult", "/candidateprofile", "/hashchecker"
  ];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem("user");

  // try to access a restricted page + not logged in
  if (authRequired && !loggedIn) {
    return next("/signin");
  }

  next();
});
export default router;

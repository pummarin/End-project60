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
    path: "/candidatelist",
    name: "candidatelist",
    component: () =>
    import("../views/CandidateList.vue")
  },
  
  // {
  //   path: "/test",
  //   name: "candidatelist",
  //   component: () =>
  //   import("../views/test.vue")
  // },
  

  {
    path: "/settime",
    name: "editCandidateProfile",
    component: () =>
    import("../views/SetTime.vue")
  },

  {
    path: "/hashchecker",
    name: "Hashchecker",
    component: () =>
      import("../views/Hashchecker.vue")
  },

  {
    path: "/editdate",
    name: "EditTime",
    component: () =>
      import("../views/EditTime.vue")
  },

  {
    path: "/adminhomepage",
    name: "AdminHome",
    component: () =>
      import("../views/AdminHome.vue")
  }
]

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});
router.beforeEach((to, from, next) => {
  const studentPage = ["/candidateDetail", "/vote"]
  const adminPages = ["/candidateprofile", "/hashchecker", "/adminhomepage", "/settime", "/candidatelist"];
  const publicPages = ["/signin", "/home", "/register", "/admin/signin", "/voteResult"];
  const loggedIn = JSON.parse(localStorage.getItem("user"));
  console.log(loggedIn);

  if (publicPages.includes(to.path)) {
    console.log();
  } else if (studentPage.includes(to.path)) {
    if (loggedIn) {
      console.log();
    } else {
      return next("/signin");
    }
  } else if (adminPages.includes(to.path)) {
    if (loggedIn) {
      if (loggedIn.username) {
        console.log();
      } else {
        return next("/admin/signin");
      }
    }else{
      return next("/admin/signin");
    }
  } else {
    return next("/home");
  }

  next();
});
export default router;

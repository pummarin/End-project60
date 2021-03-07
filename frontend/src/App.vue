<template>
  <v-app id="inspire">
    <v-app-bar
      :clipped-left="$vuetify.breakpoint.lgAndUp"
      app
      :color="appBarColor()"
      :dark="user ? true : false"
    >
      <v-btn icon large>
        <v-avatar size="50px" item>
          <v-img src="Sut_logo_Thai.png" alt="Vuetify" />
        </v-avatar>
      </v-btn>
      <v-toolbar-title style="width: 700px" class="ml-0 pl-4">
        <font color="#F16529">
          <h1 class="hidden-sm-and-down">SUT Student Council Election</h1>
        </font>
      </v-toolbar-title>

      <v-spacer />

      <template v-if="isSignin">
         <font color="#F16529">
        <span >Sign in as</span>
         </font>
        <template v-if="user.studentId">
          <v-btn color="#F16529" text @click="handleProfile">{{ user.fullName }}</v-btn>
        </template>
        <template v-else>
          <v-btn color="#F16529" text @click="handleProfile">{{ user.name }}</v-btn>
        </template>
        

        <v-menu left bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
              <v-icon color="#F16529">mdi-dots-vertical</v-icon>
            </v-btn>
          </template>

          <v-list>
            <v-list-item @click="handleSignout">
              <v-list-item-title>
                <v-icon>mdi-logout</v-icon>Sign out
              </v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </template>
      <template v-else>
        <v-btn color="#F16529" dark text to="/signin">Sign in</v-btn>
      </template>
    </v-app-bar>
    <v-content>
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <router-view></router-view>
        </v-row>
      </v-container>
    </v-content>
    <v-footer :color="footerColor()" :dark="isDark()" app>
      <div>
        &copy;
         <font color="#F16529">
        <strong>Project-60</strong>
         </font> 
      </div>
      <v-divider vertical style="padding-right: 5px"></v-divider>
      <div>
        <v-btn color="#F16529"
          href="https://github.com/pummarin/End-project60"
          icon
          small
          target="_blank"
        >
          <v-icon color="#F16529">mdi-github</v-icon>
        </v-btn>
      </div>
      <div>
        <v-btn
          href="https://www.facebook.com/Sut-Se62-Team01-118308956295583"
          icon
          small
          target="_blank"
        >
        </v-btn>
      </div>
      <v-spacer></v-spacer>
      <div>
        <font color="#F16529">
        {{ new Date().getFullYear() }} —
        <strong>SUT Student Council Election</strong>
        </font>
      </div>
    </v-footer>
  </v-app>
</template>
<script>
export default {
  name: "App",
  components: {},
  data() {
    return {
      themes: {
        student: {
          appBar: "blue darken-3",
          fontColor: "light",
        },
        staff: {
          appBar: "green darken-3",
          fontColor: "#E44D26",
        },
        public: {
          appBar: "#E44D26",
          fontColor: "dark",
        },
      },
      isSignin: undefined,
      userRole: null,
      user: {},
      dialog: false,
      drawer: null,
    };
  },
  mounted() {
    this.user = JSON.parse(localStorage.getItem("user"));
    if (this.user) {
      this.isSignin = true;
      if (this.user.studentId) {
        this.userRole = "student";
      } else {
        this.userRole = "staff";
      }
    } else {
      this.isSignin = false;
    }
  },
  methods: {
    whichColor() {
      if (!this.userRole) {
        return "white";
      } else {
        return "dark";
      }
    },
    isDark() {
      if (this.isSignin) return true;
      else return false;
    },
    appBarColor() {
      if (this.userRole == "staff") {
        return "#EBEBEB";
      } else if (this.userRole == "student") {
        return "blue darken-3";
      } else {
        return "#EBEBEB";
      }
    },
    footerColor() {
      if (this.userRole == "staff") {
        return "#EBEBEB";
      } else if (this.userRole == "student") {
        return "primary";
      } else {
        return "#EBEBEB";
      }
    },
    handleProfile() {
      this.$router.push("/profile");
    },
    handleSignout() {
      this.isSignin = false;
      localStorage.removeItem("user");
      this.$router.push("/home");
      location.reload();
    },
    handleIndex() {
      this.$router.push("/");
    },
  },
};
</script>
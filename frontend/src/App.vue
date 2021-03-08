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
          <h1 class="hidden-sm-and-down" style="font-family: 'SUT Regular'">SUT Student Council Election</h1>
        </font>
      </v-toolbar-title>

      <v-spacer />

      <template v-if="isSignin">
         <font color="#F16529" >
        <span style="font-family: 'SUT Regular'">
          <h3>Sign in as</h3></span>
         </font>
        <template v-if="user.studentId">
          
          <v-btn style="font-family: 'SUT Regular'" color="#F16529" text @click="handleProfile"><h2>{{ user.fullName }}</h2></v-btn>
        </template>
        
        <template v-else>
          <v-btn style="font-family: 'SUT Regular'" color="#F16529" text @click="handleProfile"><h2>{{ user.name }}</h2></v-btn>
        
        </template>
        

        <v-menu left bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
              <v-icon color="#F16529">mdi-dots-vertical</v-icon>
            </v-btn>
          </template>

          <v-list color = "#EBEBEB">
            <v-list-item @click="handleSignout" style="width: 130px">
              <v-icon color="#F16529">mdi-logout</v-icon>
              <v-list-item-title style="font-family: 'SUT Regular'">
                 <font color="#F16529">
                <h3 >Sign out</h3>
                 </font>
              </v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </template>
      <template v-else>
        <v-btn color="#F16529" dark text to="/signin" style="font-family: 'SUT Regular'">
        <h3>
        Sign in
        </h3>
        </v-btn>
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
        <strong style="font-family: 'SUT Regular'">Project-60</strong>
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
        <font color="#F16529" style="font-family: 'SUT Regular'">
        {{ new Date().getFullYear() }} —
        <strong style="font-family: 'SUT Regular'">SUT Student Council Election</strong>
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
          fontColor: "#E44D26",
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
        return "#EBEBEB";
      } else {
        return "#EBEBEB";
      }
    },
    footerColor() {
      if (this.userRole == "staff") {
        return "#EBEBEB";
      } else if (this.userRole == "student") {
        return "#EBEBEB";
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
<style>
.v-btn {
  font-size: 2.0em;
}

</style>
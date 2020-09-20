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
      <v-toolbar-title style="width: 700px" class="ml-0 pl-4" >
         <font color="#FB8C00"> 
        <h1 class="hidden-sm-and-down" >SUT Student Council Election</h1> </font>
      </v-toolbar-title>
      <v-spacer />

      <!-- <div>
      <v-text-field
        flat
        solo-inverted
        hide-details
        prepend-inner-icon="mdi-magnify"
        label="Search"
        class="hidden-sm-and-down"
      />
      <v-spacer />
      </div>-->
     
      <template v-if="isSignin">
        <span>Sign in as</span>
        <template v-if="user.studentId">
          <v-btn text @click="handleProfile">{{ user.fullName }}</v-btn>
        </template>
        <template v-else>
          <v-btn text @click="handleProfile">{{ user.name }}</v-btn>
        </template>

        <v-menu left bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
              <v-icon>mdi-dots-vertical</v-icon>
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
        <v-btn color="#FB8C00" dark flat text to="/signin">Sign in</v-btn>
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
        <strong>Project-60</strong>
      </div>
      <v-divider vertical style="padding-right: 5px"></v-divider>
      <div>
        <v-btn href="https://github.com/pummarin/End-project60" icon small target="_blank">
          <v-icon>mdi-github</v-icon>
        </v-btn>
      </div>
      <!-- <div>
        <v-btn
          href="https://www.facebook.com/Sut-Se62-Team01-118308956295583"
          icon
          small
          target="_blank"
        >
          <v-icon>mdi-facebook</v-icon>
        </v-btn>
      </div>-->

      <v-spacer></v-spacer>
      <div>
        {{ new Date().getFullYear() }} —
        <strong>SUT Student Council Election</strong>
      </div>
    </v-footer>
  </v-app>
</template>
<script>
import api from "../src/Api";
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
          appBar: "amber darken-3",
          fontColor: "light",
        },
        public: {
          appBar: "white",
          fontColor: "dark",
        },
      },
      isSignin: undefined,
      userRole: null,
      user: {},
      dialog: false,
      drawer: null,
      staffItems: [
        {
          icon: "mdi-home",
          role: "all",
          text: "หน้าหลัก",
          click: () => {
            this.$router.push("/home");
          },
        },
        {
          icon: "mdi-email",
          role: "staff",
          text: "แสดงข้อมูลการแจ้งซ่อม",
          click: () => {
            this.$router.push("/staff/RepairDetail");
          },
        },
        {
          icon: "mdi-car-info",
          role: "staff",
          text: "ค้นหาข้อมูลยานพาหนะ",
          click: () => {
            this.$router.push("/staff/searchEnrolledVehicle");
          },
        },
      ],
      items: [
        {
          icon: "mdi-home",
          role: "all",
          text: "หน้าหลัก",
          click: () => {
            this.$router.push("/home");
          },
        },
        {
          icon: "mdi-contacts",
          role: "student",
          text: "จองห้องพัก",
          click: () => {
            this.$router.push("/roombooking");
          },
        },
        {
          icon: "mdi-bicycle-basket",
          role: "student",
          text: "ยืมจักรยาน",
          click: () => {
            this.$router.push("/borrowedBike");
          },
        },
        {
          icon: "mdi-wrench",
          role: "student",
          text: "แจ้งซ่อม",
          click: () => {
            this.$router.push("/repair");
          },
        },
        {
          icon: "mdi-help-circle",
          role: "student",
          text: "สถานะห้องพัก",
          click: () => {
            this.$router.push("/roomstatus");
          },
        },
      ],
    };
  },
  mounted() {
    this.user = JSON.parse(localStorage.getItem("user"));
    if (this.user) {
      this.isSignin = true;
      if (this.user.studentId) {
        this.userRole = "student";
        this.checkStudentAlreadyBookingRoom();
      } else {
        this.userRole = "staff";
      }
    } else {
      this.isSignin = false;
    }
  },
  methods: {
    checkStudentAlreadyBookingRoom() {
      let user = JSON.parse(localStorage.getItem("user"));
      let body = {
        student_id: user.id,
      };
      api
        .post("/api/roombooking/student", JSON.stringify(body))
        .then((res) => {
          if (res.data.length > 0) {
            this.items[1].click = () => {
              alert("นักศึกษาจองห้องไปแล้ว");
            };
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
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
        return "amber darken-3";
      } else if (this.userRole == "student") {
        return "blue darken-3";
      } else {
        return "white";
      }
    },
    footerColor() {
      if (this.userRole == "staff") {
        return "amber darken-4";
      } else if (this.userRole == "student") {
        return "primary";
      } else {
        return "white";
      }
    },
    handleProfile() {
      this.$router.push("/profile");
    },
    handleSignout() {
      this.isSignin = false;
      localStorage.removeItem("user");
      this.$router.go("/");
    },
    handleIndex() {
      this.$router.push("/");
    },
  },
};
</script>

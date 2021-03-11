<template>
  <v-app id="inspire">
    <v-app-bar
      :clipped-left="$vuetify.breakpoint.lgAndUp"
      app
      :color="appBarColor()"
      :dark="user ? true : false"
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer" color ="#E44D26"></v-app-bar-nav-icon>
  
      <v-btn icon large>
        <v-avatar size="50px" item>
          <v-img src="Sut_logo_Thai.png"/>
        </v-avatar>
      </v-btn>
      <v-toolbar-title style="width: 700px" class="ml-0 pl-4">
        <font color="#F16529">
          <h1 class="hidden-sm-and-down" style="font-family: 'SUT Regular'">
            SUT Student Council Election
          </h1>
        </font>
      </v-toolbar-title>

      <v-spacer />

      <template v-if="isSignin">
        <font color="#F16529">
          <span style="font-family: 'SUT Regular'"> <h3>Sign in as</h3></span>
        </font>
        <template v-if="user.studentId">
          <v-btn
            style="font-family: 'SUT Regular'"
            color="#F16529"
            text
            @click="handleProfile"
            ><h2>{{ user.fullName }}</h2></v-btn
          >
        </template>

        <template v-else>
          <v-btn
            style="font-family: 'SUT Regular'"
            color="#F16529"
            text
            @click="handleProfile"
            ><h2>{{ user.name }}</h2></v-btn
          >
        </template>

        <v-menu left bottom>
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on">
              <v-icon color="#F16529">mdi-dots-vertical</v-icon>
            </v-btn>
          </template>

          <v-list color="#EBEBEB">
            <v-list-item @click="handleSignout" style="width: 130px">
              <v-icon color="#F16529">mdi-logout</v-icon>
              <v-list-item-title style="font-family: 'SUT Regular'">
                <font color="#F16529">
                  <h3>Sign out</h3>
                </font>
              </v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </template>
      <template v-else>
        <v-btn
          color="#F16529"
          dark
          text
          to="/signin"
          style="font-family: 'SUT Regular'"
        >
          <h3>Sign in</h3>
        </v-btn>
      </template>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer" :clipped="$vuetify.breakpoint.lgAndUp" app>
      <template v-if="isSignin">
        <template v-if="user.studentId">
          <v-list dense>
            <template v-for="item in studentItems">
              <v-list-item :key="item.text" link @click="item.click" :disabled="item.disabled">
                <v-list-item-action>
                  <v-icon>{{ item.icon }}</v-icon>
                </v-list-item-action>
                <v-list-item-content>
                  <v-list-item-title>{{ item.text }}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </template>
          </v-list>
        </template>
        <template v-else>
          <v-list dense>
            <template v-for="item in adminItems">
              <v-row v-if="item.heading" :key="item.heading" align="center">
                <v-col cols="6">
                  <v-subheader v-if="item.heading">{{ item.heading }}</v-subheader>
                </v-col>
                <v-col cols="6" class="text-center">
                  <a href="#!" class="body-2 black--text">EDIT</a>
                </v-col>
              </v-row>
              <v-list-group
                v-else-if="item.children"
                :key="item.text"
                v-model="item.model"
                :prepend-icon="item.model ? item.icon : item['icon-alt']"
                append-icon
              >
                <template v-slot:activator>
                  <v-list-item>
                    <v-list-item-content>
                      <v-list-item-title>{{ item.text }}</v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </template>
                <v-list-item v-for="(child, i) in item.children" :key="i" link>
                  <v-list-item-action v-if="child.icon">
                    <v-icon>{{ child.icon }}</v-icon>
                  </v-list-item-action>
                  <v-list-item-content>
                    <v-list-item-title>{{ child.text }}</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list-group>
              <v-list-item v-else :key="item.text" link @click="item.click">
                <v-list-item-action>
                  <v-icon>{{ item.icon }}</v-icon>
                </v-list-item-action>
                <v-list-item-content>
                  <v-list-item-title>{{ item.text }}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </template>
          </v-list>
        </template>
      </template>
      <template v-else>
        <v-list dense>
          <v-list-item @click="handleIndex">
            <v-list-item-action>
              <v-icon>mdi-home</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <v-list-item-title>หน้าแรก</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-list-item @click="handleVoteResult">
            <v-list-item-action>
              <v-icon>mdi-bullhorn</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <v-list-item-title>ผลการลงคะแนน</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </template>
    </v-navigation-drawer>

    <v-dialog v-model="dialog2" width="500">

                <v-card>
                  <v-card-title class="headline grey lighten-2">
                    <font color="#F16529">
                    <h3 style="font-family: 'SUT Regular'">
                    ตรวจสอบผลการลงคะแนน
                  </h3></font>
                  </v-card-title>

                  <p><v-card-text>
                    <v-text-field
                      label="กรอกรหัส"
                      name="hashvalue"
                      v-model="userhash"
                      style="font-family: 'SUT Regular'"
                    ></v-text-field>
                  </v-card-text></p>

                  <v-divider></v-divider>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      style="font-family: 'SUT Regular'"
                      class="ma-2"
                      outlined
                      rounded
                      color="#EBEBEB"
                      dark
                      @click="checkStudentHash(userhash)">
                      <font color="#F16529">
                    <h3>ตรวจสอบ</h3></font></v-btn>
                    <v-btn color="#EBEBEB"  @click="dialog2 = false" outlined
                      rounded style="font-family: 'SUT Regular'">
                        <font color="#F16529"><h3>
                      ปิด
                    </h3></font></v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>

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
        <v-btn
          color="#F16529"
          href="https://github.com/pummarin/End-project60"
          icon
          small
          target="_blank"
        >
          <v-icon color="#F16529">mdi-github</v-icon>
        </v-btn>
      </div>
      
      <v-spacer></v-spacer>
      <div>
        <font color="#F16529" style="font-family: 'SUT Regular'">
          {{ new Date().getFullYear() }} —
          <strong style="font-family: 'SUT Regular'"
            >SUT Student Council Election</strong
          >
        </font>
      </div>
    </v-footer>
  </v-app>
</template>
<script>
import Api from "./Api";
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
      drawer: false,
      userhash: undefined,
      dialog2: false,
      adminItems: [
        {
          icon: "mdi-home",
          role: "all",
          text: "หน้าหลัก",
          click: () => {
            this.$router.push("/adminhomepage");
          },
        },

        {
          icon: "mdi-contacts",
          role: "staff",
          text: "ลงทะเบียนผู้สมัคร",
          click: () => {
            this.$router.push("/candidateprofile");
          },
        },
        {
          icon: "mdi-clock",
          role: "staff",
          text: "จัดการเวลา",
          click: () => {
            this.$router.push("/settime");
          },
        },
        {
          icon: "mdi-account-box",
          role: "staff",
          text: "จัดการผู้สมัคร",
          click: () => {
            this.$router.push("/candidatelist");
          },
        },
        {
          icon: "mdi-account-box",
          role: "staff",
          text: "แสดงความถูกต้องของข้อมูล",
          click: () => {
            this.$router.push("/hashchecker");
          },
        },
      ],
      studentItems: [
        {
          icon: "mdi-home",
          role: "all",
          text: "หน้าหลัก",
          click: () => {
            this.$router.push("/home");
          },
        },        
        {
          icon: "mdi-vote",
          role: "student",
          text: "ลงคะแนน",
          click: () => {
            this.$router.push("/vote");
          }
        },
        {
          icon: "mdi-contacts",
          role: "student",
          text: "ข้อมูลผู้สมัคร",
          click: () => {
            this.$router.push("/candidateDetail");
          }
        },
        {
          icon: "mdi-bullhorn",
          role: "all",
          text: "ผลการลงคะแนน",
          click: () => {
            this.$router.push("/voteResult");
          }
        },
        {
          icon: "mdi-account-check",
          role: "student",
          text: "ตรวจสอบผลการลงคะแนน",
          click: () => {
            this.dialog2 = true;
          }
        },
      ]
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
      if (this.userRole === "staff") {
        return "#EBEBEB";
      } else if (this.userRole === "student") {
        return "#EBEBEB";
      } else {
        return "#EBEBEB";
      }
    },

    footerColor() {
      if (this.userRole === "staff") {
        return "#EBEBEB";
      } else if (this.userRole === "student") {
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
      this.$router.push("/home");
    },
    handleVoteResult() {
      this.$router.push("/voteResult");
    },
    async checkStudentHash(userhash) {
      await Api.get(
        `/api/vote/getCorrectStudentHashByStudentHash?USER_HASH=${userhash}`
      )
        .then((res1) => {
          console.log(res1);
          if (res1.data === true) {
            alert("ผลการลงคะแนนถูกต้อง");
          } else {
            alert("ตรวจพบความผิดพลาดของการลงคะแนน กรุณาติดต่อเจ้าหน้าที่");
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    // goCandidateList() {
    //   this.$router.push("/candidatelist");
    // },
    // goCandidateProfile() {
    //   this.$router.push("/candidateprofile");
    // },
    // goSetTime() {
    //   this.$router.push("/settime");
    // },
    // goHomeAdmin() {
    //   this.$router.push("/adminhomepage");
    // },
    // goVote() {
    //   this.$router.push("/vote");
    // },
    // goCandidateDetail() {
    //   this.$router.push("/candidateDetail");
    // },
  },
};
</script>
<style>
.v-btn {
  font-size: 2em;
}
</style>
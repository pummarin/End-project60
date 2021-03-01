<template>
  <v-row align="center" justify="center">
    <v-col cols="12" sm="8" md="6">
      <v-card class="elevation-12">
        <v-toolbar color="#FB8C00" dark>
          <v-toolbar-title>เข้าสู่ระบบในฐานะนักศึกษา</v-toolbar-title>
          <v-spacer />
        </v-toolbar>
        <v-card-text>
          <v-form>
            <v-text-field
              label="Login"
              name="login"
              prepend-icon="mdi-account"
              type="text"
              v-model="username"
            />

            <v-text-field
              id="password"
              label="Password"
              name="password"
              prepend-icon="mdi-lock"
              type="password"
              v-model="password"
            />
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn color="#FB8C00" dark @click="handleAdminSignin"
            >เข้าสู่ระบบในฐานะผู้ดูแล</v-btn
          >
          <v-btn color="#FB8C00" dark @click="signin">Login</v-btn>
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import api from "../Api.js";

export default {
  name: "sigin",
  data() {
    return {
      username: undefined,
      password: undefined,
      date_time_election: [],
      today: null, //วันที่
      login_today: null, //login วันนี้
      election_day: null,
      time_start: null, //เริ่มเวลาเท่าไหร่
      time_end: null, //หมดเวลาเท่าไหร่
    };
  },
  methods: {
    handleAdminSignin() {
      this.$router.push("/admin/signin");
    },

    async getAllTime() {
      await api
        .get("/api/timeshow")
        .then((response) => {
          this.date_time_election = JSON.parse(JSON.stringify(response.data));
          console.log(this.date_time_election[0]);
          console.log(JSON.parse(JSON.stringify(response.data)));
          console.log("get time_start = " + this.date_time_election[0].time_start);
        })
        .catch((e) => {
          console.log(e);
        });
    },

    signin() {
      let user = {
        studentId: this.username,
        identifyNumber: this.password,
      };
      // var timeout = new Date();
      // timeout.setHours(0);
      // timeout.setMinutes(10);
      // this.getAllTime();
      // var date2 = date1.toLocaleDateString("th-TH" ,"d/M/yyyy"); // วันที่ 3/2/2564
      // console.log(date1.toLocaleDateString("th-TH" ,"d/M/yyyy"));

      var date1 = new Date();
      this.today = date1;
      console.log(date1);

      var login_date_now = date1.toISOString().slice(0, 10).replace(/-/g, "-");
      this.login_today = login_date_now;
      console.log(login_date_now);

      // var time_now = date1.getHours() + ":" + date1.getMinutes() + ":" + date1.getSeconds();
      // console.log("time that you loged in = " + time_now);

      //  this.$router.push("/candidateDetail");

      // var end_election_time = this.time_end.toLocaleTimeString(); //หมดเวลา
      // var start_election_time = this.time_start.toLocaleTimeString(); //ในช่วงเวลานั้น
      // var login_time = time_now.toLocaleTimeString(); //เวลาที่ล็อกอิน
      // var login_date = time_now.toLocaleDateString("fr-CA"); //วันที่ล็อกอิน

      // if(login_date == election_date){
      //   if(login_time > start_election_time && login_time < end_election_time){
      //     console.log("in time");
      //   } else if(login_time < start_election_time) {
      //     console.log("election not start yet");
      //   } else if(login_time > end_election_time){
      //     console.log("election closed");
      //   }
      // }

      api
        .post("/auth/login", JSON.stringify(user))
        .then((res) => {
          let user = res.data;
          localStorage.setItem("user", JSON.stringify(user));
          // localStorage.setItem("login_time", Date.now());
          // console.log(localStorage.toLocaleTimeString());
          this.$router.go("/home");
        })
        .catch((e) => {
          alert(e);
        });
    },
  },
  mounted() {
    this.getAllTime();
    // console.log(this.date_time_election);
    if (localStorage.getItem("user")) {
      // if (this.login_date_now < this.date_time_election[0].election_day) {
      //   this.$router.push("/home");
      // } else if (this.login_date_now > this.date_time_election[0].election_day) {
      //   this.$router.push("/home");
      // } else {
      //   this.$router.push("/candidateDetail");
      // }
      this.$router.push("/candidateDetail");
    }
  },
};
</script>

<style></style>
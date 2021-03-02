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
      return new Promise((success, err) => {
        api
          .get("/api/timeshow")
          .then((response) => {
            this.date_time_election = JSON.parse(JSON.stringify(response.data));
            console.log(this.date_time_election[0]);
            console.log(JSON.parse(JSON.stringify(response.data)));
            console.log(
              "get time_start = " + this.date_time_election[0].time_start
            );
            success(true);
          })
          .catch((e) => {
            console.log(e);
            err(false);
          });
      });
    },

    signin() {
      let user = {
        studentId: this.username,
        identifyNumber: this.password,
      };
      
      var now = new Date();
      console.log(now);
      //เซตวันเวลาเริ่มต้น
      var electionDateStart = new Date(this.date_time_election[0].election_day + " " + this.date_time_election[0].time_start);
      // console.log(electionDateStart);
      //เซตวันเวลาสิ้นสุด
      var electionDateEnd = new Date(this.date_time_election[0].election_day + " " + this.date_time_election[0].time_end);
      // console.log(electionDateEnd);

      //เช็คอยู่ในช่วงเวลาไหม
      if (now >= electionDateStart && now <= electionDateEnd) {
        // console.log("อยู่ช่วงเวลาลงคะแนน");
        api
        .post("/auth/login", JSON.stringify(user))
        .then((res) => {
          localStorage.setItem("user", JSON.stringify(res.data));
          this.$router.go("/candidateDetail");
        })
        .catch((e) => {
          alert(e);
        });
      }else{
        // console.log("ไม่อยู่ในช่วงเวลาลงคะแนน");
        alert("ไม่ได้อยู่ในช่วงเวลาทำการ\n"+"เริ่ม: "+electionDateStart.toLocaleString("th-TH") + " สิ้นสุด: "+ electionDateEnd.toLocaleString("th-TH"));
      }
      
    },
  },
  mounted() {
    this.getAllTime();   
    if(localStorage.getItem("user")){
      this.$router.push("/candidateDetail");
    } 
  },
};
</script>

<style></style>
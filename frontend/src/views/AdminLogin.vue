<template>
  <v-row align="center" justify="center">
    <v-col cols="12" sm="8" md="4">
      <v-card class="elevation-12">
        <v-toolbar color="#F16529" dark>
          <!-- <v-toolbar-title style="font-family: 'SUT Regular'">เข้าสู่ระบบในฐานะผู้ดูแล</v-toolbar-title> -->
          <font color="#000000">
            <h2 style="font-family: 'SUT Regular'">เข้าสู่ระบบในฐานะผู้ดูแล</h2>
          </font>
          <v-spacer />
        </v-toolbar>
        <v-card-text>
          <v-form>
            <v-row justify="center">
              <v-col cols="12" sm="6" md="8">
                <h1>
                <v-text-field
                style="font-family: 'SUT Regular'"
                  label="Username"
                  outlined
                  prepend-icon="mdi-account"
                  type="text"
                  v-model="username"
                />
                </h1>
                <h1>
                <v-text-field
                style="font-family: 'SUT Regular'"
                  label="Password"
                  outlined
                  prepend-icon="mdi-lock"
                  type="password"
                  v-model="password"
                  @keyup.enter="signin"
                /></h1>
              </v-col>
            </v-row>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn
            color="#F16529"
            dark
            @click="handleAdminSignin"
            style="font-family: 'SUT Regular'"
          >
            <font color="#000000"
              ><h4>เข้าสู่ระบบสำหรับนักศึกษา</h4></font
            ></v-btn
          >
          <v-btn
            color="#F16529"
            dark
            @click="signin"
            style="font-family: 'SUT Regular'"
          >
            <font color="#000000"><h4>Login</h4></font></v-btn
          >
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
    };
  },
  methods: {
    handleAdminSignin() {
      this.$router.push("/signin");
    },

    signin() {
      let user = {
        username: this.username,
        password: this.password,
      };
      api
        .post("/auth/admin/signin", JSON.stringify(user))
        .then((res) => {
          let user = res.data;
          // alert("Signin successfully..." + user.username);
          localStorage.setItem("user", JSON.stringify(user));
          this.$router.go("/home");
        })
        .catch((e) => {
          alert(e);
        });
    },
  },
  mounted() {
    if (localStorage.getItem("user")) {
      this.$router.push("/adminhomepage");
    }
  },
};
</script>

<style>
</style>

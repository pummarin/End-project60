<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex xs3 sm6 md9 lg12>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="6"> </v-col>
        </v-row>
        <br />
        <v-card  class="mx-auto" max-width="1500">
          <br />
          <h1 class="display-2 font-weight-bold mb-3">Set Time</h1>
          <v-row justify="center">
            <v-col cols="12" sm="4">
              <v-menu
                v-model="date_menu"
                :close-on-content-click="false"
                transition="scale-transition"
                offset-y
                full-width
              >
                <template v-slot:activator="{ on }">
                  <v-text-field
                    v-model="select_date"
                    label="วันเลือกตั้ง (Election Day)"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-on="on"
                    outlined
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-model="select_date"
                  no-title
                  width="400"
                  @input="date_menu = false"
                ></v-date-picker>
              </v-menu>
            </v-col>
          </v-row>
          <v-row justify="center">
            <v-col cols="12" sm="4">
              <v-dialog
                ref="dialog"
                v-model="time_start_menu"
                :close-on-content-click="false"
                transition="scale-transition"
                offset-y
                width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="select_time_start"
                    label="เริ่มเวลา (Start)"
                    prepend-icon="mdi-clock-time-four-outline"
                    readonly
                    v-bind="attrs"
                    outlined
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-time-picker
                  v-if="time_start_menu"
                  v-model="select_time_start"
                  full-width
                  @input="time_start_menu = false"
                >
                  <v-spacer></v-spacer>
                </v-time-picker>
              </v-dialog>
            </v-col>
          </v-row>

          <v-row justify="center">
            <v-col cols="12" sm="4">
              <v-dialog
                ref="dialog"
                v-model="time_end_menu"
                :close-on-content-click="false"
                transition="scale-transition"
                offset-y
                width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="select_time_end"
                    label="หมดเววลา (End)"
                    prepend-icon="mdi-clock-time-four-outline"
                    readonly
                    outlined
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-time-picker
                  v-if="time_end_menu"
                  v-model="select_time_end"
                  full-width
                  @input="time_end_menu = false"
                >
                  <v-spacer></v-spacer>
                </v-time-picker>
              </v-dialog>
            </v-col>
          </v-row>

          <v-row justify="center">
            <v-col cols="6" sm="4">
              <v-select
                 class="pa-0 ma-0"
                      label="ผู้กรอกข้อมูล (Updated By)"
                      v-model="selectAdmin"
                      :items="admins"
                      outlined
                      item-text="name"
                      item-value="admin_id"
              
              />
            </v-col>
          </v-row>

          <v-row justify="center">
            <v-col cols="6" sm="6" class="pa-2 mx-3">
              <v-btn @click="saveEdit" color="success">Edit</v-btn>
              &nbsp;
              <v-btn class="ma-2" router-link to="/home">Cancel</v-btn>
            </v-col>
          </v-row>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>
<script>
import api from "../Api.js";
export default {
  data: () => ({

    date_time: [0],
    select_date: null,
    date_menu: false,

    select_time_start: null,
    time_start_menu: false,

    select_time_end: null,
    time_end_menu: false,
    
    // selectedit: null,

    admins: [],
    selectAdmin: null,
    currentUser: null,
  }),
  mounted() {
    this.getAllTime();

    this.currentUser = JSON.parse(localStorage.getItem("user"));
    console.log("this.currentUser = " + this.currentUser);
    // this.getAllAdmin();
    this.admins.push(this.currentUser);
    this.selectAdmin = this.currentUser; //default ไว้
  },

  methods: {
    backToMenu() {
      this.$router.push("/home");
    },

    getAllAdmin() {
      api
        .get("/api/admins")
        .then((response) => {
          this.admins = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getAllTime() {
      api
        .get("/api/timeshow")
        .then((response) => {
          this.date_time = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch((e) => {
          console.log("error :" + e);
        });
    },


    saveEdit() {
        // console.log(this.selectedit);
      api
      .put("/api/editdate").then((response) => {
        console.log(response);
         window.location.reload();
      });
    },

  },
};
</script>
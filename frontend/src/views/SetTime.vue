<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex xs3 sm6 md9 lg12>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="6">
            <!-- <v-alert type="success" dismissible v-model="alertSuccess"
              >บันทึกข้อมูลสำเร็จ</v-alert
            >
            <v-alert type="error" dismissible v-model="alertFailed"
              >{{ alertmsg }}!</v-alert
            > -->
          </v-col>
        </v-row>
        <br />
        <v-card class="mx-auto" shaped max-width="900">
          <br />
          <h1>Set Time</h1>
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

                  <!-- <v-btn text color="primary" @input="time_start_menu = false">
                    OK
                  </v-btn> -->
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
                    label="หมดเวลา (End)"
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
                item-text="name"
                item-value="admin_id"
                outlined
              />
            </v-col>
          </v-row>

          <v-row justify="center">
            <v-col cols="6" sm="6" class="pa-2 mx-3">
              <v-btn rounded @click="save" color="#F16529">SAVE</v-btn>
              &nbsp;
              <v-btn rounded class="ma-2" color="#EBEBEB" router-link to="/adminhomepage">CANCEL</v-btn>
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
  mounted() {
    this.currentUser = JSON.parse(localStorage.getItem("user"));
    console.log("this.currentUser = " + this.currentUser);
    // this.getAllAdmin();
    this.admins.push(this.currentUser);
    this.selectAdmin = this.currentUser; //default ไว้

    this.clearAlert();
  },
  data() {
    return {
      admins: [],
      selectAdmin: null,

      select_date: null,
      date_menu: false,

      select_time_start: null,
      time_start_menu: false,

      select_time_end: null,
      time_end_menu: false,

      //   alertFailed: false,
      //   alertSuccess: false,
      //   alertmsg: undefined,
      currentUser: null,
    };
  },

  methods: {
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
    clearAlert() {
      this.alertmsg = false;
      this.alertFailed = false;
      this.alertSuccess = false;
    },
    save() {
      var fd = new FormData();
      fd.append("election_day", this.select_date); //String
      fd.append("time_start", this.select_time_start); //String
      fd.append("time_end", this.select_time_end); //String
      fd.append("admin", this.selectAdmin.admin_id); //long
      // Log for debug.
      console.log(
        fd.forEach((v, k) => {
          console.log(k + " = " + v + " -> " + typeof v);
        })
      );
      console.log("Setting Time in process...");
      // console.log(this.selectAdmin);
      api
        .post("/api/timecheck", fd)
        .then((response) => {
          console.log(response.data);
          console.log("Set Time Successfully");
          // this.alertSuccess = true;
          window.location.reload();
        })
        .catch(function (error) {
          console.log(error);
          // this.alertFailed = true;
        });
    },
  },
};
</script>
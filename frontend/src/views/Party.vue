<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex xs3 sm6 md9 lg12>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="6">
            <v-alert type="success" dismissible v-model="alertSuccess">บันทึกข้อมูลสำเร็จ</v-alert>
            <v-alert type="error" dismissible v-model="alertFailed">{{alertmsg}}!</v-alert>
          </v-col>
        </v-row>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">ข้อมูลพรรค</h1>

        <v-row justify="center" class="pb-0 mb-0">
            <v-col cols="12" sm="6" md="3">
            <v-text-field solo label="หมายเลขพรรค" v-model="fillPartyNo" />
            </v-col>

          <v-col cols="12" sm="6" md="3">
            <v-text-field solo label="ชื่อพรรค" v-model="fillPartyName" />
          </v-col>

          <v-col cols="12" sm="6" md="3">
            <v-text-field solo label="นโยบาย" v-model="fillPolicy" />
          </v-col>

          </v-row>

        <v-row justify="center">
          <v-col cols="6" class="pa-0 mx-2">
            <v-btn @click="checksave" class="light-blue accent-4">บันทึก</v-btn>
          </v-col>
        </v-row>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import api from "../Api";
export default {
  data() {
    return {
    
        fillPartyNo: undefined,
        fillPartyName: undefined,
        fillPolicy: undefined,
        Admins: [],
      alertFailed: false,
      alertSuccess: false,
      alertmsg: undefined
    };
  },
  mounted() {
    this.getAdmins();
  },
  methods: {
    getAdmins() {
        api
        .get("/api/admins/")
        .then(response => {
          this.admins.push(JSON.parse(localStorage.getItem("user")));
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log(e);
        });
    },
    save() {
      let data = {
        p_number: this.fillPartyNo,
        p_name: this.fillPartyName,
        p_policy: this.fillPolicy,
        admins: this.selectAdmins
      };
      api
        .post("/api/party", data)
        .then(() => {
          this.clearAlert();
          this.alertSuccess = true;
          this.selectAdmins = null;
          this.fillPartyNo = null;
          this.fillPartyName = null;
          this.fillPolicy = null;
        })
        .catch(e => {
          console.log(e);
        });
    },
    checksave() {
      
      if (
        !this.selectAdmins ||
        !this.fillPartyNo ||
        !this.fillPartyName ||
        !this.fillPolicy
      ) {
        this.clearAlert();
        this.alertmsg = "กรุณากรอกข้อมูลให้ครบ";
        this.alertFailed = true;
      } else {
        this.save();
      }
    }
  }
};
</script>
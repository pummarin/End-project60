<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <v-alert type="success" dismissible v-model="alertSuccess">พบข้อมูล</v-alert>
        <v-alert type="error" dismissible v-model="alertFailed">ไม่พบข้อมูล!</v-alert>
        <h1 class="display-2 font-weight-bold mb-3">ข้อมูลผู้ลงสมัคร</h1>

        <!-- <v-row justify="center" class="pb-0 mb-0">
          <v-col cols="3">
            <v-text-field
              solo
              label="หมายเลขพรรค"
              v-model="partyNum"
              @keyup.enter="SearchPartyNum"
            />
          </v-col>
          <v-col cols="1">
            <v-btn @click="SearchPartyNum" large class="light-blue darken-1">Search</v-btn>
          </v-col>
        </v-row> -->

        <v-card>
          <v-data-table
            :headers="headers"
            :items="candidateProfile"
            :items-per-page="5"
            class="elevation-3"
          ></v-data-table>
        </v-card>

        <!-- <v-card v-if="Statussearch">
          <v-data-table
            :headers="headers"
            :items="SearchPartyNumber"
            :items-per-page="5"
            class="elevation-3"
          ></v-data-table>
        </v-card> -->

      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import api from "../Api.js";
export default {
  mounted() {
    this.getAllCandidateProfile();
  },
  data() {
    return {
      headers: [
        { text: "รหัสนักศึกษา", value: "student_id" },
        { text: "ชื่อนักศึกษา", value: "c_name" },
        { text: "สาขาวิชา", value: "major.major" },
        { text: "ชั้นปี", value: "year" },
        { text: "Gpax", value: "grade" },
        { text: "ตำแหน่ง", value: "position" },
        { text: "ผลงาน", value: "archivement" }
      ],
      candidateProfile: [],
      // SearchPartyNumber: [],
      // partyNum: undefined,
      // Statussearch: false,
      alertFailed: false,
      alertSuccess: false,
    };
  },

  methods: {
    getAllCandidateProfile() {
      api
        .get("/api/canprofile")
        .then((res) => {
          this.candidateProfile = res.data;
          console.log("loading candidateprofiles");
          console.log(JSON.parse(JSON.stringify(res.data)));
          // this.clearAlert();
        })
        .catch((e) => {
          console.log(e);
        });
    },
    // FindCandidateProofileByPartyNumber(){
    //   api
    //     .post("/api/partys"+this.partyNum)

    // },
    // SearchPartyNumber() {
    //   api
    //     .get("/api/canprofile"+this.partyNum)
    //     .then(res => {
    //       this.SearchPartyNumber = res.data;
    //       console.log("loading roombooking");
    //       console.log(JSON.parse(JSON.stringify(res.data)));
    //       this.clearAlert();
    //       if (this.SearchPartyNumber.length == 0) {
    //         this.alertFailed = true;
    //          this.Status = false;
    //          this.Statussearch = true;
    //       } else {
    //         this.alertSuccess = true;
    //         this.Status = false;
    //         this.Statussearch = true;
    //       }
    //     })
    //     .catch(e => {
    //       console.log(e);
    //     });
    // },
    clearAlert() {
      this.alertSuccess = false;
      this.alertFailed = false;
      // this.Status = true;
      // this.Statussearch = false;
    },
  },
};
</script>
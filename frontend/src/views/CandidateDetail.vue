<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <v-alert type="success" dismissible v-model="alertSuccess">พบข้อมูล</v-alert>
        <v-alert type="error" dismissible v-model="alertFailed">ไม่พบข้อมูล!</v-alert>
        <h1 class="display-2 font-weight-bold mb-3">ข้อมูลผู้ลงสมัคร</h1>

         <v-row justify="center" class="pb-0 mb-0">
            <v-col cols="3">
            <v-text-field solo 
            label="หมายเลขพรรค"
            v-model="partyNum"
            @keyup.enter="SearchPartyNum"/>
            </v-col>
            <v-col cols="1">
                <v-btn @click="SearchPartyNum" large class="light-blue darken-1">Search</v-btn>
            </v-col>
        </v-row>

        <v-card v-if="Status">
            <v-data-table
              :headers="headers"
              :items="roomBooking"
              :items-per-page="5"
              class="elevation-3"
            ></v-data-table>
        </v-card> 
        <v-card v-if="Statussearch">
            <v-data-table
              :headers="headers"
              :items="SearchRoom"
              :items-per-page="5"
              class="elevation-3"
            ></v-data-table>
        </v-card> 

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
        { text: "รหัสนักศึกษา", value: "candidateprofile.student_id" },
        { text: "ชื่อนักศึกษา", value: "candidateprofile.c_name" },
        { text: "สาขาวิชา", value: "candidateprofile.major" },
        { text: "ชั้นปี", value: "candidateprofile.year" },
        { text: "Gpax", value: "candidateprofile.grade" },
        { text: "ตำแหน่ง", value: "candidateprofile.position"},
        { text: "ผลงาน", value: "candidateprofile.archivement"}
      ],
      roomBooking: [],
      SearchRoom: [],
      partyNum: undefined,
      Status: true,
      Statussearch: false,
      alertFailed: false,
      alertSuccess: false,
    };
  },

  methods: {
     getAllCandidateProfile() {
      api
        .get("/api/GetAndSortData")
        .then(res => {
          this.roomBooking = res.data;
          console.log("loading roombooking");
          console.log(JSON.parse(JSON.stringify(res.data)));
          // this.clearAlert();
        })
        .catch(e => {
          console.log(e);
        });
    },
    SearchRoomname() {
      api
        .get("/api/roombooking/"+this.roomName)
        .then(res => {
          this.SearchRoom = res.data;
          console.log("loading roombooking");
          console.log(JSON.parse(JSON.stringify(res.data)));
          this.clearAlert();
          if (this.SearchRoom.length == 0) {
            this.alertFailed = true;
             this.Status = false;
             this.Statussearch = true;
          } else {
            this.alertSuccess = true;
            this.Status = false;
            this.Statussearch = true;
          }
        })
        .catch(e => {
          console.log(e);
        });
    },
    clearAlert() {
    this.alertSuccess = false;
    this.alertFailed = false;
    this.Status = true;
    this.Statussearch = false;
  }

  },
  
};
</script>
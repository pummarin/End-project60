<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <v-alert type="success" dismissible v-model="alertSuccess">ค้นหาสำเร็จ</v-alert>
        <v-alert type="error" dismissible v-model="alertFailed">ไม่พบข้อมูล!</v-alert>

        <h1 class="display-2 font-weight-bold mb-3">Candidate Profile List</h1>

        <!-- <v-row justify="center" class="pb-0 mb-0">
          <v-col cols="3">
            <v-text-field
              solo
              label="Search"
              v-model="can_name"
              @keyup.enter="searchByName"
            />
          </v-col>
          <v-col cols="1">
            <v-btn @click="searchByName" large class="light-blue darken-1">Search</v-btn>
          </v-col>
        </v-row> -->

        <!-- <v-card v-if="StatusSearch">
          <v-data-table :headers="headers" :items="list_Can" :items-per-page="5"></v-data-table>
        </v-card> -->

        <v-card v-if="StatusGetAll">
        
          <v-data-table :headers="headers"  :items="lstAllEnroll" :items-per-page="5">
     
          </v-data-table>
          
    
        </v-card>
        &nbsp;
            <v-btn class="ma-2" router-link to="/home">BACK</v-btn>
      </v-flex>
    </v-layout>
  </v-container>
</template>


<script>
import api from "../Api.js";
export default {
  data() {
    return {
      headers: [
        { text: "Number",value: "c_number", align: "left", sortable: false},
        { text: "Year", value: "year", sortable: false },
        { text: "Name", value: "c_name", sortable: false },
        { text: "Purpose", value: "purpose", sortable: false }
       
         
      ],
      list_Can: [],
      lstAllEnroll: [],
     
  
      StatusGetAll: true,
      alertSuccess: false,
      alertFailed: false
    };
  },
  mounted() {
    this.getAllCandidate();
  },
  methods: {
    // searchByName() {
    //   api
    //     .get("/api/canprofile/searchCandidate=" + this.can_name)
    //     .then(response => {
    //       this.list_Can = response.data;
    //       console.log(JSON.parse(JSON.stringify(response.data)));
    //       this.clearAlert();
    //       if (this.list_Can.length == 0) {
    //         this.alertFailed = true;
    //         this.StatusSearch = true;
    //         this.StatusGetAll = false;
    //       } else {
    //         this.alertSuccess = true;
    //         this.StatusSearch = true;
    //         this.StatusGetAll = false;
    //       }
    //     })
    //     .catch(e => {
    //       console.log("error :" + e);
    //     });
    // },

    clearAlert() {
      this.alertSuccess = false;
      this.alertFailed = false;
      this.StatusSearch = false;
      this.StatusGetAll = true;
    },
    getAllCandidate() {
      api
        .get("/api/canprofile")
        .then(response => {
          this.lstAllEnroll = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log("error :" + e);
        });
    }
  }
};
</script>
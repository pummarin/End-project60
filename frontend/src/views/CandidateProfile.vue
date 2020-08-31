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
        <h1 class="display-2 font-weight-bold mb-3">ข้อมูลผู้สมัคร</h1>

        <v-row justify="center" class="pb-0 mb-0">
            <v-col cols="12" sm="6" md="3">
            <v-text-field solo label="คำนำหน้าชื่อ" v-model="fillTitleName" />
            </v-col>

          <v-row justify="center">
          <v-col cols="6">
            <v-select class="pa-0 ma-0" label="เพศ" v-model="selectGender"
              :items="Gender" item-text="gender" item-value="gender.gender_id" />
          </v-col>
        </v-row>
   
          <v-col cols="12" sm="6" md="3">
            <v-text-field solo label="ชื่อ-สกุล" v-model="fillName" />
          </v-col>

          <v-col cols="12" sm="6" md="3">
            <v-text-field solo label="รหัสนักศึกษา" v-model="fillStudentId" />
          </v-col>

          <v-col cols="12" sm="6" md="3">
            <v-text-field solo label="ตำแหน่ง" v-model="fillPosition" />
          </v-col>

          <v-row justify="center">
          <v-col cols="6">
            <v-select class="pa-0 ma-0" label="สำนักวิชา" v-model="selectMajor"
              :items="Major" item-text="major" item-value="id" />
          </v-col>
          </v-row>

        <v-col cols="12" sm="6" md="3">
            <v-text-field solo label="ชั้นปี" v-model="fillYear" />
          </v-col>

          <v-col cols="12" sm="6" md="3">
            <v-text-field solo label="เกรด" v-model="fillGrade" />
          </v-col>

          <v-col cols="12" sm="6" md="3">
            <v-text-field solo label="ผลงาน" v-model="fillArchivement" />
          </v-col>

        //upload file
          </v-row>

        <v-row justify="center">
          <v-col cols="6" class="pa-0 mx-2">
            <v-btn @click="checksave" class="light-blue accent-4">เพิ่ม</v-btn>
          </v-col>
          <v-row justify="center">
          <v-col cols="6" class="pa-0 mx-2">
            <v-btn class="light-red accent-4" router-link to="/">ยกเลิก</v-btn>
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
    
        fillTitleName: undefined,
        fillName: undefined,
        fillStudentId: undefined,
        fillPosition: undefined,
        fillYear: undefined,
        fillGrade: undefined,
        fillArchivement: undefined,

        Major: [],
        selectMajor: undefined,

        Gender: [],
        selectGender: undefined,
        
        Admins: [],
      alertFailed: false,
      alertSuccess: false,
      alertmsg: undefined
    };
  },
  mounted() {
    this.getAdmins();
    this.getGender();
    this.getMajor();
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
    getGender() {
        api
        .get("/api/gender/")
        .then(response => {
          this.gender = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log(e);
        });
    },
    getMajor() {
        api
        .get("/api/major/")
        .then(response => {
          this.major = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log(e);
        });
    },
    save() {
      let data = {
        title_name: this.fillTitleName,
        c_name: this.fillName,
        student_id: this.fillStudentId,
        position: this.fillPosition,
        year: this.fillYear,
        grade: this.fillGrade,
        archivement: this.fillArchivement,

        major: this.selectMajor,
        gender: this.selectGender,
        admins: this.selectAdmins
      };
      api
        .post("/api/canp", data)
        .then(() => {
          this.clearAlert();
          this.alertSuccess = true;

          this.selectAdmins = null;
          this.selectMajor = null;
          this.selectGender = null;

          this.fillTitleName = null;
          this.fillName = null;
          this.fillStudentId = null;
          this.fillPosition = null;
          this.fillYear = null;
          this.fillGrade = null;
          this.fillArchivement = null;

        })
        .catch(e => {
          console.log(e);
        });
    },
    checksave() {
      
      if (
        !this.selectAdmins ||
        !this.selectMajor ||
        !this.selectGender ||
        !this.fillTitleName ||
        !this.fillName ||
        !this.fillStudentId ||
        !this.fillPosition ||
        !this.fillYear ||
        !this.fillGrade ||
        !this.fillArchivement
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
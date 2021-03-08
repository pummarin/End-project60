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
          <h1 style="font-family: 'SUT Regular'">Add Candidate</h1>

          <v-row justify="center">
            <v-col cols="12" sm="6" md="3">
              <v-text-field style="font-family: 'SUT Regular'"
                label="หมายเลขผู้สมัคร (No.)"
                placeholder=" "
                outlined
                v-model="fillCanNumber"
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row justify="center">
            <v-col cols="12" sm="6" md="3">
              <v-text-field style="font-family: 'SUT Regular'"
                label="คำนำหน้าชื่อ (Title Name)"
                placeholder=" "
                outlined
                v-model="fillTitleName"
              ></v-text-field>
            </v-col>

            <v-col cols="12" sm="6" md="6">
              <v-text-field style="font-family: 'SUT Regular'"
                label="ชื่อ-สกุล (Firstname - Lastname)"
                placeholder=" "
                outlined
                v-model="fillName"
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row justify="center">
            <v-col cols="6" sm="2" md="3">
              <v-select style="font-family: 'SUT Regular'"
                class="pa-0 ma-0"
                label="เพศ (Gender)"
                v-model="selectGender"
                :items="genders"
                item-text="gender"
                item-value="gender_id"
                outlined
              />
            </v-col>

            <v-col cols="6" sm="2" md="3">
              <v-menu
                v-model="menu2"
                :close-on-content-click="false"
                transition="scale-transition"
                offset-y
                full-width
                max-width="290px"
                min-width="290px"
              >
                <template v-slot:activator="{ on }">
                  <v-text-field style="font-family: 'SUT Regular'"
                    v-model="selectDate"
                    label="วัน/เดือน/ปีเกิด (Birthday)"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-on="on"
                    outlined
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-model="selectDate"
                  no-title
                  @input="menu2 = false"
                ></v-date-picker>
              </v-menu>
            </v-col>

            <v-col cols="12" sm="6" md="3">
              <v-text-field style="font-family: 'SUT Regular'"
                label="เบอร์โทรศัพท์ (Mobile Phone)"
                placeholder=" "
                outlined
                v-model="fillTelephone"
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row justify="center">
            <v-col cols="12" sm="6" md="3">
              <v-text-field
              style="font-family: 'SUT Regular'"
                label="รหัสนักศึกษา (Student ID No.)"
                placeholder=" "
                outlined
                v-model="fillStudentId"
              ></v-text-field>
            </v-col>

            <v-col cols="12" sm="6" md="3">
              <v-text-field
              style="font-family: 'SUT Regular'"
                label="ชั้นปี (Year)"
                placeholder=" "
                outlined
                v-model="fillYear"
              ></v-text-field>
            </v-col>

            <v-col cols="12" sm="6" md="3">
              <v-text-field
              style="font-family: 'SUT Regular'"
                label="คะแนนเฉลี่ยสะสม (GPAX)"
                placeholder=" "
                outlined
                v-model="fillGrade"
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row justify="center">
            <v-col cols="6">
              <v-select
              style="font-family: 'SUT Regular'"
                class="pa-0 ma-0"
                label="สำนักวิชา-สาขาวิชา (Major)"
                v-model="selectMajor"
                :items="majors"
                item-text="major"
                item-value="major_id"
                outlined
              />
            </v-col>
          </v-row>

          <v-row justify="center">
            <v-col cols="12" sm="6" md="6">
              <v-text-field
              style="font-family: 'SUT Regular'"
                label="กิจกรรมที่เคยร่วม (Archivement)"
                placeholder=" "
                outlined
                v-model="fillArchivement"
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row justify="center">
            <v-col cols="12" sm="6" md="6">
              <v-text-field
              style="font-family: 'SUT Regular'"
                label="จุดมุ่งหมายในการสมัครครั้งนี้ (Purpose)"
                placeholder=" "
                outlined
                v-model="fillPurpose"
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row justify="center">
            <v-col cols="12" sm="6" md="6">
              <v-file-input
              style="font-family: 'SUT Regular'"
                accept="image/png, image/jpeg"
                label="รูปภาพ (Image)"
                prepend-icon="mdi-camera"
                outlined
                @change="onFileSelected"
              ></v-file-input>
            </v-col>
          </v-row>

          <v-row justify="center">
            <v-col cols="12" sm="6" md="6">
              <v-file-input
              style="font-family: 'SUT Regular'"
                label="ผลงาน (Portfolio)"
                outlined
                @change="pdfSelected"
              ></v-file-input>
            </v-col>
          </v-row>

          <v-row justify="center">
            <v-col cols="6">
              <v-select
              style="font-family: 'SUT Regular'"
                class="pa-0 ma-0"
                label="ผู้กรอกข้อมูล (Updated By)"
                v-model="selectAdmin"
                :items="admins"
                item-text="name"
                item-value="admin_id"
                outlined
                disabled
              />
            </v-col>
          </v-row>

          <v-row justify="center">
            <v-col cols="6" sm="6" class="pa-2 mx-3">
              <v-btn rounded @click="save" color="#F16529" style="font-family: 'SUT Regular'">
                <h3>SAVE</h3></v-btn>
              &nbsp;
              <v-btn rounded class="ma-2" color="#EBEBEB" router-link to="/adminhomepage" style="font-family: 'SUT Regular'">
              <h3>CANCEL</h3></v-btn>
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
    this.getAllGender();
    this.getAllMajor();
    this.clearAlert();
  },
  data() {
    return {
      fillTitleName: "",
      fillName: "",
      fillTelephone: "",
      fillStudentId: "",
      fillYear: "",
      fillGrade: "",
      fillArchivement: "",
      fillCanNumber: "",
      fillPurpose: "",
      selectedFile: null,
      file: null,
      selectedPdf: null,
      pdf: null,
      majors: [],
      selectMajor: null,
      genders: [],
      selectGender: null,
      admins: [],
      selectAdmin: null,
      response: null,
      message: null,
      selectDate: null,
      menu2: false,
      alertFailed: false,
      alertSuccess: false,
      alertmsg: undefined,
      currentUser: null,
    };
  },
  methods: {
    onFileSelected(file) {
      // ให้ภาพที่เราเลือกจากคอม มาเก็บไว้ในตัวแปร selectedFile
      console.log(file);
      this.file = file;
      // this.selectedFile = event.target.files[0];
    },
    pdfSelected(pdf) {
      // ให้ภาพที่เราเลือกจากคอม มาเก็บไว้ในตัวแปร selectedFile
      console.log(pdf);
      this.pdf = pdf;
      // this.selectedFile = event.target.files[0];
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
    getAllGender() {
      api
        .get("/gender")
        .then((response) => {
          this.genders = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getAllMajor() {
      api
        .get("/major")
        .then((response) => {
          this.majors = response.data;
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
      // หน้าบ้าน FormData -> key ต้องให้ตรงกับ หลังบ้าน @RequestParam("key")
      fd.append("title_name", this.fillTitleName); //String
      fd.append("canp_name", this.fillName); //String
      fd.append("birthday", this.selectDate); //String
      fd.append("telephone", this.fillTelephone); //String
      fd.append("student_id", this.fillStudentId); //String
      fd.append("year", this.fillYear); //int
      fd.append("grade", this.fillGrade); //String
      fd.append("archivement", this.fillArchivement); //String
      fd.append("c_number", this.fillCanNumber); //int
      fd.append("purpose", this.fillPurpose); //String
      fd.append("major", this.selectMajor); //long
      fd.append("gender", this.selectGender); //long
      fd.append("admin", this.selectAdmin.admin_id); //long
      fd.append("file", this.file); //MultipartFile
      fd.append("pdf", this.pdf); //MultipartFile
      // Log for debug.
      console.log(
        fd.forEach((v, k) => {
          console.log(k + " = " + v + " -> " + typeof v);
        })
      );
      console.log("Posting in process...");
      console.log(this.selectAdmin);
      api
        .post("/api/canp", fd)
        .then((response) => {
          console.log(response.data);
          console.log("Posting Successfully");
          // this.alertSuccess = true;
          // alert("Added New Candidate Successfully");

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
<style>
.v-text-field input {
  font-size: 1.3em;
}
.v-text-field label {
  font-size: 1.5em;
}
.v-text-field title {
  font-size: 1.5em;
}

</style>
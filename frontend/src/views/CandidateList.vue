<template>
  <v-container>
    <v-flex mb-4>
      <v-data-table :headers="headers" :items="allCandidate" class="elevation-1" style="font-family: 'SUT Regular'">
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title style="font-family: 'SUT Regular'"><h2>Candidates Management</h2></v-toolbar-title>
            <v-divider class="mx-4" inset vertical></v-divider>
            <v-spacer></v-spacer>

            <v-dialog v-if="selectEdit" v-model="dialogEdit" max-width="800">
              <v-card>
                <v-row justify="center">
                  <v-card-text class="text-center">
                    <v-row justify="center">
                      <v-col cols="12" sm="6" md="6">
                        <v-img
                          max-height="320"
                          max-width="320"
                          v-if="selectEdit.avatar"
                          :src="
                            'http://localhost:9000/files/' + selectEdit.avatar
                          "
                        ></v-img>
                        <v-progress-circular
                          v-if="!selectEdit.avatar"
                          indeterminate
                          color="primary"
                        ></v-progress-circular>
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-row>
                <v-row justify="center">
                  <v-col cols="12" sm="6" md="3">
                    <p>
                      <v-text-field
                        style="font-family: 'SUT Regular'"
                        label="หมายเลขผู้สมัคร (No.)"
                        placeholder=" "
                        outlined
                        v-model="selectEdit.c_number"
                      ></v-text-field>
                    </p>
                  </v-col>
                </v-row>

                <p>
                  <v-row justify="center">
                    <v-col cols="12" sm="6" md="3">
                      <v-text-field
                        style="font-family: 'SUT Regular'"
                        label="คำนำหน้าชื่อ (Title Name)"
                        placeholder=" "
                        outlined
                        v-model="selectEdit.title_name"
                      ></v-text-field>
                    </v-col>

                    <v-col cols="12" sm="6" md="6">
                      <v-text-field
                        style="font-family: 'SUT Regular'"
                        label="ชื่อ-สกุล (Firstname - Lastname)"
                        placeholder=" "
                        outlined
                        v-model="selectEdit.c_name"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </p>
                <p>
                  <v-row justify="center">
                    <v-col cols="6" sm="3">
                      <v-select
                        style="font-family: 'SUT Regular'"
                        class="pa-0 ma-0"
                        label="เพศ (Gender)"
                        v-model="selectEdit.gender"
                        :items="genders"
                        item-text="gender"
                        outlined
                        return-object
                      />
                    </v-col>

                    <v-col cols="6" sm="3">
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
                          <v-text-field
                            style="font-family: 'SUT Regular'"
                            v-model="selectEdit.birthday"
                            label="วัน/เดือน/ปีเกิด (Birthday)"
                            prepend-icon="mdi-calendar"
                            readonly
                            v-on="on"
                            outlined
                          ></v-text-field>
                        </template>
                        <v-date-picker
                          v-model="selectEdit.birthday"
                          no-title
                          @input="menu2 = false"
                        ></v-date-picker>
                      </v-menu>
                    </v-col>

                    <v-col cols="12" sm="6" md="3">
                      <v-text-field
                        style="font-family: 'SUT Regular'"
                        label="เบอร์โทรศัพท์ (Mobile Phone)"
                        placeholder=" "
                        outlined
                        v-model="selectEdit.telephone"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </p>
                <p>
                  <v-row justify="center">
                    <v-col cols="12" sm="6" md="3">
                      <v-text-field
                        style="font-family: 'SUT Regular'"
                        label="รหัสนักศึกษา (Student ID No.)"
                        placeholder=" "
                        outlined
                        v-model="selectEdit.studentId"
                      ></v-text-field>
                    </v-col>

                    <v-col cols="12" sm="6" md="3">
                      <v-text-field
                        style="font-family: 'SUT Regular'"
                        label="ชั้นปี (Year)"
                        placeholder=" "
                        outlined
                        v-model="selectEdit.year"
                      ></v-text-field>
                    </v-col>

                    <v-col cols="12" sm="6" md="3">
                      <v-text-field
                        style="font-family: 'SUT Regular'"
                        label="คะแนนเฉลี่ยสะสม (GPAX)"
                        placeholder=" "
                        outlined
                        v-model="selectEdit.grade"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </p>
                <p>
                  <v-row justify="center">
                    <v-col cols="12" sm="6" md="8">
                      <v-select
                        style="font-family: 'SUT Regular'"
                        class="pa-0 ma-0"
                        label="สำนักวิชา-สาขาวิชา (Major)"
                        v-model="selectEdit.major"
                        :items="majors"
                        item-text="major"
                        outlined
                        return-object
                      />
                    </v-col>
                  </v-row>
                </p>
                <p>
                  <v-row justify="center">
                    <v-col cols="12" sm="6" md="8">
                      <v-text-field
                        style="font-family: 'SUT Regular'"
                        label="กิจกรรมที่เคยร่วม (Archivement)"
                        placeholder=" "
                        outlined
                        v-model="selectEdit.archivement"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </p>
                <p>
                  <v-row justify="center">
                    <v-col cols="12" sm="6" md="8">
                      <v-text-field
                        style="font-family: 'SUT Regular'"
                        label="จุดมุ่งหมายในการสมัครครั้งนี้ (Purpose)"
                        placeholder=" "
                        outlined
                        v-model="selectEdit.purpose"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </p>
                <p>
                  <v-row ow justify="center">
                    <v-col cols="12" sm="6" md="4">
                      <v-file-input
                        style="font-family: 'SUT Regular'"
                        accept="image/png, image/jpeg"
                        label="รูปภาพ (Image)"
                        prepend-icon="mdi-camera"
                        outlined
                        @change="onFileSelected"
                      ></v-file-input>
                    </v-col>
                    <!-- </v-row>
                <v-row justify="center"> -->
                    <v-col cols="12" sm="6" md="4">
                      <v-file-input
                        style="font-family: 'SUT Regular'"
                        label="ผลงาน (Portfolio)"
                        outlined
                        @change="pdfSelected"
                      ></v-file-input>
                    </v-col>
                  </v-row>
                </p>
                <p>
                  <v-row justify="center">
                    <v-col cols="6" sm="6" md="8">
                      <v-select
                        style="font-family: 'SUT Regular'"
                        class="pa-0 ma-0"
                        label="ผู้กรอกข้อมูล (Updated By)"
                        v-model="selectAdmin"
                        :items="admins"
                        outlined
                        disabled
                        item-text="name"
                        item-value="admin_id"
                      />
                    </v-col>
                  </v-row>
                </p>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-row justify="center">
                    <v-col cols="12">
                      <v-btn
                        rounded
                        class="ma-2"
                        color="#F16529"
                        @click="saveEdit"
                        style="font-family: 'SUT Regular'"
                      >
                        <h3>Save</h3></v-btn
                      >
                      <v-btn
                        rounded
                        class="ma-2"
                        color="#EBEBEB"
                        @click="closeEdit"
                        style="font-family: 'SUT Regular'"
                      >
                        <h3>Cancel</h3></v-btn
                      >
                    </v-col>
                  </v-row>
                </v-card-actions>
              </v-card>
            </v-dialog>
            <!-- <v-icon @click="backToMenu">mdi-arrow-left</v-icon> -->

            <v-dialog v-model="dialogDelete" max-width="445px">
              <v-card justify="center">
                <v-card-title
                  class="headline"
                  style="font-family: 'SUT Regular'"
                >
                  <p style="font-family: 'SUT Regular'">
                    Are you sure you want to delete this candidate?
                  </p>
                </v-card-title>
                <v-card-actions>
                  <v-spacer></v-spacer>

                  <v-btn
                    rounded
                    color="#F16529"
                    @click="deleteCandidate"
                    style="font-family: 'SUT Regular'"
                  >
                    <h3>Yes</h3></v-btn
                  >
                  <v-btn
                    rounded
                    color="#EBEBEB"
                    @click="closeDelete"
                    style="font-family: 'SUT Regular'"
                  >
                    <h3>No</h3></v-btn
                  >
                  <v-spacer></v-spacer>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-toolbar>
        </template>
        <template v-slot:item.actions="{ item }">
          <v-icon small class="mr-2" @click="editItem(item)">mdi-pencil</v-icon>
          <v-icon small @click="deleteItem(item)"> mdi-delete </v-icon>
        </template>
      </v-data-table>
    </v-flex>
    <v-card-actions>
      <v-spacer></v-spacer>
      <!-- <v-row justify="center">
        <v-col cols="12">
          <v-btn rounded class="ma-2" color="#EBEBEB" @click="backToMenu">Back to home page</v-btn>
        </v-col>
      </v-row> -->
    </v-card-actions>
  </v-container>
</template>
<script>
import api from "../Api.js";
export default {
  data: () => ({
    menu2: false,
    file: null,
    pdf: null,
    majors: [],
    genders: [],
    dialogDelete: false,
    headers: [
      {
        text: "Candidate Number",
        value: "c_number",
        align: "left",
        sortable: false,
      },
      { text: "Year", value: "year", sortable: false },
      { text: "Name", value: "c_name", sortable: false },
      { text: "Purpose", value: "purpose", sortable: false },
      { text: " ", value: "actions", sortable: false },
    ],
    allCandidate: [],
    selectCanId: null,
    selectEdit: null, //ประกาศตัวแปรไว้เพื่อรับค่า
    dialogEdit: false,
    admins: [],
    selectAdmin: null,
    currentUser: null,
  }),
  mounted() {
    this.getAllCandidate();
    this.getAllGender();
    this.getAllMajor();

    this.currentUser = JSON.parse(localStorage.getItem("user"));
    console.log("this.currentUser = " + this.currentUser);
    // this.getAllAdmin();
    this.admins.push(this.currentUser);
    this.selectAdmin = this.currentUser; //default ไว้
  },

  methods: {
    backToMenu() {
      this.$router.push("/adminhomepage");
    },

    onFileSelected(file) {
      console.log(file);
      this.file = file;
      this.selectEdit.avatar = file.name;
    },
    pdfSelected(pdf) {
      console.log(pdf);
      this.pdf = pdf;
      this.selectEdit.pdf = pdf.name;
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

    getAllCandidate() {
      api
        .get("/api/canprofile")
        .then((response) => {
          this.allCandidate = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch((e) => {
          console.log("error :" + e);
        });
    },

    editItem(item) {
      this.selectEdit = item;
      this.dialogEdit = true;
      console.log(this.selectEdit);
    },

    saveEdit() {
      console.log(this.selectEdit);
      if (this.file !== null) {
        //check ว่าได้อัพโหลดรูปใหม่มั้ย
        var fd = new FormData();
        fd.append("file", this.file);
        api.post("/upload", fd).then((response) => {
          console.log(response);
        });
      }
      if (this.pdf !== null) {
        //check ว่าได้อัพโหลด pdf ใหม่มั้ย
        var pdf1 = new FormData();
        pdf1.append("pdf", this.pdf);
        api.post("/uploadPdfFile", pdf1).then((response) => {
          console.log(response);
        });
      }
      api.put("/api/editcanprofile", this.selectEdit).then((response) => {
        console.log(response);
        this.dialogEdit = false;
        window.location.reload();
      });
    },

    deleteItem(item) {
      this.dialogDelete = true; //pop-up ขึ้นมา Y/N
      console.log(item);
      this.selectCanId = item.can_id; // item ที่เลือกจะลบ ไปเก็บไว้ในตัวแปร selectCanId
      //  api
      //   .delete("/api/deletecanprofile/" + item.can_id).then(response => {
      //      console.log(response);
      //      console.log(this.allCandidate);

      //      let i = this.allCandidate.indexOf(item);
      //      console.log("i = " + i);
      //      this.allCandidate.slice(i, 1);
      //      console.log(this.allCandidate);
      //      this.getAllCandidate();
      //   }

      //   )
      // this.dialogDelete = true
    },

    deleteCandidate() {
      api
        .delete("/api/deletecanprofile/" + this.selectCanId)
        .then((response) => {
          console.log(response);
          console.log(this.allCandidate);

          let i = this.allCandidate.indexOf(this.selectCanId);
          console.log("i = " + i);
          this.allCandidate.slice(i, 1); //ลบข้อมูลแบบ slice คือ (start, end)
          console.log(this.allCandidate);
          this.getAllCandidate(); //เรียกข้อมูลมาใหม่
          this.dialogDelete = false; //ปิด pop-up
        });
    },
    //

    closeEdit() {
      this.dialogEdit = false;
      // this.$nextTick(() => {
      //   this.editedItem = Object.assign({}, this.defaultItem);
      //   this.editedIndex = -1;
      // });
    },

    closeDelete() {
      this.dialogDelete = false;
      // this.$nextTick(() => {
      //   this.editedItem = Object.assign({}, this.defaultItem);
      //   this.editedIndex = -1;
      // });
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
/* 
table.v-table items {
  font-size: 2.0em;
} */
.v-data-table > .v-data-table__wrapper > table > tbody > tr > th, .v-data-table > .v-data-table__wrapper > table > thead > tr > th, .v-data-table > .v-data-table__wrapper > table > tfoot > tr > th {
    font-size: 1.3em;
}
.v-data-table > .v-data-table__wrapper > table > tbody > tr > td, .v-data-table > .v-data-table__wrapper > table > thead > tr > td, .v-data-table > .v-data-table__wrapper > table > tfoot > tr > td {
    font-size: 1.3em;
}

.v-data-footer {
    font-size: 1.2em;
}
.v-data-footer__select .v-select__selections .v-select__selection--comma {
    font-size: 1.2em;
}
.v-list-item__title {
    font-family: 'SUT Regular';
    font-size: 1.3em;
}
.v-date-picker-table .v-btn {
  font-family: "SUT Regular";
  font-size: 1.2em;
}
.v-picker {
  font-family: "SUT Regular";
  font-size: 1.2em;
}

.v-date-picker-table th {
  font-family: "SUT Regular";
  font-size: 1.2em;
}
.v-time-picker-clock__item {
  font-family: "SUT Regular";
  font-size: 1.2em;
}
</style>
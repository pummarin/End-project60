<template>
  <v-container>
    <v-flex mb-4>
      <v-data-table
        :headers="headers"
        :items="allCandidate"
        class="elevation-1"
      >
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title>CANDIDATES LIST</v-toolbar-title>
            <v-divider class="mx-4" inset vertical></v-divider>
            <v-spacer></v-spacer>

            <v-dialog v-if="selectEdit" v-model="dialogEdit" max-width="500px">
              <v-card>
                <v-row justify="center">
                  <v-card-text class="text-center">
                    <v-img
                      v-if="selectEdit.avatar"
                      :src="'http://localhost:9000/files/' + selectEdit.avatar"
                    ></v-img>
                    <v-progress-circular
                      v-if="!selectEdit.avatar"
                      indeterminate
                      color="primary"
                    ></v-progress-circular>
                  </v-card-text>
                </v-row>
                <v-row justify="center">
                  <v-col cols="12" sm="6" md="2">
                    <v-text-field
                      label="หมายเลขผู้สมัคร"
                      placeholder=" "
                      outlined
                      v-model="selectEdit.c_number"
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-row justify="center">
                  <v-col cols="12" sm="6" md="2">
                    <v-text-field
                      label="คำนำหน้าชื่อ"
                      placeholder=" "
                      outlined
                      v-model="selectEdit.title_name"
                    ></v-text-field>
                  </v-col>

                  <v-col cols="12" sm="6" md="4">
                    <v-text-field
                      label="ชื่อ-สกุล"
                      placeholder=" "
                      outlined
                      v-model="selectEdit.c_name"
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-row justify="center">
                  <v-col cols="6" sm="2">
                    <v-select
                      class="pa-0 ma-0"
                      label="เพศ"
                      v-model="selectEdit.gender"
                      :items="genders"
                      item-text="gender"
                      return-object
                    />
                  </v-col>

                  <v-col cols="6" sm="2">
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
                          v-model="selectEdit.birthday"
                          label="วัน/เดือน/ปีเกิด"
                          prepend-icon="mdi-calendar"
                          readonly
                          v-on="on"
                        ></v-text-field>
                      </template>
                      <v-date-picker
                        v-model="selectEdit.birthday"
                        no-title
                        @input="menu2 = false"
                      ></v-date-picker>
                    </v-menu>
                  </v-col>

                  <v-col cols="12" sm="6" md="2">
                    <v-text-field
                      label="เบอร์โทรศัพท์"
                      placeholder=" "
                      outlined
                      v-model="selectEdit.telephone"
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-row justify="center">
                  <v-col cols="12" sm="6" md="2">
                    <v-text-field
                      label="รหัสนักศึกษา"
                      placeholder=" "
                      outlined
                      v-model="selectEdit.studentId"
                    ></v-text-field>
                  </v-col>

                  <v-col cols="12" sm="6" md="2">
                    <v-text-field
                      label="ชั้นปี"
                      placeholder=" "
                      outlined
                      v-model="selectEdit.year"
                    ></v-text-field>
                  </v-col>

                  <v-col cols="12" sm="6" md="2">
                    <v-text-field
                      label="คะแนนเฉลี่ยสะสม(GPAX)"
                      placeholder=" "
                      outlined
                      v-model="selectEdit.grade"
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-row justify="center">
                  <v-col cols="6">
                    <v-select
                      class="pa-0 ma-0"
                      label="สำนักวิชา-สาขาวิชา"
                      v-model="selectEdit.major"
                      :items="majors"
                      item-text="major"
                      return-object
                    />
                  </v-col>
                </v-row>

                <v-row justify="center">
                  <v-col cols="12" sm="6" md="6">
                    <v-text-field
                      label="กิจกรรมที่เคยร่วม"
                      placeholder=" "
                      outlined
                      v-model="selectEdit.archivement"
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-row justify="center">
                  <v-col cols="12" sm="6" md="6">
                    <v-text-field
                      label="จุดมุ่งหมายในการสมัครครั้งนี้"
                      placeholder=" "
                      outlined
                      v-model="selectEdit.purpose"
                    ></v-text-field>
                  </v-col>
                </v-row>

                <v-row justify="center">
                  <v-col cols="12" sm="6" md="6">
                    <v-file-input
                      accept="image/png, image/jpeg"
                      label="รูปภาพ"
                      prepend-icon="mdi-camera"
                      outlined
                      @change="onFileSelected"

                    ></v-file-input>
                  </v-col>
                </v-row>

                <!-- accept="image/png, image/jpeg" -->
                <!-- <v-row justify="center">
          <v-col cols="6">
            <v-select
              class="pa-0 ma-0"
              label="ผู้กรอกข้อมูล"
              v-model="selectAdmin"
              :items="admins"
              item-text="name"
              item-value="admin_id"
            />
          </v-col>
        </v-row> -->

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue darken-1" text @click="close">
                    Cancel
                  </v-btn>
                  <v-btn color="blue darken-1" text @click="saveEdit">
                    Save
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>

            <v-dialog v-model="dialogDelete" max-width="600px">
              <v-card>
                <v-card-title class="headline"
                  >Are you sure you want to delete this candidate?</v-card-title
                >
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue darken-1" text @click="closeDelete"
                    >Cancel</v-btn
                  >
                  <v-btn color="blue darken-1" text @click="deleteCandidate"
                    >OK</v-btn
                  >
                  <v-spacer></v-spacer>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-toolbar>
        </template>
        <template v-slot:item.actions="{ item }">
          <v-icon small class="mr-2" @click="editItem(item)">
            mdi-pencil
          </v-icon>
          <v-icon small @click="deleteItem(item)"> mdi-delete </v-icon>
        </template>
      </v-data-table>
    </v-flex>
  </v-container>
</template>
<script>
import api from "../Api.js";
export default {
  data: () => ({
    menu2: false,
    file: null,
    majors: [],
    genders: [],
    dialogDelete: false,
    headers: [
      { text: "Number", value: "c_number", align: "left", sortable: false },
      { text: "Year", value: "year", sortable: false },
      { text: "Name", value: "c_name", sortable: false },
      { text: "Purpose", value: "purpose", sortable: false },
      { text: "Actions", value: "actions", sortable: false },
    ],
    allCandidate: [],
    selectCanId: null,
    selectEdit: null, //ประกาศตัวแปรไว้เพื่อรับค่า
    dialogEdit: false,
  }),
  mounted() {
    this.getAllCandidate();
    this.getAllGender();
    this.getAllMajor();
  },

  watch: {
    dialogEdit(val) {
      val || this.closeEdit();
    },
    dialogDelete(val) {
      val || this.closeDelete();
    },
  },

  methods: {
    onFileSelected(file) {
      // ให้ภาพที่เราเลือกจากคอม มาเก็บไว้ในตัวแปร selectedFile
      console.log(file);
      this.file = file;
      this.selectEdit.avatar = file.name;
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
        var fd = new FormData();
        fd.append("file", this.file);
        api.post("/upload", fd).then((response) => {
          console.log(response);
        });
      }
      api.put("/api/editcanprofile", this.selectEdit).then((response) => {
        console.log(response);
        this.dialogEdit = false;
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
      this.dialog = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    closeDelete() {
      this.dialogDelete = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },
  },
};
</script>
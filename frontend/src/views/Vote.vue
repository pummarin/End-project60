
<template>
  <v-col cols="12" md="5" sm="6">
    <div>
      <div>
        <h1>ลงคะแนนผู้สมัคร</h1>
        <v-container fluid grid-list-md>
          <v-col v-for="i in candidate" v-bind:key="i.can_id">
            <v-card width="650" height="auto" v-if="loaded">
              <v-card-title primary-title>
                <font size="auto">หมายเลขผู้สมัคร: {{ i.c_number }}</font>
              </v-card-title>

              <v-card-text class="text-center">
                <v-img
                  v-if="i.avatar"
                  :src="'http://localhost:9000/files/' + i.avatar"
                ></v-img>
                <v-progress-circular
                  v-if="!i.avatar"
                  indeterminate
                  color="primary"
                ></v-progress-circular>
              </v-card-text>

              <v-card-text>
                <font color="black">
                  <pre>ชื่อ-นามสกุล: {{ i.c_name }}</pre>
                  <pre>Gpax: {{ i.grade }}</pre>
                </font>
              </v-card-text>

              <v-card-actions>
                <div>
                  <v-btn
                    class="ma-2"
                    outlined
                    color="indigo"
                    dark
                    @click="save2(i)"
                    >ลงคะแนน</v-btn
                  >
                </div>
              </v-card-actions>
            </v-card>
          </v-col>
          <!-- <v-btn x-large class="ma-2" outlined color="error" dark
                >ไม่ประสงค์ลงคะแนน</v-btn
              > -->
        </v-container>
      </div>
    </div>
    <v-row justify="center">
      <v-dialog v-model="dialog" persistent max-width="550px">
        <!-- <template v-slot:activator="{ on, attrs }">
          <v-btn color="primary" dark v-bind="attrs" v-on="on">
            Open Dialog
          </v-btn>
        </template> -->
        <v-card>
          <v-card-title class="headline">
            <u>ลงคะแนนสำเร็จ.. กรุณาบันทึกภาพเป็นหลักฐาน</u>
          </v-card-title>
          <v-card-text>
            <p>Code: {{studentHash.hash}}</p>
            <p>Index: {{studentHash.id}}</p>
            </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="green darken-1"
              text
              @click="(dialog = false), goDetail()"
            >
              Disagree
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </v-col>
</template>

<script>
import Axios from "axios";
import Api from "../Api";

export default {
  name: "Vote",
  data() {
    return {
      loaded: false,
      photos: [],
      candidate: [],
      alertSuccess: false,
      dialog: false,
      studentHash: ''
    };
  },
  methods: {
    async getPhotos() {
      this.photos = await Axios.get("https://picsum.photos/v2/list").then(
        (Response) => {
          // console.log(Response.data);
          // this.photos = Response.data;
          return Response.data;
        }
      );
    },

    clearAlert() {
      this.alertSuccess = false;
    },
    async getAllCandidate() {
      const student = await JSON.parse(localStorage.getItem("user"));
      // console.log(student.s_year)
      await Api.get(`/api/canprofile2?year=${student.s_year}`)
        .then((response) => {
          this.candidate = response.data;
          // console.log(JSON.parse(JSON.stringify(response.data)));
          // for(let i in this.candidate){
          //   console.log(i);
          // }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    async save2(c) {
      if (confirm("ยืนยันการลงคะแนนหรือไม่?")) {
        const student = await JSON.parse(localStorage.getItem("user"));
        // console.log(student);
        var data = {
          can_id: c.can_id,
          students_id: student.id,
        };

        Api.post("/api/vote/new", data)
          .then((res) => {
            console.log(res.data);
            this.alertSuccess = true;
            this.getBlockHashVoteStudent();
            this.dialog = true;
            // setTimeout(() => {
            //   this.$router.push("/candidateDetail");
            // }, 1000);
          })
          .catch((e) => {
            console.log(e);
          });
      }
      // console.log(c);
    },
    checkStudentAlreadyCandidate() {
      let user = JSON.parse(localStorage.getItem("user"));
      let body = {
        studentId: user.studentId,
      };
      Api.post("/api/canp/student", JSON.stringify(body))
        .then((res) => {
          if (res.data === true) {
            alert("นักศึกษาเป็นผู้ลงสมัคร");
            this.$router.push("/candidateDetail");
          } else {
            this.loaded = true;
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    goDetail() {
      setTimeout(() => {
        this.$router.push("/candidateDetail");
      }, 1000);
    },
    async getBlockHashVoteStudent() {
      const student = await JSON.parse(localStorage.getItem("user"));
      // console.log(student);
      await Api.get(
        `/api/vote/getHashBlockByStudentId?STUDENT_ID=${student.id}`
      ).then((res) => {
        this.studentHash = res.data
        console.log(this.studentHash);
      });
    },
  },
  mounted() {
    // this.getPhotos();    
    this.clearAlert();
    this.getAllCandidate();
    let user = JSON.parse(localStorage.getItem("user"));
    let body = {
      student_id: user.id,
    };
    // console.log(user.id);
    Api.post("/api/vote/student", JSON.stringify(body))
      .then((res) => {
        // console.log(res.data)
        if (res.data === true) {
          alert("นักศึกษาลงคะแนนไปแล้ว");

          this.$router.push("/candidateDetail");
          // this.$router.go();
        } else {
          this.checkStudentAlreadyCandidate();
        }
      })
      .catch((e) => {
        console.log(e);
      });
  },
};
</script>

<style>
</style>
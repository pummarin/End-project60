
<template>
  <v-col cols="12" md="5" sm="6">
    <div>
      <div>
        <h1 >ข้อมูลผู้สมัคร</h1>
        <v-container grid-list-md>
          <v-col v-for="i in candidate" v-bind:key="i.can_id">
            <v-card width="650" height="auto">
              <v-card-title primary-title>
                <font size="auto">หมายเลขผู้สมัคร: {{ i.c_number }}</font>
              </v-card-title>

              <v-card-text class="text-center">
                <v-img v-if="i.avatar" :src="'http://localhost:9000/files/' + i.avatar"></v-img>
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
                  <!-- <v-btn
                    class="ma-2"
                    outlined
                    color="indigo"
                    dark
                    @click="save2()"
                    >ข้อมูลผู้สมัคร</v-btn
                  > -->
                  <v-row>
                    <v-dialog
                      v-model="dialog"
                      fullscreen
                      hide-overlay
                      transition="dialog-bottom-transition"
                    >
                      <template v-slot:activator="{ on, attrs }">
                        <v-btn
                          class="ma-2"
                          outlined
                          color="indigo"
                          dark
                          v-bind="attrs"
                          v-on="on"
                        >
                          ข้อมูลผู้สมัคร
                        </v-btn>
                        <v-btn
                          class="ma-2"
                          outlined
                          color="primary"
                          dark
                          @click="checkStudentAlreadyVote"
                          >Go ot vote page</v-btn
                        >
                      </template>

                      <v-card>
                        <v-toolbar dark color="primary">
                          <v-btn icon dark @click="dialog = false">
                            <v-icon>mdi-close</v-icon>
                          </v-btn>
                          <v-toolbar-title>รายละเอียด</v-toolbar-title>
                          <v-spacer></v-spacer>
                          <v-toolbar-items>
                            <v-btn dark text @click="dialog = false">
                              Close
                            </v-btn>
                          </v-toolbar-items>
                        </v-toolbar>
                        <v-list three-line subheader>
                          <v-list-item>
                            <v-list-item-content>
                              <v-list-item-title
                                >ข้อมูลส่วนตัว</v-list-item-title
                              >
                              <v-card-text>
                                <pre>ชื่อ-นามสกุล: {{i.title_name}}{{ i.c_name }}</pre>
                                <pre>วัน/เดือน/ปี: {{ i.birthday }}</pre>
                                <pre>เพศ: {{ i.gender.gender }}</pre>
                                <pre>สำนักวิชา: {{i.major.major}}</pre>
                                <pre>รหัสนักศึกษา: {{i.student_id}}</pre>
                                <pre>ชั้นปี: {{i.year}}</pre>
                                <pre>Gpax: {{i.grade}}</pre>
                              </v-card-text>
                            </v-list-item-content>
                          </v-list-item>
                          <v-list-item>
                            <v-list-item-content>
                              <v-list-item-title>จุดประสงค์ในการลงสมัคร</v-list-item-title>
                              <v-card-text>
                                {{i.purpose}}
                              </v-card-text>
                            </v-list-item-content>
                          </v-list-item>
                          <v-list-item>
                            <v-list-item-content>
                              <v-list-item-title>กิจกรรมที่เคยเข้าร่วม</v-list-item-title>
                              <v-card-text>
                                {{i.archivement}}
                              </v-card-text>
                            </v-list-item-content>
                          </v-list-item>
                        </v-list>
                        <v-divider></v-divider>
                      </v-card>
                    </v-dialog>
                  </v-row>
                </div>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-container>
      </div>
    </div>
  </v-col>
</template>

<script>
import Axios from "axios";
import Api from "../Api";

export default {
  name: "Vote",
  data() {
    return {
      photos: [],
      candidate: [],
      alertSuccess: false,
      dialog: false,
    };
  },
  methods: {
    

    clearAlert() {
      this.alertSuccess = false;
    },
    async getAllCandidate() {
      const student = await JSON.parse(localStorage.getItem("user"));
      // console.log(student.s_year);
      await Api.get(`/api/canprofile2?year=${student.s_year}`)
        .then((response) => {
          this.candidate = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
          // for(let i in this.candidate){
          //   console.log(i);
          // }
        })
        .catch((e) => {
          console.log(e);
        });
    },

    async getPhotos() {
      // this.photos = await Axios.get(`${api/canprofile2}/${this.getAllCandidate.avatar}`).then(
      this.photos = await Axios.get("https://picsum.photos/v2/list").then(
        (Response) => {
          console.log(Response.data);
          // this.photos = Response.data;
          return Response.data;
        }
      );
    },
    
    checkStudentAlreadyVote() {
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
            
            // this.$router.push("/candidateDetail");
            // this.$router.go();
          }else{
            this.checkStudentAlreadyCandidate();            
          } 
        })
        .catch((e) => {
          console.log(e);
        });
    },
    checkStudentAlreadyCandidate(){
      let user = JSON.parse(localStorage.getItem("user"));
      let body = {
        studentId: user.studentId
      }
      Api.post("/api/canp/student",JSON.stringify(body))
        .then((res) => {
          if(res.data === true){
            alert("นักศึกษาเป็นผู้ลงสมัคร");
          }else{
            this.$router.push("/vote")
          }
        })
        .catch((e) => {
          console.log(e);
        })
    }
    // async save2() {},
  },
  mounted() {
    // this.getPhotos();
    this.clearAlert();
    this.getAllCandidate();
  },
};
</script>

<style>
</style>
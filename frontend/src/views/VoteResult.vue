<template>
  <v-container>
    <div>
      <v-row>
        <v-col
          cols="12"
          md="6"
          sm="6"
          v-for="i in scores"
          v-bind:key="i.candidateProfile.can_id"
        >
          <v-card width="700" height="auto">
            <v-card-title primary-title>
              <font size="auto"
                >หมายเลขผู้สมัคร: {{ i.candidateProfile.c_number }}</font
              >
            </v-card-title>

            <v-card-text class="text-center">
              <v-img
                v-if="i.candidateProfile.avatar"
                :src="
                  'http://localhost:9000/files/' + i.candidateProfile.avatar
                "
              ></v-img>
              <v-progress-circular
                v-if="!i.candidateProfile.avatar"
                indeterminate
                color="primary"
              ></v-progress-circular>
            </v-card-text>

            <v-card-text>
              <font color="black">
                <pre>ชื่อ-นามสกุล: {{ i.candidateProfile.title_name}}{{ i.candidateProfile.c_name }}</pre>
                <pre>Gpax: {{ i.candidateProfile.grade }}</pre>
                <pre>กิจกรรมที่เข้าร่วม: {{ i.candidateProfile.archivement }}</pre>
              </font>
            </v-card-text>
            <v-card-text>
              <div v-if="checktime()">
                <font color="blue">
                  <pre><h1>คะแนนที่ได้: {{i.score}} </h1> </pre>
                </font>
              </div>
              <div v-else>
                <font color="blue">
                  <pre><h1>คะแนนที่ได้: ยังไม่ถึงเวลาประกาศผล </h1> </pre>
                </font>
              </div>
              <v-divider></v-divider>
              <v-dialog v-model="dialog2" width="500">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="red lighten-2"
                    outlined
                    dark
                    v-bind="attrs"
                    v-on="on"
                  >
                    ตรวจสอบการลงคะแนน
                  </v-btn>
                </template>

                <v-card>
                  <v-card-title class="headline grey lighten-2">
                    ตรวจสอบการลงคะแนน
                  </v-card-title>

                  <v-card-text>
                    <v-text-field
                      label="กรอกรหัส"
                      name="hashvalue"
                      v-model="userhash"
                    ></v-text-field>
                  </v-card-text>

                  <v-divider></v-divider>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      class="ma-2"
                      outlined
                      color="primary"
                      dark
                      @click="checkStudentHash(userhash)"
                      >ตรวจสอบ</v-btn
                    >
                    <v-btn color="primary" text @click="dialog2 = false">
                      ปิด
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </div>
  </v-container>
</template>

<script>
import Api from "../Api";
export default {
  name: "Vote",
  data() {
    return {
      candidate: [],
      votes: [],
      dialog2: false,
      userhash: undefined,
      scores: [],
    };
  },
  methods: {
    async getAllCandidate() {
      await Api.get("/api/canprofile")
        .then((response) => {
          this.candidate = response.data;
          // console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch((e) => {
          console.log(e);
        });
    },
    async checkStudentHash(userhash) {
      await Api.get(
        `/api/vote/getCorrectStudentHashByStudentHash?USER_HASH=${userhash}`
      )
        .then((res1) => {
          console.log(res1);
          if (res1.data === true) {
            alert("ผลการลงคะแนนถูกต้อง");
          } else {
            alert("ผลการลงคะแนนไม่ถูกต้อง");
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    checktime(){
      return false;
    },

    async getScore() {
      await Api.get("/api/score")
        .then((res2) => {
          this.scores = res2.data;
          console.log(this.scores);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  mounted() {
    this.getAllCandidate();
    this.getScore();
    this.checktime();
  },
};
</script>

<style>
h1 {
  font-size: 20px;
}
</style>
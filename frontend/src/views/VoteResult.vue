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
              <font size="auto" style="font-family: 'SUT Regular'">
                <h3>หมายเลขผู้สมัคร: {{ i.candidateProfile.c_number }}</h3></font>
            </v-card-title>

            <v-card-text class="text-center" >
              <v-img
                v-if="i.candidateProfile.avatar"
                :src="
                  'http://localhost:9000/files/' + i.candidateProfile.avatar
                "
              width="536px" height="560px"></v-img>
              <v-progress-circular
                v-if="!i.candidateProfile.avatar"
                indeterminate
                color="primary"
              ></v-progress-circular>
            </v-card-text>

            <v-card-text>
              <font color="black"><p>
                <pre style="font-family: 'SUT Regular'">ชื่อ-นามสกุล: {{ i.candidateProfile.title_name}}{{ i.candidateProfile.c_name }}</pre>
                <pre style="font-family: 'SUT Regular'">Gpax: {{ i.candidateProfile.grade }}</pre>
                <pre style="font-family: 'SUT Regular'">กิจกรรมที่เข้าร่วม: {{ i.candidateProfile.archivement }}</pre></p>
              </font>
            </v-card-text>
            <v-card-text>
              <div v-if="checktime()">
                <font color="#F16529">
                  <pre style="font-family: 'SUT Regular'" ><h2>คะแนนที่ได้: {{i.score}} </h2> </pre>
                </font>
              </div>
              <div v-else>
                <font color="#F16529">
                  <pre style="font-family: 'SUT Regular'"><h2>คะแนนที่ได้: ยังไม่ถึงเวลาประกาศผลคะแนน </h2> </pre>
                </font>
              </div>
              
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
      scores: [],
      date_time_election: [],
      // election_day: null,
      time_start: null, //เริ่มเวลาเท่าไหร่
      time_end: null, //หมดเวลาเท่าไหร่
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

    async getAllTime() {
      return new Promise((success, err) => {
        Api
          .get("/api/timeshow")
          .then((response) => {
            this.date_time_election = JSON.parse(JSON.stringify(response.data));
            console.log(this.date_time_election[0]);
            console.log(JSON.parse(JSON.stringify(response.data)));
            console.log(
              "get time_start = " + this.date_time_election[0].time_start
            );
            success(true);
          })
          .catch((e) => {
            console.log(e);
            err(false);
          });
      });
    },

    checktime(){
      var now = new Date();
      console.log(now);
      //เซตวันเวลาเริ่มต้น
      // var electionDateStart = new Date(this.date_time_election[0].election_day + " " + this.date_time_election[0].time_start);
      // console.log(electionDateStart);
      //เซตวันเวลาสิ้นสุด
      var electionDateEnd = new Date(this.date_time_election[0].election_day + " " + this.date_time_election[0].time_end);
      // console.log(electionDateEnd);

      //เช็คอยู่ในช่วงเวลาไหม
      if (now >= electionDateEnd ) {
        // console.log("อยู่ช่วงเวลาลงคะแนน");
        return true;
      }else{
        // console.log("ไม่อยู่ในช่วงเวลาลงคะแนน");
        // alert("ไม่ได้อยู่ในช่วงเวลาทำการ\n"+"เริ่ม: "+electionDateStart.toLocaleString("th-TH") + " สิ้นสุด: "+ electionDateEnd.toLocaleString("th-TH"));
        return false;
      }
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
    this.getAllTime();
    this.getAllCandidate();
    this.getScore();
    this.checktime();
  },
};
</script>

<style>
p {
  font-size: 1.5em;
}
/* h3 {
  font-size: 2.0em;
} */


</style>
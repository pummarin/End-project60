<template>
  <v-row align="center" justify="center">
    <v-col cols="12" sm="8" md="10">
      <template>
        <v-row align="center" justify="center">
          <v-img max-height="650px" max-width="300px" src="SUTlogoBIG.jpg">
            <template v-slot:placeholder>
              <v-row class="fill-height ma-0" align="center" justify="center">
                <v-progress-circular
                  indeterminate
                  color="grey lighten-5"
                ></v-progress-circular>
              </v-row>
            </template>
          </v-img>
        </v-row>

        <font color="#E44D26">
          <h1 class="text-center" style="font-family: 'Love'">
            Welcome to Suranaree University of Technology's Election
          </h1>
        </font>
        <div class="text-decoration-underline" style="font-family: 'Love'">
          <font color="#000000">
            <h3 class="text-left">UPCOMING EVENTS</h3></font
          >
        </div>
        <v-card elevation="2" shaped max-width="400" color="#EBEBEB">
          <v-card-text>
            <font color="#E44D26">
            <h2 style="font-family: 'Love'">Student Council</h2>
            </font>
            <font color="#E44D26">
              <br />
              <h1 style="font-family: 'Love'">
                {{ date_time_election[0].election_day }}
              </h1>
            </font>
            <!-- <p>adjective</p> --><br />
            <font color="#000000">
              <h2 style="font-family: 'Love'">
                Start: {{ date_time_election[0].time_start }} - End: {{ date_time_election[0].time_end }}
              </h2>
            </font>
            <font color="#000000">
              <h2  style="font-family: 'Angsana New'">
               ทดสอบ
              </h2>
            </font>
          </v-card-text>
        </v-card>
      </template>
    </v-col>
  </v-row>
</template>

<script>
import api from "../Api.js";

export default {
  name: "home",
  data() {
    return {
      date_time_election: [],
    };
  },
  methods: {
    async getAllTime() {
      return new Promise((success, err) => {
        api
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
  },
  mounted() {
    this.getAllTime();
  },
};
</script>
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
        <!-- <v-card class="mx-auto" shaped max-width="2000">
          <v-img
            class="white--text align-end"
            max-height="500"
            max-width="900"
            src="back1.jpg"
          >
            <template v-slot:placeholder>
              <v-row class="fill-height ma-0" align="center" justify="center">
                <v-progress-circular
                  indeterminate
                  color="#000000"
                ></v-progress-circular>
              </v-row>
            </template>
          </v-img>

          <v-row justify="center">
            <v-col cols="6" sm="6"  md="8">
              <v-btn rounded @click="handleCreateCandidate" color="#F16529"
                >Add Candidate </v-btn
              >
              &nbsp;
              <v-btn rounded @click="handleShowCandidate" color="#F16529"
                >Candidate Management</v-btn
              >
              &nbsp;
              <v-btn rounded @click="handleTime" color="#F16529">Set Time</v-btn>
            </v-col>
          </v-row>
        </v-card> -->
      </template>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: "AdminHomepage",
  data() {
    return {};
  },
  methods: {
    handleCreateCandidate() {
      this.$router.push("/candidateprofile");
    },
    handleShowCandidate() {
      this.$router.push("/candidatelist");
    },
    handleTime() {
      this.$router.push("/settime");
    },
  },
  mounted() {},
};
</script>

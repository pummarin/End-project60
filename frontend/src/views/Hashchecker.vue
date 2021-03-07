<template>
  <v-container>
    <v-row justify="space-around">
      <v-card width="100%">
        <v-img
          height="200px"
          src="https://cdn.pixabay.com/photo/2020/07/12/07/47/bee-5396362_1280.jpg"
        >
          <v-app-bar flat color="rgba(0, 0, 0, 0)">
            <v-spacer></v-spacer>
          </v-app-bar>

          <v-card-title class="white--text mt-8">
            <p class="ml-3">ตรวจสอบความถูกต้องของข้อมูล</p>
          </v-card-title>
        </v-img>

        <v-card-text>
          <div v-if="votes.length === 0">
            <p>Not Have Vote</p>
          </div>
          <v-timeline v-if="votes" align-top dense>
            <v-timeline-item
              v-for="vote in votes"
              :key="vote.id"
              :color="vote.correct ? 'green' : 'red'"
              small
            >
              <div>
                <div class="font-weight-normal">
                  <strong>{{ vote.id }}</strong> @{{ vote.voteTime }}
                </div>
                <div>Block hash : {{ vote.hash }}</div>
                <div>Previous hash: {{ vote.prevHash }}</div>
              </div>
            </v-timeline-item>
          </v-timeline>
        </v-card-text>
      </v-card>
    </v-row>
  </v-container>
</template>

<script>
import Api from "../Api";
export default {
  data() {
    return {
      votes: [],
    };
  },
  methods: {
    async getAllVote() {
      await Api.get(`/api/vote/getCheckedAllBlock`)
        .then((res) => {
          this.votes = res.data;
          console.log(JSON.parse(JSON.stringify(this.votes)));
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },

  mounted() {
    this.getAllVote();
  },
};
</script>

<style>
</style>
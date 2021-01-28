<template>
  <v-container>
    <v-row justify="space-around">
      <v-card width="100%">
        <v-img
          height="200px"
          src="https://cdn.pixabay.com/photo/2020/07/12/07/47/bee-5396362_1280.jpg"
        >
          <v-app-bar
            flat
            color="rgba(0, 0, 0, 0)"
          >
            <v-spacer></v-spacer>

            <v-btn
              color="white"
              icon
            >
              <v-icon>mdi-dots-vertical</v-icon>
            </v-btn>
          </v-app-bar>

          <v-card-title class="white--text mt-8">
            
            <p class="ml-3">
              ตรวจสอบความถูกต้องของข้อมูล
            </p>
          </v-card-title>
        </v-img>

        <v-card-text>
         
          <v-timeline
            align-top
            dense
          >
            <v-timeline-item
              v-for="message in messages"
              :key="message.time"
              :color="color"
              small
            >
              <div>
                <div class="font-weight-normal">
                  <strong>{{ message.from }}</strong> @{{ message.time }}
                </div>
                <div>{{ message.message }}</div>
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
      color:'green',
      messages: [
        {
          from: 'You',
          message: `Sure, I'll see you later.`,
          time: '10:42am',
          color: 'deep-purple lighten-1',
        },
        {
          from: 'John Doe',
          message: 'Yeah, sure. Does 1:00pm work?',
          time: '10:37am',
          color: 'dark green',
        },
        {
          from: 'You',
          message: 'Did you still want to grab lunch today?',
          time: '9:47am',
          color: 'red',
        },
      ],
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
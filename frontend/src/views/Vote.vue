
<template>
  <v-col cols="12" md="8" sm="6">    
    <v-alert type="success" dismissible v-model="alertSuccess">Voting Finished ...</v-alert>
    <div>
      <div>
        <v-col v-for="n in 5" :key="n">
          <v-card width="700" height="auto">
            <v-card-title primary-title>
              <font size="auto">Number: {{photos[n].id}}</font>
            </v-card-title>
            <v-card-text>
              <v-img :src="photos[n].download_url"></v-img>
            </v-card-text>
            <v-card-text>
              <font color="black">
                <pre>Candidate Name: {{photos[n].author}}</pre>
                <pre>Gpax: {{test}}</pre>
              </font>
            </v-card-text>

            <v-card-actions>
              <div>
                <v-btn class="ma-2" outlined color="indigo" dark  @click="Leo">ลงคะแนน</v-btn>
              </div>
              <v-spacer></v-spacer>
              <v-btn @click="detail" text>Details</v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </div>
    </div>
  </v-col>
</template>

<script>
import Axios from "axios";
export default {
  name: "Vote",
  data() {
    return {
      photos: [],
      test: "3.45",
      detail: [],
      alertSuccess: false
    };
  },
  methods: {
    getPhotos() {
      Axios.get("https://picsum.photos/v2/list").then((Response) => {
        console.log(Response.data);
        this.photos = Response.data;
      });
    },
    Leo() {
      this.alertSuccess = true;
    },
    clearAlert(){
      this.alertSuccess = false;
    }
  },
  mounted() {
    this.getPhotos();
    this.clearAlert();
  },
};
</script>

<style>
</style>
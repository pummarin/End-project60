
<template>
  <v-col cols="10" md="6" sm="6">
    <div>
      <div>
        <v-container grid-list-md>
          <v-col v-for="i in candidate" v-bind:key="i.can_id">
            <v-card width="650" height="auto">
              <v-card-title primary-title>
                <font size="auto">Number: {{ i.c_number }}</font>
              </v-card-title>

              <v-card-text class="text-center">
                <v-img v-if="photos[29]" :src="photos[29].download_url"></v-img>
                <v-progress-circular
                  v-if="!photos[29]"
                  indeterminate
                  color="primary"
                ></v-progress-circular>
              </v-card-text>

              <v-card-text>
                <font color="black">
                  <pre>Name: {{ i.c_name }}</pre>
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
                          to="/vote"
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
                                <pre>ชื่อ-นามสกุล: {{ i.c_name }}</pre>
                              </v-card-text>
                              
                            </v-list-item-content>
                          </v-list-item>
                          <v-list-item>
                            <v-list-item-content>
                              <v-list-item-title>Password</v-list-item-title>
                              <v-list-item-subtitle
                                >Require password for purchase or use password
                                to restrict purchase</v-list-item-subtitle
                              >
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
    async save2() {},
  },
  mounted() {
    this.getPhotos();
    this.clearAlert();
    this.getAllCandidate();
  },
};
</script>

<style>
</style>
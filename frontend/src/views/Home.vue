<template>
  <div class="library">
    <center>
      <h2 class="cult">Welcome to the Cult</h2>
    </center>
  </div>
</template>

<script>
import http from "../http-common";
import cultcontrol from '../thecult/cultcontrol'
export default {
  name: "Home",
  components: {},
  data() {
    return {
      showMenu: true
    };
  },
  methods: {
    getGroups() {
      http
        .get("http://localhost:8181/private/getUserGroup", {
          headers: {
            Authorization: this.$store.state.token
          },
          params: {
            "username" : this.$store.state.user
          }
        })
        .then(resp => {
          if (resp.data.hasOwnProperty("WARN")) {
            this.$toast.warning(cultcontrol.getTranslationOf(resp.data.WARN));
          } else {
            let got = resp.data.RESULT;
            let groupIDs = []
            for (let i = 0; i < got.length; i++) {
              const element = got[i];
              groupIDs.push(element.id)
              
            }
            this.$store.dispatch("storeGroups", groupIDs)
          }
        })
        .catch(err => {
          this.$toast.error(err);
        });
    },
    getUserData(){
      http.get("http://localhost:8181/private/checkUser", 
      {
        headers : {
          "Authorization" : this.$store.state.token
        },
        params : {
          user : this.$store.state.user
        }
      })
      .then(resp => {
        let res = resp.data.RESULT
        this.$store.dispatch("storeId", res.id);
      })
      .catch(err => this.$toast.error(err))
    }
  },
  mounted() {
    this.getGroups();
    this.getUserData();
  }
};
</script>

<style>
@font-face {
  font-family: D-Gothic;
  src: url("../assets/fonts/Deutsch.ttf");
}

body,
html {
  background-image: url("../assets/images/background.jpg");
}

h2.cult {
  font-family: D-Gothic;
  color: wheat;
  font-size: 128px;
}
</style>
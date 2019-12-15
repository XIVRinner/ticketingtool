<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <div class="modal-header">
            <slot name="header">
              <h3>{{translate.header}} {{title}}</h3>
            </slot>
          </div>

          <div class="modal-body">
            <slot name="body">
              <table class="table">
                <tbody>
                  <tr>
                    <td>{{translate.short}}</td>
                    <td>{{translate.resp}}</td>
                    <td>{{translate.sev}}</td>
                  </tr>
                  <tr>
                    <td>
                      <input type="text" v-model="ticketData.short" size="30" @change="chtitle()" />
                    </td>
                    <td>
                      <input type="text" :placeholder="translate.user" v-model="ticketData.user" />
                    </td>
                    <td width="250">
                      <v-select :options="severity" v-model="ticketData.severity" />
                    </td>
                  </tr>
                  <tr>
                    <td>{{translate.long}}</td>
                    <td>{{translate.group}}</td>
                    <td>{{translate.status}}</td>
                  </tr>
                  <tr>
                    <td>
                      <textarea rows="4" v-model="ticketData.long" />
                    </td>
                    <td>
                      <v-select :options="group" v-model="ticketData.group" />
                    </td>
                    <td>
                      <v-select :options="status" v-model="ticketData.status" />
                    </td>
                  </tr>
                </tbody>
              </table>
            </slot>
          </div>

          <div class="modal-footer">
            <slot name="footer">
              <button class="btn btn-success" @click="sendTicket">{{translate.create}}</button>
              <button class="btn btn-warning" @click="closeCreator()">Close</button>
            </slot>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import cultcontrol from "../thecult/cultcontrol";
import http from "../http-common";
export default {
  name: "ChangeCreator",
  data() {
    return {
      translate: {
        header: "",
        user: "",
        resp: "",
        short: "",
        long: "",
        sev: "",
        status: "",
        create : "",
        
      },
      severity: [],
      status: [],
      ticketData: {
        user: "",
        severity: "",
        long: "",
        group: "",
        status: "",
        short: "",
        userid : ""
      },
      group: [],
      title: ""
    };
  },
  methods: {
    closeCreator() {
      this.$router.push("/changes");
    },
    getSeverities() {
      http
        .get("http://localhost:8181/private/getSeverities", {
          headers: { Authorization: this.$store.state.token }
        })
        .then(resp => {
          let sevs = resp.data.RESULT;
          for (let i = 0; i < sevs.length; i++) {
            const element = sevs[i];
            const element_name = sevs[i].name;
            element.name = cultcontrol.getTranslationOf(element_name);
            element.label = cultcontrol.getTranslationOf(element_name);
            this.severity.push(element);
          }
        })
        .catch(err => this.$toast.error(err));
    },
    getStatuses() {
      http
        .get("http://localhost:8181/private/getChangeStatuses", {
          headers: { Authorization: this.$store.state.token }
        })
        .then(resp => {
          let stat = resp.data.RESULT;
          for (let i = 0; i < stat.length; i++) {
            const element = stat[i];
            const element_name = stat[i].name;
            element.name = cultcontrol.getTranslationOf(element_name);
            element.label = cultcontrol.getTranslationOf(element_name);
            this.status.push(element);
          }
        })
        .catch(err => this.$toast.error(err));
    },
    getGroups() {
      http
        .get("http://localhost:8181/private/getAllGroup", {
          headers: { Authorization: this.$store.state.token }
        })
        .then(resp => {
          let grp = resp.data.RESULT;
          for (let i = 0; i < grp.length; i++) {
            const element = grp[i];
            element.label = element.name;

            this.group.push(element);
          }
        })
        .catch(err => this.$toast.error(err));
    },
    queryUser() {
      console.log(this.ticketData.user);
    },
    chtitle() {
      this.title = "->" + this.ticketData.short;
    },
    sendTicket(){


        let dataForm = this.ticketData;
        if(dataForm.short.trim() === "" || dataForm.group === "") {
            this.$toast.warning("Review your formula!");
            return;
        }

        let translatedTicket = {
            userid : this.getUserId(dataForm.short),
            groupid : dataForm.group.id,
            severity : dataForm.severity.id,
            status : dataForm.status.id,
            shortDescription : dataForm.short,
            longDescription : dataForm.long
        };

        http.post("http://localhost:8181/private/createChange", translatedTicket ,{ headers : {
            "Authorization" : this.$store.state.token
        }})
        .then(resp => {this.$router.push('/changes'); resp.data = null; })
        .catch(err => this.$toast.error(err));
    },
    getUserId(name){
      if(name.trim() === ""){
          this.$toast.warning("Ticket created on your name.");
          return this.$store.state.userData.id;
      }

      http.get("http://localhost:8181/private/checkUser", 
      {
        headers : {
          "Authorization" : this.$store.state.token
        },
        params : {
          user : name
        }
      })
      .then(resp => {
        let res = resp.data.RESULT
        

        return res.id;
      })

      
      this.$toast.warning("That user is not found! Change created on your name.")

      return this.$store.state.userData.id;
    }
  },
  mounted() {
    this.translate.header = cultcontrol.getTranslationOf("CREATE_CH");
    this.translate.user = cultcontrol.getTranslationOf("USER");
    this.translate.resp = cultcontrol.getTranslationOf("RESPONSIBLE");
    this.translate.short = cultcontrol.getTranslationOf("SHORT_TI");
    this.translate.long = cultcontrol.getTranslationOf("LONG_TI");
    this.translate.sev = cultcontrol.getTranslationOf("SEVERITY");
    this.translate.status = cultcontrol.getTranslationOf("STATUS");
    this.translate.group = cultcontrol.getTranslationOf("GROUP");
    this.translate.create = cultcontrol.getTranslationOf("CREATE_TI");
    this.getSeverities();
    this.getStatuses();
    this.getGroups();
  }
};
</script>

<style scoped>
.modal-mask {
  position: fixed;
  z-index: 1000;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: table;
  transition: opacity 0.3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 930px;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
  font-family: Helvetica, Arial, sans-serif;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}

.modal-default-button {
  float: right;
}

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>
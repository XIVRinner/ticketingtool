<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <div class="modal-header">
            <slot name="header">
              <h3>Edit {{ticket.name}}</h3>
            </slot>
          </div>

          <div class="modal-body">
            <slot name="body">
              <table class="table">
                <tbody>
                  <tr class="table-striped">
                    <td>{{translate.group}}</td>
                    <td>{{translate.customer}}</td>
                    <td>{{translate.sev}}</td>
                  </tr>
                  <tr>
                    <td>{{ticket.group}}</td>
                    <td>{{ticket.customer}}</td>
                    <td><v-select :options="severity" :selected="ticket.severity" /></td>
                  </tr>
                  <tr>
                    <td>{{translate.long}}</td>
                    <td>{{translate.resolution}}</td>
                    <td>{{translate.status}}</td>
                  </tr>
                  <tr>
                    <td>
                      <textarea rows="4" v-model="ticket.long" />
                    </td>
                    <td>
                      <textarea rows="4" v-model="ticket.resolution" />
                    </td>
                    <td>
                      <v-select :options="status" v-model="ticket.status" />
                    </td>
                  </tr>
                </tbody>
              </table>
            </slot>
          </div>

          <div class="modal-footer">
            <slot name="footer">
              <button class="btn btn-success" @click="$emit('close')">Make Changes to Spell</button>
              <button class="btn btn-danger" @click="$emit('close')">Cast on Target!</button>
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
  name: "TicketEditor",
  props: ["ticket"],
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
        create: "",
        group: "",
        resolution : ""
      },
      severity : [],
      status : []
    };
  },
  async mounted() {
    this.getTicketDetails();
    this.getSeverities();
    this.getStatuses();
    this.translate.header = cultcontrol.getTranslationOf("CREATE_TI");
    this.translate.user = cultcontrol.getTranslationOf("USER");
    this.translate.resp = cultcontrol.getTranslationOf("RESPONSIBLE");
    this.translate.short = cultcontrol.getTranslationOf("SHORT_TI");
    this.translate.long = cultcontrol.getTranslationOf("LONG_TI");
    this.translate.sev = cultcontrol.getTranslationOf("SEVERITY");
    this.translate.status = cultcontrol.getTranslationOf("STATUS");
    this.translate.group = cultcontrol.getTranslationOf("GROUP");
    this.translate.create = cultcontrol.getTranslationOf("CREATE_TI");
    this.translate.customer = cultcontrol.getTranslationOf("CUSTOMER");
    this.translate.resolution = cultcontrol.getTranslationOf("RESOLUTION");
  },
  methods: {
    getTicketDetails() {
      http
        .get("http://localhost:8181/private/getTicketById", {
          headers: { Authorization: this.$store.state.token },
          params: {
            id: this.ticket.id
          }
        })
        .then(resp => {
          let tickets = resp.data.RESULT;
          this.ticket.group = tickets.group.name;
          this.ticket.customer =
            tickets.customer.name + "(" + tickets.customer.org + ")";
        })
        .catch(err => this.$toast.error(err));
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
        .get("http://localhost:8181/private/getTicketStatuses", {
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
  width: 1250px;
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
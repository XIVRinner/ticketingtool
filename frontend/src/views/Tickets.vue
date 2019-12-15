<template>
  <div>
    <table class="table table-hover table-dark">
      <thead>
        <tr>
          <th v-for="i in columns" :key="i.key">{{i.title}}</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="t in ticketData" :key="t.id" @click="selectRow(t)">
            <td>{{t.id}}</td>
            <td>{{t.name}}</td>
            <td>{{t.resp}}</td>
            <td>{{t.open}}</td>
            <td>{{t.sev}}</td>
        </tr>
      </tbody>
    </table>

    <TicketEditor :ticket="selectedTicket" v-if="showModal" @close="showModal = false"/>
  </div>
</template>

<script>
import cultcontrol from "../thecult/cultcontrol";
import http from "../http-common"
import TicketEditor from '../components/TicketEditor'
export default {
  name: "Tickets",
  components : {
    TicketEditor
  },
  data() {
    return {
      columns: [
        {
          title: cultcontrol.getTranslationOf("NUMBER"),
          key: "no"
        },
        {
          title: cultcontrol.getTranslationOf("TITLE"),
          key: "ti"
        },
        {
          title: cultcontrol.getTranslationOf("RESPONSIBLE"),
          key: "re"
        },
        {
          title: cultcontrol.getTranslationOf("DATE_OPENED"),
          key: "op"
        },
        {
          title: cultcontrol.getTranslationOf("SEVERITY"),
          key: "sv"
        }
      ],
      ticketData: [],
      selectedTicket : null,
      showModal : false
    };
  },
  methods: {
    getTickets() {
      http
        .get("http://localhost:8181/private/getTicketsByUser", {
          headers: { Authorization: this.$store.state.token },
          params: {
            user: this.$store.state.userData.id
          }
        })
        .then(resp => {
          let tickets = resp.data.RESULT;
          console.log(tickets);

          for (let i = 0; i < tickets.length; i++) {
              const element = tickets[i];
              const obj = {
                  id : element.id,
                  name : element.short,
                  resp : element.responsilbe.user,
                  open : element.created,
                  sev : cultcontrol.getTranslationOf(element.severity.name)
                }
              this.ticketData.push(obj);
          }
        })
        .catch(err => this.$toast.error(err));
    },
    selectRow(t){
      this.selectedTicket = t;
      this.showModal = true;
    }
  },
  mounted() {

      this.getTickets()
  }
};
</script>

<style>
</style>
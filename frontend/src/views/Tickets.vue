<template>
  <div>
      <table class="table table-hover table-dark">
          <thead>
              <tr>
                  <th v-for="i in columns" :key="i.key">{{i.title}}</th>
              </tr>
          </thead>
          <tbody>
          </tbody>
      </table>
  </div>
</template>

<script>
export default {
    name : "Tickets",
    data(){
        return {
            columns : 
            [
                {
                    title : "Number",
                    key : "no"
                },
                {
                    title : "Title",
                    key : "ti"
                },
                {
                    title : "Responsible",
                    key : "re"
                },
                {
                    title : "Date Opened",
                    key : "op"
                },
                {
                    title : "Severity",
                    key : "sv"
                }
            ]
        }
    },
    methods : {

    },
    mounted() {

    }


}
</script>

<style>

</style>
<template>
  <div id="app">
    <div id="demo" :class="[{'collapsed' : collapsed}]">
      <router-view />
      <sidebar-menu
        :menu="menu"
        :collapsed="collapsed"
        :show-one-child="true"
        @toggle-collapse="onToggleCollapse"
        @item-click="onItemClick"
        v-if="$store.state.showMenu"
      />
    </div>
  </div>
</template>
<script>
import cultcontrol from "./thecult/cultcontrol";
export default {
  name: "App",
  data() {
    return {
      show: "H",
      texts: {
        TICKETS: "",
        CHANGES: ""
      },
      menu: [
        {
          header: true,
          title: "The Cult",
          hiddenOnCollapse: true
        },
        {
          href: "/tickets",
          title: cultcontrol.getTranslationOf("TICKETS"),
          icon: "fa fa-code"
        },
        {
          href: "/changes",
          title: cultcontrol.getTranslationOf("CHANGES"),
          icon: "fa fa-code"
        }
      ],
      collapsed: false
    };
  },
  methods: {
    onToggleCollapse(collapsed) {
      this.collapsed = collapsed;
    },
    onItemClick(event, item) {
      item.smth = null;
    }
  },
  mounted() {
    
  }
};
</script>

<style>
</style>

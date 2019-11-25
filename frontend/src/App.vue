<template>
  <div id="app">
    <div id="demo" :class="[{'collapsed' : collapsed}]">
      
      <sidebar-menu
        v-if="$store.state.showMenu"
        :menu="menu"
        :collapsed="collapsed"
        :show-one-child="true"
        @toggle-collapse="onToggleCollapse"
        @item-click="onItemClick"
        
      />
      <router-view />
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
      collapsed: true
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

<style scoped lang="scss">
@import url('https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600');
body,
html {
  margin: 0;
  padding: 0;
}
body {
  font-family: 'Source Sans Pro', sans-serif;
  font-size: 18px;
  background-color: #f2f4f7;
  color: #262626;
}
#demo {
  padding-left: 350px;
}
#demo.collapsed {
  padding-left: 50px;
}
.demo {
  padding: 50px;
}
.container {
  max-width: 900px;
}
pre {
  font-family: Consolas, monospace;
  color: #000;
  background: #fff;
  border-radius: 2px;
  padding: 15px;
  line-height: 1.5;
  overflow: auto;
}
</style>

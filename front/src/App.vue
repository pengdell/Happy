<template>
  <div id="app">
    <navi-bar />
    <router-view />
    <footer-nav />
  </div>
</template>

<script>
import NaviBar from "@/components/layout/HeaderNaviBar.vue";
import FooterNav from "@/components/layout/FooterNav.vue";

export default {
  name: "App",
  components: {
    NaviBar,
    FooterNav,
  },
};
</script>

<style>
a {
  text-decoration: none;
}

a:hover {
  text-decoration: none;
  font-weight: bold;
  color: chartreuse;
}

a.router-link-exact-active {
  color: #3aa4ca;
}
</style>

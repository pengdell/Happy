<template>
  <b-container class="bv-example-row text-center">
    <b-row>&nbsp;</b-row>
    <h3>
      <b-icon icon="house-door-fill" animation="fade"></b-icon> HappyHouse
    </h3>
    <b-row>
      <b-col>
        <house-search-bar></house-search-bar>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="6" align="left">
        <house-list />
      </b-col>
      <b-col cols="6">
        <house-detail />
      </b-col>
    </b-row>

    <!--지도-->
    <b-row class="mb-2 mt-1">
      <b-col><KakaoMap /></b-col>
    </b-row>
    <!--지도-->
  </b-container>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";
import KakaoMap from "@/components/house/KaokaoMap.vue";

import { mapMutations } from "vuex";

export default {
  name: "HouseView",
  components: {
    HouseSearchBar,
    HouseList,
    HouseDetail,
    KakaoMap,
  },
  created() {
    this.CLEAR_HOUSE_LIST();
  },
  methods: {
    ...mapMutations(["CLEAR_HOUSE_LIST"]),
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
</style>

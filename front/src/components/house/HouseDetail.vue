<template>
  <div>
    <b-container v-if="house" class="bv-example-row">
      <b-row>&nbsp;</b-row>
      <b-row>
        <b-col>
          <b-alert show variant="primary"
            >아파트명 : {{ house.아파트 }}
          </b-alert></b-col
        >
      </b-row>

      <b-row>
        <b-col>
          <b-alert show variant="primary"
            >거래일자 : {{ house.년 }}년 {{ house.월 }}월 {{ house.일 }}일
          </b-alert>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-alert show variant="primary"
            >전용면적 : {{ house.전용면적 }}㎡
          </b-alert>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-alert show variant="primary">층수 : {{ house.층 }}층</b-alert>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-alert show variant="primary"
            >거래금액 :
            {{
              (parseInt(house.거래금액.replace(",", "")) * 10000) | price
            }}원</b-alert
          >
        </b-col>
      </b-row>
    </b-container>
    <b-container v-else>
      <!--지도-->
      <!-- <b-row class="mb-2 mt-1">
        <b-col><KakaoMap /></b-col>
      </b-row> -->
      <!--지도-->
    </b-container>
  </div>
</template>

<script>
//import KakaoMap from "./KaokaoMap.vue";
import { mapState } from "vuex";
import { mapMutations } from "vuex";

export default {
  name: "HouseDetail",
  components: {
    //KakaoMap,
  },
  created() {
    this.CLEAR_HOUSE();
  },
  methods: {
    ...mapMutations(["CLEAR_HOUSE"]),
  },
  computed: {
    ...mapState(["house", "houses"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style></style>

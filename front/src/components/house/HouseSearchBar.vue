<template>
  <b-row class="mt-4 mb-4 text-center">
    <!-- <b-col class="sm-3">
      <b-form-input
        v-model.trim="dongCode"
        placeholder="동코드 입력...(예 : 11110)"
        @keypress.enter="sendKeyword"
      ></b-form-input>
    </b-col>
    <b-col class="sm-3" align="left">
      <b-button variant="outline-primary" @click="sendKeyword">검색</b-button>
    </b-col> -->
    <b-col class="sm-3">
      <b-form-select
        v-model="sidoCode"
        :options="sidos"
        @change="gugunList"
      ></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select
        v-model="gugunCode"
        :options="guguns"
        @change="dongList"
      ></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select
        v-model="dongCode"
        :options="dongs"
        @change="searchApt"
      ></b-form-select>
    </b-col>
    <b-icon
      class="h"
      icon="heart"
      v-if="heart == 0"
      @click="insertInterest"
    ></b-icon>
    <b-icon
      class="h"
      icon="heart-fill"
      v-else-if="heart == 1"
      @click="removeInterest"
    ></b-icon>
  </b-row>
</template>

<script>
import http from "@/api/http";
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      heart: -1, //-1은아직 동을 안누름, 0이면 관심지역 아직 등록안함, 1임면 관심지역으로 등록했음
      dongName: null,
    };
  },
  computed: {
    ...mapState([
      "sidos",
      "guguns",
      "dongs",
      "houses",
      "house",
      "gugunName",
      "sidoName",
      "interests",
      "loginUser",
    ]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(["getSido", "getGugun", "getDong", "getHouseList"]),
    ...mapMutations([
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_HOUSE_LIST",
    ]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      this.$store.dispatch("setChargers", this.sidoCode); //시도가 바뀔때마다 충전소 리스트 갱신

      for (let i = 0; i < this.sidos.length; i++) {
        if (this.sidos[i].value == this.sidoCode) {
          this.$store.dispatch("setSidoName", this.sidos[i].text);
        }
      } //for i

      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
      this.heart = -1;
    }, //gugunList

    dongList() {
      this.$store.dispatch("setFoods", this.gugunCode); //구군이 바뀔때마다 음식점 리스트 갱신
      this.$store.dispatch("setServices", this.gugunCode); //구군이 바뀔때마다 의료시설 리스트 갱신

      for (let i = 0; i < this.guguns.length; i++) {
        if (this.guguns[i].value == this.gugunCode) {
          const addr = {
            sidoName: this.sidoName,
            gugunName: this.guguns[i].text.trim(),
          };
          this.$store.dispatch("setGugunName", addr);
        }
      } //for i

      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode)
        // this.getDong(this.gugunCode);
        this.getDong(this.gugunName);

      this.heart = -1;
    },

    searchApt() {
      //하트 표시 결정
      this.heart = 0;
      for (let i = 0; i < this.dongs.length; i++) {
        if (this.dongs[i].value == this.dongCode) {
          this.dongName = this.dongs[i].text;
          break;
        }
      } //for j

      for (let i = 0; i < this.interests.length; i++) {
        if (this.interests[i].dongName == this.dongName) {
          this.heart = 1;
          break;
        }
      } //for i

      this.CLEAR_HOUSE_LIST();
      this.$store.dispatch("deleteHouse");
      if (this.gugunCode) this.getHouseList(this.dongCode);
    },
    insertInterest() {
      http
        .post("/user/interest", null, {
          params: {
            id: this.loginUser.id,
            gugunCode: this.dongCode,
            dongName: this.dongName,
          },
        })
        .then((response) => {
          console.log(response);
          this.$store.dispatch("setInterests", this.loginUser);
        })
        .then((response) => {
          console.log(response);
          this.heart = 1;
        });
    }, //insertInterest
    removeInterest() {
      http
        .delete("/user/interest", {
          params: {
            id: this.loginUser.id,
            gugunCode: this.gugunCode,
            dongName: this.dongName,
          },
        })
        .then(() => {
          this.$store.dispatch("setInterests", this.loginUser);
        })
        .then(() => {
          this.heart = 0;
        });
    }, //removeInterest
  },
};
</script>

<style>
.h {
  margin-top: 10px;
  size: 15px;
}

.h:hover {
  cursor: pointer;
}
</style>

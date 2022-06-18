<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    <house-list-item
      v-for="(house, index) in displayedHouses"
      :key="index"
      :house="house"
    />
    <!--페이징 시작-->
    <nav aria-label="Page navigation example">
      <ul class="pagination">
        <li class="page-item" id="prev">
          <button
            type="button"
            class="page-link"
            v-if="page != 1"
            @click="page--"
          >
            Previous
          </button>
        </li>
        <li class="page-item">
          <button
            type="button"
            class="page-link"
            v-for="pageNumber in pages.slice(page - 1, page + 5)"
            :key="pageNumber"
            @click="page = pageNumber"
          >
            {{ pageNumber }}
          </button>
        </li>
        <li class="page-item">
          <button
            type="button"
            @click="page++"
            v-if="page < pages.length"
            class="page-link"
          >
            Next
          </button>
        </li>
      </ul>
    </nav>
    <!--페이징 끝-->
    <b-row>&nbsp;</b-row>
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem.vue";
import { mapState } from "vuex";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {
      page: 1,
      perPage: 7,
      pages: [],
    };
  },
  computed: {
    ...mapState(["houses"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
    displayedHouses() {
      return this.paginate(this.houses);
    },
  }, //computed
  watch: {
    houses() {
      this.setPages();
    },
  },
  methods: {
    setPages() {
      this.pages = [];
      let numberOfPages = Math.ceil(this.houses.length / this.perPage);
      for (let i = 1; i <= numberOfPages; i++) {
        this.pages.push(i);
      }
    }, //setPages
    paginate(houses) {
      let page = this.page;
      let perPage = this.perPage;
      let from = page * perPage - perPage;
      let to = page * perPage;
      return houses.slice(from, to);
    }, //paginate
  }, //methods
  filters: {
    //페이징
    trimWords(value) {
      return value.split("").splice(0, 20).join(" ") + "...";
    },
  }, //filters
};
</script>

<style>
button.page-link {
  display: inline-block;
}
button.page-link {
  font-size: 20px;
  color: #29b3ed;
  font-weight: 500;
}
.offset {
  width: 500px !important;
  margin: 20px auto;
}

#prev {
  left: 11px;
}
</style>

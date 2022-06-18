<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3>
      <b-icon icon="house-door-fill" animation="fade"></b-icon> HappyHouse
    </h3>
    <b-row>&nbsp;</b-row>

    <!--slide-->
    <vueper-slides
      ref="myVueperSlides"
      autoplay
      :pause-on-hover="pauseOnHover"
      @autoplay-pause="internalAutoPlaying = false"
      @autoplay-resume="internalAutoPlaying = true"
      fixed-height="500px"
    >
      <vueper-slide
        v-for="slide in slides"
        :key="slide.id"
        :title="slide.title"
        :content="slide.content"
        :image="slide.image"
        :link="slide.link"
      />
    </vueper-slides>

    <!-- 검색 버튼 -->
    <b-row>&nbsp;</b-row>
    <b-row>
      <b-col cols="4"></b-col>

      <b-col cols="3">
        <b-form-input
          id="input-small"
          size="sm"
          placeholder="키워드를 통해 뉴스를 검색해보세요"
          v-model="keyword"
          @keyup.enter="showNews"
        ></b-form-input>
      </b-col>
      <b-col cols="0">
        <h5>
          <b-icon
            icon="search"
            animation="fade"
            id="dod"
            @click="showNews"
          ></b-icon></h5
      ></b-col>
    </b-row>

    <b-row>&nbsp;</b-row>

    <!-- 검색한 뉴스 제공 -->

    <b-row v-for="(item, index) in news" :key="index">
      <b-col cols="12">
        <b-card :header-html="item.title" class="mb-1 card_head" no-body>
          <b-card-body class="text-left card_body">
            <a :href="item.link" target="_blank" class="newsBody">
              <span v-html="item.description"></span
            ></a>
            <div id="pubdate">{{ item.pubDate | getDate }}</div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="11"></b-col>
      <a href="#input-small"
        ><h3><b-icon icon="search" id="dod2"></b-icon></h3
      ></a>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import { VueperSlides, VueperSlide } from "vueperslides";
import img from "@/assets/home.png";
import "vueperslides/dist/vueperslides.css";

export default {
  name: "HomeView",
  data() {
    return {
      keyword: null,
      pauseOnHover: true,
      autoPlaying: true,
      internalAutoPlaying: true,
      image: img,
      slides: [
        {
          id: "slide-1",
          image: require("@/assets/home2.png"),
        },
        {
          id: "slide-2",
          image: require("@/assets/apt.jpg"),
        },
        {
          id: "slide-3",
          image: require("@/assets/adver.png"),
          link: "https://www.ssafy.com/ksp/jsp/swp/swpMain.jsp",
        },
      ], //slides
    }; //return
  }, //data
  components: {
    VueperSlides,
    VueperSlide,
  },
  props: {
    msg: String,
  },
  computed: {
    ...mapState(["news"]),
  },
  created() {
    this.$store.dispatch("setNews", "아파트 매매");
  },
  methods: {
    showNews() {
      this.$store.dispatch("setNews", this.keyword);
      this.keyword = "";
    }, //showNews
  }, //methods
  filters: {
    getDate(today) {
      let time = new Date(today);

      let month = time.getMonth() + 1;
      if (month >= 0 && month < 10) month = "0" + month;

      let date = time.getDate();
      if (date >= 0 && date < 10) date = "0" + date;

      let hour = time.getHours();
      if (hour >= 0 && hour < 10) hour = "0" + hour;

      let minute = time.getMinutes();
      if (minute >= 0 && minute < 10) minute = "0" + minute;

      return (
        time.getUTCFullYear() +
        "." +
        month +
        "." +
        date +
        " " +
        hour +
        ":" +
        minute
      );
    },
  }, //filters
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
#img {
  width: 1110px;
}

.card_head {
  background-color: rgb(187, 229, 250);
  font-size: 20px;
}

.card_body {
  background-color: #fcf7f8;
  font-size: 15px;
}
#pubdate {
  text-align: right;
  font-size: 14px;
  color: darkgrey;
}
#input-small {
  margin-left: 28px;
}
#dod:hover {
  cursor: pointer;
}
#dod {
  margin-top: 4px;
  margin-left: 22px;
}
#dod2 {
  margin-left: 45px;
}
.newsBody {
  color: rgb(19, 19, 134);
}
</style>

<template>
  <b-container class="bv-example-row mt-3">
    <div v-if="loginUser">
      <b-row>
        <b-col>
          <b-alert show
            ><h3>
              <b-icon icon="question-square" animation="cylon"></b-icon> QnA
              게시판
              <b-icon icon="question-square" animation="cylon"></b-icon></h3
          ></b-alert>
        </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col class="text-right">
          <h1>
            <b-icon
              icon="pencil-square"
              @click="moveWrite()"
              id="pencil"
            ></b-icon>
          </h1>
        </b-col>
      </b-row>
      <b-row>
        <b-col v-if="articles.length">
          <b-table-simple hover responsive striped>
            <b-thead head-variant="dark">
              <b-tr>
                <b-th>글번호</b-th>
                <b-th>제목</b-th>
                <b-th>작성자</b-th>
                <b-th>작성일</b-th>
              </b-tr>
            </b-thead>
            <tbody>
              <board-list-item
                v-for="article in displayedArticles"
                :key="article.no"
                v-bind="article"
              />
            </tbody>
          </b-table-simple>
        </b-col>
      </b-row>
      <!--페이지 네비게이션-->
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
    </div>
    <div v-else>
      <b-row>&nbsp;</b-row>
      <b-alert show variant="danger"><h4>접근 권한이 없습니다.</h4></b-alert>
      <b-row>&nbsp;</b-row>
      <b-row>&nbsp;</b-row>
      <b-row>&nbsp;</b-row>
      <b-row>&nbsp;</b-row>
      <b-row>&nbsp;</b-row>
      <b-row>&nbsp;</b-row>
      <b-row>&nbsp;</b-row>
      <b-row>&nbsp;</b-row>
      <b-row>&nbsp;</b-row>
      <b-row>&nbsp;</b-row>
      <b-row>&nbsp;</b-row>
      <b-row>&nbsp;</b-row>
      <b-row>&nbsp;</b-row>
    </div>
    <b-row>&nbsp;</b-row>
    <b-row>&nbsp;</b-row>
    <b-row>&nbsp;</b-row>
    <b-row>&nbsp;</b-row>
    <b-row>&nbsp;</b-row>
    <b-row>&nbsp;</b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";
import BoardListItem from "@/components/board/item/BoardListItem";
import { mapState } from "vuex";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      articles: [],
      ///페이징영역///
      page: 1,
      perPage: 10,
      pages: [],
      ///페이징영역///
    };
  },
  computed: {
    ...mapState({ loginUser: (state) => state.loginUser }),
    ///페이징///
    displayedArticles() {
      return this.paginate(this.articles);
    },
  },
  watch: {
    //페이징
    articles() {
      this.setPages();
    },
  },
  created() {
    http.get(`/board`).then(({ data }) => {
      this.articles = data;
      this.setPages();
    });
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
    ///페이징///
    setPages() {
      this.pages = [];
      let numberOfPages = Math.ceil(this.articles.length / this.perPage);
      for (let i = 1; i <= numberOfPages; i++) {
        this.pages.push(i);
      }
    }, //setPages
    paginate(articles) {
      let page = this.page;
      let perPage = this.perPage;
      let from = page * perPage - perPage;
      let to = page * perPage;
      return articles.slice(from, to);
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

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
#pencil:hover {
  cursor: pointer;
  color: rgb(205, 214, 189);
}

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
  margin-left: 450px;
}
</style>

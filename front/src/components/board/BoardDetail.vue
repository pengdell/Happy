<template>
  <div>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <b-alert show><h1>Question</h1></b-alert>
        </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col class="text-left">
          <h1>
            <b-icon icon="card-list" class="icon" @click="listArticle"></b-icon>
          </h1>
        </b-col>
        <b-col class="text-right">
          <h1>
            <b-icon
              icon="pencil"
              class="icon"
              @click="moveModifyArticle"
            ></b-icon>
            <span>&nbsp;&nbsp;</span>
            <b-icon icon="trash" class="icon" @click="deleteArticle"></b-icon>
          </h1>
        </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col>
          <b-card
            :header-html="`<h4>
            ${article.subject}</h4>
            <span style='float:right; font-size:16px'>${article.userid}</span><br>
            <span style='color:darkgrey; float:right; font-size:16px'>${article.regtime}</span>`"
            class="mb-2 card_head"
            no-body
          >
            <b-card-body class="text-left card_body">
              <div v-html="message" style="font-size: 18px"></div>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
      <b-row>&nbsp;</b-row>
      <b-row>&nbsp;</b-row>
      <b-row>&nbsp;</b-row>
      <b-row>
        <b-col>
          <b-alert show variant="warning" class="answer_head"
            ><h3>Answers</h3></b-alert
          >
        </b-col>
      </b-row>
    </b-container>

    <!--입력폼-->
    <comment-write :boardno="this.boardno" />
    <!--수정폼-->
    <comment-write
      v-if="isModifyShow && this.modifyComment != null"
      :modifyComment="this.modifyComment"
      @modify-comment-cancel="onModifyCommentCancel"
    />
    <comment
      v-for="(comment, index) in comments"
      :key="index"
      :answer="comment"
      @modify-comment="onModifyComment"
    />
    <!--:answer props로 데이터를 넘기는 부분-->
  </div>
</template>

<script>
// import moment from "moment";
import { mapGetters } from "vuex";
import http from "@/api/http";
import CommentWrite from "@/components/board/comment/CommentWrite.vue";
import Comment from "@/components/board/comment/Comment.vue";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
      boardno: "",
      isModifyShow: false,
      modifyComment: Object,
    };
  },
  computed: {
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
    ...mapGetters(["comments"]),
  },
  components: {
    CommentWrite,
    Comment,
  },
  created() {
    this.boardno = this.$route.params.no;

    http.get(`/board/${this.$route.params.no}`).then(({ data }) => {
      this.article = data;
    });

    this.$store.dispatch("getComments", `/answer/${this.boardno}`);
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "boardModify",
        params: { no: this.article.no },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "boardDelete",
          params: { no: this.article.no },
        });
      }
    },
    onModifyComment(comment) {
      this.modifyComment = comment;
      this.isModifyShow = true;
    },
    onModifyCommentCancel(isShow) {
      this.isModifyShow = isShow;
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style>
.icon:hover {
  cursor: pointer;
  color: rgb(229, 217, 241);
}
.card_head {
  background-color: #eee5f8;
}
.card_body {
  font-size: 20px;
  background-color: #f1e8f5;
}
</style>

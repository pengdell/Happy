<template>
  <div v-show="isShow" class="comment">
    <div class="head">{{ answer.userid }} ({{ answer.regtime }})</div>
    <div class="content" v-html="enterToBr(answer.content)"></div>
    <!-- 로그인 기능 구현 후 로그인한 자신의 글에만 보이게 한다. -->
    <div v-if="loginUser.id == answer.userid">
      <div class="cbtn">
        <b-icon icon="pen" class="icon" @click="modifyCommentView"></b-icon
        >update
        <b-icon icon="trash" class="icon" @click="deleteComment"></b-icon>delete
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";

export default {
  name: "comm-ent",
  data() {
    return {
      isShow: true,
    };
  },
  computed: {
    ...mapState({ loginUser: (state) => state.loginUser }),
  },
  props: {
    answer: Object,
  },
  methods: {
    modifyCommentView() {
      this.$emit("modify-comment", {
        no: this.answer.no,
        content: this.answer.content,
        boardno: this.answer.boardno,
      });
    },
    deleteComment() {
      if (confirm("삭제를 진행할까요?")) {
        http.delete(`/answer/${this.answer.no}`).then(({ data }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data === "SUCCESS") {
            msg = "삭제가 완료되었습니다.";
          }
          alert(msg);
          // 도서평(댓글) 얻기.
          this.$store.dispatch("getComments", `/answer/${this.answer.boardno}`);
        });
      }
    },
    enterToBr(str) {
      if (str) return str.replace(/(?:\r\n|\r|\n)/g, "<br />");
    },
  },
};
</script>

<style>
.comment {
  text-align: left;
  border-radius: 5px;
  background-color: #d6e7fa;
  padding: 10px 20px;
  margin: 10px;
}
.head {
  font-weight: bold;
  margin-bottom: 5px;
}
.content {
  padding: 5px;
}
.cbtn {
  text-align: right;
  color: steelblue;
  margin: 5px 0px;
}
</style>

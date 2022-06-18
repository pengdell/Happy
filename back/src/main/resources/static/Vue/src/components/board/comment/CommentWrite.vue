<template>
  <div class="regist">
    <div v-if="this.modifyComment != null" class="regist_form">
      <textarea
        id="comment"
        name="comment"
        v-model="tmp.content"
        cols="40"
        rows="2"
      ></textarea>
      <b-icon icon="x-lg" class="icon" @click="updateCommentCancel"></b-icon>
      <span>&nbsp;</span>
      <b-icon icon="pen" class="icon" @click="updateComment"></b-icon>
    </div>
    <div v-else class="regist_form">
      <textarea
        id="comment"
        name="comment"
        v-model="content"
        cols="35"
        rows="2"
      ></textarea>
      <span>
        <b-icon icon="pencil" id="submit" @click="registComment"></b-icon>
      </span>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";

export default {
  name: "comment-write",
  data() {
    return {
      // 차후 작성자 이름은 로그인 구현후 로그인한 사용자로 바꾼다.
      // userid: "안효인",
      content: "",
    };
  },
  computed: {
    ...mapState({ userid: (state) => state.loginUser.id }),
    tmp() {
      return this.modifyComment;
    },
  },
  props: {
    boardno: { type: String },
    modifyComment: { type: Object },
  },
  methods: {
    registComment() {
      http
        .post("/answer", {
          userid: this.userid,
          content: this.content,
          boardno: this.boardno,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "SUCCESS") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);

          // 작성글 지우기
          this.content = "";

          // 도서평(댓글) 얻기.
          this.$store.dispatch("getComments", `/answer/${this.boardno}`);
        });
    },
    updateComment() {
      http
        .put(`/answer`, {
          no: this.modifyComment.no,
          content: this.modifyComment.content,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "SUCCESS") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);

          // 도서평(댓글) 얻기.
          this.$store.dispatch(
            "getComments",
            // eslint-disable-next-line
            `/answer/${this.modifyComment.boardno}`
          );

          this.$emit("modify-comment-cancel", false);
        });
    },
    updateCommentCancel() {
      this.$emit("modify-comment-cancel", false);
    },
  },
};
</script>

<style scoped>
.regist {
  padding: 10px;
}
.regist_form {
  text-align: left;
  border-radius: 5px;
  background-color: #d6e7fa;
  padding: 10px;
}

textarea {
  width: 95%;
  padding: 10px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  color: #787878;
  font-size: large;
}

.icon {
  font-size: 18px;
  margin-left: 6px;
  margin-bottom: 20px;
}

button {
  float: right;
}

#submit {
  margin-left: 12px;
  margin-bottom: 20px;
  font-size: 28px;
}

button.small {
  width: 60px;
  font-size: small;
  font-weight: bold;
}
</style>

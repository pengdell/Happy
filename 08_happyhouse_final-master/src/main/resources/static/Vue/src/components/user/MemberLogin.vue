<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show
          ><h3><b-icon icon="person-square"></b-icon> &nbsp;로그인</h3></b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <b-form-group label="아이디:" label-for="id">
              <b-form-input
                id="id"
                v-model="user.id"
                required
                placeholder="아이디를 입력하세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="pw">
              <b-form-input
                type="password"
                id="pw"
                v-model="user.pw"
                required
                placeholder="비밀번호를 입력하세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-button
              type="button"
              variant="outline-primary"
              class="m-1"
              @click="confirm"
              >로그인</b-button
            >
            <b-button
              type="button"
              variant="outline-success"
              class="m-1"
              @click="movePage"
              >회원가입</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
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
  </b-container>
</template>

<script>
import http from "@/api/http";

export default {
  name: "MemberLogin",
  data() {
    return {
      isLoginError: false,
      user: {
        id: "",
        pw: "",
      },
    };
  },
  methods: {
    //로그인 버튼을 눌러 로그인하기
    confirm() {
      http
        .get(`/user/login`, {
          params: {
            id: this.user.id,
            pw: this.user.pw,
          },
        })
        .then((response) => {
          console.log(response);
          let msg = "";
          if (response.data != "FAIL") {
            msg = "로그인 성공!!";

            const loginUser = {
              id: response.data.id,
              pw: response.data.pw,
              name: response.data.name,
              address: response.data.address,
              phone: response.data.phone,
            };

            this.$store.dispatch("setLoginUser", loginUser);
            this.$store.dispatch("setInterests", loginUser); //관심지역 가져오기
            this.isLoginError = false;
            alert(msg);
            this.$router.push({ name: "home" });
          } //if
          else {
            this.isLoginError = true;
          }
        });
    },
    movePage() {
      this.$router.push({ name: "signUp" });
    },
  },
};
</script>

<style></style>

<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원정보 조회</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group label="아이디:" label-for="id">
              <b-form-input
                id="id"
                v-model="loginUser.id"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
                readonly
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="pw">
              <b-form-input
                type="password"
                id="pw"
                v-model="loginUser.pw"
                required
                placeholder="비밀번호를 입력하세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름:" label-for="name">
              <b-form-input
                id="name"
                v-model="loginUser.name"
                required
                placeholder="이름을 입력하세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="주소:" label-for="address">
              <b-form-input
                id="address"
                v-model="loginUser.address"
                required
                placeholder="주소를 입력하세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="전화번호:" label-for="phone">
              <b-form-input
                id="phone"
                v-model="loginUser.phone"
                required
                placeholder="전화번호를 입력하세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-button
              type="button"
              variant="outline-primary"
              class="m-1"
              @click="updateUser"
              >수정</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";

export default {
  name: "MemberModify",
  computed: {
    ...mapState(["loginUser"]),
  },
  methods: {
    updateUser() {
      http
        .put(`/user/modify`, null, {
          params: {
            id: this.loginUser.id,
            pw: this.loginUser.pw,
            name: this.loginUser.name,
            address: this.loginUser.address,
            phone: this.loginUser.phone,
          },
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제 발생가 발생했습니다";
          if (data === "SUCCESS") {
            msg = "수정이 완료되었습니다.";
            this.$store.dispatch("setLoginUser", this.loginUser);
            this.$router.push({ name: "userDetail" });
          }
          alert(msg);
        });
    }, //remove
  }, //methods
}; //export
</script>

<style></style>

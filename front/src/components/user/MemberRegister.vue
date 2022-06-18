<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show
          ><h3>
            <b-icon icon="person-plus-fill"></b-icon> &nbsp;회원가입
          </h3></b-alert
        >
      </b-col>
    </b-row>

    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left" @submit="onSubmit">
            <b-form-group label="아이디:" label-for="id">
              <b-form-input
                id="id"
                v-model="id"
                required
                placeholder="아이디를 입력하세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="pw">
              <b-form-input
                type="password"
                id="pw"
                v-model="pw"
                required
                placeholder="비밀번호를 입력하세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름:" label-for="name">
              <b-form-input
                id="name"
                v-model="name"
                required
                placeholder="이름을 입력하세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="주소:" label-for="address">
              <b-form-input
                id="address"
                v-model="address"
                required
                placeholder="주소를 입력하세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="전화번호:" label-for="phone">
              <b-form-input
                id="phone"
                v-model="phone"
                required
                placeholder="전화번호를 입력하세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-button type="submit" variant="outline-success" class="m-1"
              >회원가입</b-button
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

export default {
  name: "MemberRegister",
  data() {
    return {
      id: "",
      pw: "",
      name: "",
      address: "",
      phone: "",
    };
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      this.regist();
    },

    regist() {
      http
        .post(`/user/regist`, null, {
          params: {
            id: this.id,
            pw: this.pw,
            name: this.name,
            address: this.address,
            phone: this.phone,
          },
        })
        .then(({ data }) => {
          let msg = "";
          if (data === "SUCCESS") {
            msg = "회원가입 성공!";
            this.$router.push({ name: "home" });
          } else msg = "입력 정보를 확인해주세요";
          alert(msg);
        });
    },
  },
};
</script>

<style></style>

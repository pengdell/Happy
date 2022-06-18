<template>
  <div>
    <b-navbar id="navbar">
      <!-- primary, info, warning, danger, dark, or light. -->
      <b-navbar-brand href="#">
        <router-link to="/">
          <img
            src="@/assets/ssafy_icon.png"
            class="d-inline-block align-middle"
            width="100px"
            alt="ssafy"
          />
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'home' }" class="link"
              ><b-icon icon="emoji-heart-eyes" font-scale="1.5"></b-icon>
              홈</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'board' }" class="link"
              ><b-icon icon="chat-right-quote" font-scale="1.5"></b-icon>
              QnA</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'house' }" class="link"
              ><b-icon icon="emoji-smile" font-scale="1.5"></b-icon>
              아파트정보</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto">
          <!--관심지역-->
          <b-nav-item-dropdown right v-if="loginUser">
            <template #button-content>
              <b-icon icon="bookmark-heart-fill" font-scale="2"></b-icon>
            </template>

            <b-dropdown-item v-for="(interest, index) in interests" :key="index"
              ><router-link
                :to="{
                  name: 'houseinterest',
                  params: {
                    gugunCode: interest.gugunCode,
                    dongName: interest.dongName,
                  },
                }"
                class="link"
                ><b-icon icon="plus-circle" font-scale="1"></b-icon>&nbsp;{{
                  interest.dongName
                }}</router-link
              >
            </b-dropdown-item>
          </b-nav-item-dropdown>
          <!---->

          <b-nav-item-dropdown right>
            <template #button-content>
              <b-icon icon="person-bounding-box" font-scale="2"></b-icon>
            </template>
            <b-dropdown-item href="#" v-if="!loginUser"
              ><router-link :to="{ name: 'signUp' }" class="link"
                ><b-icon icon="person-plus-fill"></b-icon> 회원가입</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#" v-else
              ><router-link :to="{ name: 'userDetail' }" class="link"
                ><b-icon icon="zoom-in"></b-icon> 회원정보</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#" v-if="!loginUser"
              ><router-link :to="{ name: 'signIn' }" class="link"
                ><b-icon icon="key-fill"></b-icon> 로그인</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#" v-else @click="logout"
              ><router-link :to="{ name: 'home' }" class="link"
                ><b-icon icon="person-dash"></b-icon> 로그아웃</router-link
              ></b-dropdown-item
            >
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "HeaderNaviBar",
  computed: {
    ...mapState(["loginUser", "interests"]),
  },
  methods: {
    logout() {
      this.$store.dispatch("deleteLoginUser");
    },
  },
};
</script>

<style>
#navbar {
  background-color: skyblue;
}
.link {
  color: black;
}
</style>

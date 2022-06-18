import Vue from "vue";
import Vuex from "vuex";
import http from "@/api/http";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    comments: [],
    loginUser: null,
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    houses: [],
    house: null,
    sidoName: null,
    gugunName: null,
    dongName: null,
    news: [],
    chargers: [],
    foods: [],
    services: [],
    interests: [],
  },
  getters: {
    comments(state) {
      return state.comments;
    },
    loginUser(state) {
      return state.loginUser;
    },
  },
  mutations: {
    setComments(state, payload) {
      state.comments = payload;
    },
    //로그인
    setLoginUser(state, payload) {
      state.loginUser = payload;
    },
    //로그아웃, 탈퇴
    deleteLoginUser(state) {
      state.loginUser = null;
    },
    //관심지역 얻어오기
    SET_INTERESTS(state, payload) {
      state.interests = payload;
    },
    /////////////////////////////// House start /////////////////////////////////////
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_SIDO_NAME(state, sidoName) {
      state.sidoName = sidoName;
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_GUGUN_NAME(state, gugunName) {
      state.gugunName = gugunName;
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },

    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_HOUSE_LIST(state) {
      state.houses = [];
    },
    CLEAR_HOUSE(state) {
      state.house = null;
    },
    SET_DETAIL_HOUSE(state, house) {
      state.house = house;
    },

    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },

    SET_DONGNAME(state, name) {
      state.dongName = name;
    },
    DELETE_HOUSE(state) {
      state.house = null;
    },
    CLEAR_DONG_NAME(state) {
      state.dongName = "역삼동";
    },

    /////////////////////////////// House end /////////////////////////////////////

    ///////////////////////////////News /////////////////////
    SET_NEWS(state, news) {
      state.news = news;
    },
    ///////////////////Charger////////////////
    SET_CHARGERS(state, chargers) {
      state.chargers = chargers;
    },
    /////////Foods//////////
    SET_FOODS(state, foods) {
      state.foods = foods;
    },
    ////////////////////////Services/////////////////
    SET_SERVICES(state, services) {
      state.services = services;
    },
  },
  actions: {
    getComments(context, payload) {
      http.get(payload).then(({ data }) => {
        context.commit("setComments", data);
      });
    },
    //로그인
    setLoginUser({ commit }, loginUser) {
      commit("setLoginUser", loginUser);
    },
    //로그아웃, 탈퇴
    deleteLoginUser({ commit }) {
      commit("deleteLoginUser");
    },
    setInterests({ commit }, loginUser) {
      http.get("/user/interest/" + loginUser.id).then((response) => {
        commit("SET_INTERESTS", response.data);
      });
    },
    /////////////////////////////// House start /////////////////////////////////////

    setSidoName({ commit }, sidoName) {
      commit("SET_SIDO_NAME", sidoName);
    },
    setGugunName({ commit }, gugunName) {
      commit("SET_GUGUN_NAME", gugunName);
    },
    getSido({ commit }) {
      http
        .get(`/house/sido`)
        .then(({ data }) => {
          commit("SET_SIDO_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun({ commit }, sidoCode) {
      const params = { sido: sidoCode };
      http
        .get(`/house/gugun`, { params })
        .then(({ data }) => {
          commit("SET_GUGUN_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getDong({ commit }, addr) {
      const params = { gugunName: addr.gugunName, sidoName: addr.sidoName };
      http
        .get(`/house/dong`, { params })
        .then(({ data }) => {
          commit("SET_DONG_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async getHouseList({ commit, state }, dongCode) {
      if (!dongCode) {
        return;
      }

      // vue cli enviroment variables 검색
      //.env.local file 생성.
      // 반드시 VUE_APP으로 시작해야 한다.
      // const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      const SERVICE_KEY =
        "MhMh57LWgXnx3qki11elLSnh0BunyqfqFkA%2FqunNw42jnFQf308NMYBbqk8b0XzYGPBOR7sTw8VZ3UhAjW6RtA%3D%3D";
      const SERVICE_URL =
        "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
      const gugunCode = dongCode.substring(0, 5);
      const params = {
        LAWD_CD: gugunCode,
        DEAL_YMD: "202203",
        numOfRows: 100,
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      http
        .get(SERVICE_URL, { params })
        .then(({ data }) => {
          for (i = 0; i < state.dongs.length; i++) {
            if (state.dongs[i].value == dongCode) {
              // commit
              commit("SET_DONGNAME", state.dongs[i].text);
              break;
            }
          }

          const variable = data.response.body.items.item;
          for (var i in variable) {
            if (variable[i].법정동.trim() == state.dongName.trim()) {
              state.houses.push(variable[i]);
            }
          }
          commit("SET_HOUSE_LIST", state.houses);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    detailHouse({ commit }, house) {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
    deleteHouse({ commit }) {
      commit("DELETE_HOUSE");
    },
    clearDongName({ commit }) {
      commit("CLEAR_DONG_NAME");
    },
    /////////////////////////////// House end /////////////////////////////////////

    ///////////////////////////////News /////////////////////
    setNews({ commit }, keyword) {
      http.get(`/news/+${keyword}`).then(({ data }) => {
        commit("SET_NEWS", data);
      });
    }, //setNews
    /////////////////Chargers ////////////
    setChargers({ commit }, sidoCode) {
      if (!sidoCode) {
        return;
      }

      const SERVICE_KEY =
        "XjvtWcdVPCK83W0FrKNeJhYCKBqvkqIvikqKT3dcrB3bQoupKdgyMav1j9%2FoK%2FUwGNN8jeiQimO4M%2BgJ4OBYDQ%3D%3D";
      const SERVICE_URL =
        "https://apis.data.go.kr/B552584/EvCharger/getChargerInfo";

      const params = {
        ServiceKey: decodeURIComponent(SERVICE_KEY),
        pageNo: 1,
        numOfRows: 200,
        zcode: sidoCode,
      };
      http.get(SERVICE_URL, { params }).then(({ data }) => {
        commit("SET_CHARGERS", data.items[0].item);
      });
    }, //setChargers
    ////////////////Foods/////////////////////////
    setFoods({ commit }, gugunCode) {
      if (!gugunCode) {
        return;
      }

      const SERVICE_KEY =
        "XjvtWcdVPCK83W0FrKNeJhYCKBqvkqIvikqKT3dcrB3bQoupKdgyMav1j9%2FoK%2FUwGNN8jeiQimO4M%2BgJ4OBYDQ%3D%3D";
      const SERVICE_URL =
        "http://apis.data.go.kr/B553077/api/open/sdsc2/storeListInDong";

      const params = {
        ServiceKey: decodeURIComponent(SERVICE_KEY),
        pageNo: 1,
        numOfRows: 200,
        divId: "signguCd",
        key: gugunCode,
        indsLclsCd: "Q",
        type: "json",
      };
      http.get(SERVICE_URL, { params }).then(({ data }) => {
        commit("SET_FOODS", data.body.items);
      });
    },
    ////////////setServices///////////////////////////////////////
    setServices({ commit }, gugunCode) {
      if (!gugunCode) {
        return;
      }

      const SERVICE_KEY =
        "XjvtWcdVPCK83W0FrKNeJhYCKBqvkqIvikqKT3dcrB3bQoupKdgyMav1j9%2FoK%2FUwGNN8jeiQimO4M%2BgJ4OBYDQ%3D%3D";
      const SERVICE_URL =
        "http://apis.data.go.kr/B553077/api/open/sdsc2/storeListInDong";

      const params = {
        ServiceKey: decodeURIComponent(SERVICE_KEY),
        pageNo: 1,
        numOfRows: 200,
        divId: "signguCd",
        key: gugunCode,
        indsLclsCd: "F",
        type: "json",
      };
      http.get(SERVICE_URL, { params }).then(({ data }) => {
        commit("SET_SERVICES", data.body.items);
      });
    },
  },
  modules: {},
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});

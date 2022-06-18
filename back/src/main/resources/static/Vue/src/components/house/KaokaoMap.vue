<template>
  <div id="mapwrap">
    <div id="map"></div>
    <div class="category">
      <ul>
        <li id="store" :class="{ selected: btn2 }" @click="Btn2">
          <h3><b-icon icon="cup-straw"></b-icon></h3>
        </li>
        <li id="service" :class="{ selected: btn3 }" @click="Btn3">
          <h3><b-icon icon="shop"></b-icon></h3>
        </li>
        <li id="charger" :class="{ selected: btn1 }" @click="Btn1">
          <h3><b-icon icon="plug"></b-icon></h3>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
import image from "@/assets/b.png";
import image2 from "@/assets/ssafy_logo.png";
import image3 from "@/assets/carm.png"; //전기자동차 마커
import image4 from "@/assets/food.png"; //음식점 마커
import image5 from "@/assets/shop.png"; //가게 마커
//import { mapMutations } from "vuex";

export default {
  name: "KakaoMap",
  data() {
    return {
      markers: [],
      infowindow: null,
      btn1: false,
      btn2: false,
      btn3: false,
      location: null,
      interestDong: null,
    };
  },
  computed: {
    ...mapState([
      "house",
      "houses",
      "dongName",
      "chargers",
      "sidoName",
      "foods",
      "gugunName",
      "services",
    ]),
  },
  watch: {
    house(addr) {
      this.location = addr;
      this.initMap(addr);
    },
    dongName(newDong) {
      this.btn1 = false;
      this.btn2 = false;
      this.location = newDong;
      this.initMap(newDong);
    },
    sidoName() {
      this.btn1 = false; //새로운 시도를 검색하면 버튼 리셋
      this.btn2 = false;
      this.btn3 = false;
    },
    gugunName() {
      this.btn1 = false; //새로운 구를 검색하면 버튼 리셋
      this.btn2 = false;
      this.btn3 = false;
    },
    houses() {
      this.initMap();
    },
    //관심지역 검색///
    $route(newRoute) {
      let gugunCode = newRoute.params.gugunCode;
      //state의 동이름부터 바꾸자
      this.SET_DONGNAME(newRoute.params.dongName);
      this.CLEAR_HOUSE_LIST();
      this.$store.dispatch("deleteHouse");
      this.getHouseList(gugunCode);
      this.$store.dispatch("setChargers", gugunCode.slice(0, 2));
      this.$store.dispatch("setFoods", gugunCode.slice(0, 5));
      this.$store.dispatch("setServices", gugunCode.slice(0, 5));
    }, //route
  },
  created() {
    //this.$store.dispatch("clearDongName");
    let gugunCode = this.$route.params.gugunCode;
    if (gugunCode) {
      //state의 동이름부터 바꾸자
      this.SET_DONGNAME(this.$route.params.dongName);
      this.CLEAR_HOUSE_LIST();
      this.$store.dispatch("deleteHouse");
      this.getHouseList(gugunCode);
      this.$store.dispatch("setChargers", gugunCode.slice(0, 2));
      this.$store.dispatch("setFoods", gugunCode.slice(0, 5));
      this.$store.dispatch("setServices", gugunCode.slice(0, 5));
    }
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=014f573628962c21d2216e9a1829c586&libraries=services&autoload=false";
      document.head.appendChild(script);
    }
  },
  methods: {
    ...mapActions(["getSido", "getGugun", "getDong", "getHouseList"]),

    ...mapMutations([
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_HOUSE_LIST",
      "SET_DONGNAME",
    ]),
    initMap(addr) {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      //집이 1개
      if (addr != null && this.house) {
        options = {
          center: new kakao.maps.LatLng(33.450701, 126.570667),
          level: 4,
        };
      }
      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      var map = new kakao.maps.Map(container, options);

      ///주소를 위도 경도 세팅
      let geocoder = new kakao.maps.services.Geocoder();
      var imageSrc = image;
      var imageSize = new kakao.maps.Size(72, 72); // 마커이미지의 크기입니다
      var imageOption = { offset: new kakao.maps.Point(45, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      var markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption /* eslint-disable */
      );
      var markerImage2 = new kakao.maps.MarkerImage(
        image2,
        imageSize,
        imageOption /* eslint-disable */
      );
      imageSize = new kakao.maps.Size(36, 36); // 마커이미지의 크기입니다
      var markerImage3 = new kakao.maps.MarkerImage(
        image3,
        imageSize,
        imageOption /* eslint-disable */
      );

      imageSize = new kakao.maps.Size(36, 36); // 마커이미지의 크기입니다
      var markerImage4 = new kakao.maps.MarkerImage(
        image4,
        imageSize,
        imageOption /* eslint-disable */
      );
      imageSize = new kakao.maps.Size(32, 40); // 마커이미지의 크기입니다
      var markerImage5 = new kakao.maps.MarkerImage(
        image5,
        imageSize,
        imageOption /* eslint-disable */
      );

      //충전소 나타내기
      if (this.btn1) {
        this.chargers.forEach(function (charger) {
          let coords = new kakao.maps.LatLng(charger.lat, charger.lng);

          var marker = new kakao.maps.Marker({
            position: coords,
            clickable: true,
            image: markerImage3,
            map: map,
          });

          let type = "";
          if (charger.type == "01") type = "DC차데모";
          else if (charger.type == "02") type = "AC완속";
          else if (charger.type == "03") type = "DC차데모+AC3상";
          else if (charger.type == "04") type = "DC콤보";
          else if (charger.type == "05") type = "DC차데모+DC콤보";
          else if (charger.type == "06") type = "DC차데모+AC3상+DC콤보";
          else type = "AC상";

          var content = `<div class="customoverlay3" >
                    <div  class="title">${charger.statNm}</div>
                      <div class="title">충전기 타입: ${type} </div>
                      <div class="title">이용시간: ${charger.useTime}</div>
                      </div>`;

          var overlay = new kakao.maps.CustomOverlay({
            position: coords,
            content: content,
          });
          kakao.maps.event.addListener(marker, "mouseover", function () {
            overlay.setMap(map);
          });
          kakao.maps.event.addListener(marker, "mouseout", function () {
            overlay.setMap(null);
          });
        }); //foreach
      } //btn1

      //주변 상권 정보 나타내기
      if (this.btn2) {
        this.foods.forEach(function (food) {
          let coords = new kakao.maps.LatLng(food.lat, food.lon);

          var marker = new kakao.maps.Marker({
            position: coords,
            clickable: true,
            image: markerImage4,
            map: map,
          });
          var content = `<div class="customoverlay4">
                        <div class="title">${food.bizesNm}</div>
                        <div class="title">${food.indsSclsNm}</div>
                        </div>`;

          var overlay = new kakao.maps.CustomOverlay({
            position: coords,
            content: content,
          });
          kakao.maps.event.addListener(marker, "mouseover", function () {
            overlay.setMap(map);
          });
          kakao.maps.event.addListener(marker, "mouseout", function () {
            overlay.setMap(null);
          });
        }); //foreach
      } //btn2

      ///주변 생활서비스시설 나타내기
      if (this.btn3) {
        this.services.forEach(function (service) {
          let coords = new kakao.maps.LatLng(service.lat, service.lon);

          var marker = new kakao.maps.Marker({
            position: coords,
            clickable: true,
            image: markerImage5,
            map: map,
          });
          var content = `<div class="customoverlay5">
                        <div class="title">${service.bizesNm}</div>
                        <div class="title">${service.indsSclsNm}</div>
                        </div>`;

          var overlay = new kakao.maps.CustomOverlay({
            position: coords,
            content: content,
          });
          kakao.maps.event.addListener(marker, "mouseover", function () {
            overlay.setMap(map);
          });
          kakao.maps.event.addListener(marker, "mouseout", function () {
            overlay.setMap(null);
          });
        }); //foreach
      } //btn3

      //특정 집 한개만 나타내기
      if (addr != null && this.house) {
        geocoder.addressSearch(
          (this.house.법정동 + " " + this.house.지번).trim(),
          function (result, status) {
            if (status == kakao.maps.services.Status.OK) {
              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

              // 결과값으로 받은 위치를 마커로 표시합니다
              var marker = new kakao.maps.Marker({
                position: coords,
                image: markerImage,
              });
              marker.setMap(map);
              map.setCenter(coords);
            } //if
          } /* eslint-disable */
        ); //geocorder
      } //if

      //모든 집 리스트를 지도에 나타내기
      else if (this.houses.length > 0) {
        for (let i = 0; i < this.houses.length; i++) {
          geocoder.addressSearch(
            (this.houses[i].법정동 + " " + this.houses[i].지번).trim(),
            function (result, status) {
              if (status == kakao.maps.services.Status.OK) {
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                // 결과값으로 받은 위치를 마커로 표시합니다
                var marker = new kakao.maps.Marker({
                  position: coords,
                  image: markerImage,
                });
                marker.setMap(map);
                map.setCenter(coords);
              } //if
            } /* eslint-disable */
          ); //geocorder
        } //for i

        ////집 여러개를 나타내고 한 좌표애서 알려주기
        geocoder.addressSearch(
          this.dongName,
          function (result, status) {
            if (status == kakao.maps.services.Status.OK) {
              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

              var marker = new kakao.maps.Marker({
                map: map,
                position: coords,
                image: markerImage,
              });

              let content = `<div class="customoverlay2">
                <span class="title">${result[0].address_name}</span>
              </div>`;

              var customOverlay = new kakao.maps.CustomOverlay({
                content: content,
                position: coords,
                map: map,
                yAnchor: 1,
              });
              // map.setCenter(coords);
            } //if
          } /* eslint-disable */
        ); //geocorder
      } //else if

      //새로고침했을 때
      else if (this.dongName == "역삼동") {
        options = {
          center: new kakao.maps.LatLng(33.450701, 126.570667),
          level: 2,
        };

        map = new kakao.maps.Map(container, options);
        geocoder.addressSearch(
          this.dongName + " 테헤란로 212",
          function (result, status) {
            if (status == kakao.maps.services.Status.OK) {
              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

              var marker = new kakao.maps.Marker({
                map: map,
                position: coords,
                image: markerImage2,
              });

              let content = `<div class="customoverlay">
                <span class="title">멀티캠퍼스</span>
              </div>`;

              var customOverlay = new kakao.maps.CustomOverlay({
                content: content,
                position: coords,
                map: map,
                yAnchor: 1,
              });
              //    customOverlay.open(map, marker);

              map.setCenter(coords);
            } //if
          } /* eslint-disable */
        ); //geocorder
      }

      //검색하고 집이 없을 때
      else {
        geocoder.addressSearch(
          this.dongName,
          function (result, status) {
            if (status == kakao.maps.services.Status.OK) {
              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

              var marker = new kakao.maps.Marker({
                map: map,
                position: coords,
                image: markerImage,
              });

              let content = `<div class="customoverlay2">
                <span class="title">${result[0].address_name}</span>
              </div>`;

              var customOverlay = new kakao.maps.CustomOverlay({
                content: content,
                position: coords,
                map: map,
                yAnchor: 1,
              });
              map.setCenter(coords);
            } //if
          } /* eslint-disable */
        ); //geocorder
      } //else
      map.addOverlayMapTypeId(kakao.maps.MapTypeId.TRAFFIC);
    }, //initMap

    // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
    makeOverListener(map, marker, infowindow) {
      return function () {
        infowindow.open(map, marker);
      };
    },

    // 인포윈도우를 닫는 클로저를 만드는 함수입니다
    makeOutListener(infowindow) {
      return function () {
        infowindow.close();
      };
    },

    Btn1() {
      //1번 버튼 - 전기충전소 지도
      this.btn1 = !this.btn1;
      this.initMap(this.location);
    },

    Btn2() {
      //2번 버튼 - 상권정보 지도
      this.btn2 = !this.btn2;
      this.initMap(this.location);
    },

    Btn3() {
      //3번 버튼 - 의료시설 지도
      this.btn3 = !this.btn3;
      this.initMap(this.location);
    },

    //////////////////////////////////////////////////////////////////////////////////
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position) /* eslint-disable */
      );

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }

      var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });

      this.map.setCenter(iwPosition);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 1200px;
  height: 680px;
}
#mapwrap {
  position: relative;
  overflow: hidden;
}
.category,
.category * {
  margin: 0;
  padding: 0;
  color: #000;
}
.category {
  position: absolute;
  overflow: hidden;
  top: 10px;
  left: 10px;
  width: 150px;
  height: 45px;
  z-index: 10;
  /*border: 1px solid black;*/
  background-color: #faf8c8;
}
.category .selected {
  background: rgb(160, 240, 240);
  color: #fff;
}
.category li {
  list-style: none;
  float: left;
  width: 50px;
  height: 45px;
  padding-top: 5px;
  cursor: pointer;
}
</style>

<style>
/* 지도 인포윈도우 */
.customoverlay {
  position: relative;
  right: 7px;
  bottom: 58px;
  border-radius: 8px;
  border: 1px solid rgb(19, 18, 18);
  /*border-bottom: 2px solid #ddd;*/
  float: left;
  background-color: rgb(234, 245, 245);
  width: 90px;
  height: 30px;
}

.customoverlay .title {
  display: block;
  text-align: center;
  padding: 5px 8px;
  font-size: 14px;
  font-weight: bold;
}

.customoverlay2 {
  position: relative;
  right: 15px;
  bottom: 68px;
  border-radius: 8px;
  border: 1px solid rgb(19, 18, 18);
  float: left;
  width: 155px;
  background-color: rgb(234, 245, 245);
}
.customoverlay2 .title {
  display: block;
  text-align: center;
  padding: 5px 8px;
  font-size: 14px;
  font-weight: bold;
}

.customoverlay3 {
  position: relative;
  right: 18px;
  bottom: 125px;
  border-radius: 8px;
  border: 1px solid rgb(19, 18, 18);
  float: left;
  width: 300px;
  height: 100px;
  background-color: rgb(229, 250, 205);
}
.customoverlay3 .title {
  display: block;

  padding: 5px 8px;
  font-size: 14px;
  font-weight: bold;
}

.customoverlay4 {
  position: relative;
  right: 22px;
  bottom: 105px;
  border-radius: 8px;
  border: 1px solid rgb(19, 18, 18);
  float: left;
  width: 200px;
  height: 60px;
  background-color: rgb(250, 242, 205);
}
.customoverlay4 .title {
  display: block;
  padding: 5px 8px;
  font-size: 14px;
  font-weight: bold;
}

.customoverlay5 {
  position: relative;
  right: 25px;
  bottom: 105px;
  border-radius: 8px;
  border: 1px solid rgb(19, 18, 18);
  float: left;
  width: 200px;
  height: 60px;
  background-color: rgb(248, 242, 251);
}
.customoverlay5 .title {
  display: block;
  padding: 5px 8px;
  font-size: 14px;
  font-weight: bold;
}
</style>

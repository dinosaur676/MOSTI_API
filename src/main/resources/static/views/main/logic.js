import {init as currencyInit} from "../component/currencies.js";
import {init as progressInit} from "../component/charts/progress.js";
import {init as compareInit} from "../component/charts/compare.js";
import {init as statsInit} from "../component/charts/statistics.js";
import {init as geoInit} from "../component/charts/geo.js";

export const logic = {
    init: async function () {
        currencyInit([{name: "euro", icon: "€", value: 321.12, delta: "-0.2", color: "#1CA1C1"}]);
        progressInit();
        compareInit();
        statsInit();
        geoInit();
        await logic.openLayersInit()
    },
    sseInit: function(){
        this.eventSource = new EventSource("/api/sse/redis/stream-flux");
        this.currentMap.addSseEventListener(this.eventSource);
        this.eventSource.onerror = function (event) {
            console.log(event);
            console.log('connection state: ' + this.eventSource.readyState + ', error: ');
        };
    },
    eventSource:null,
    currentMap:null,
    convertMap: function(type){
        this.eventSource?.close()
        this.currentMap.destroy()
        if(type==='kakao'){
            this.kakaoInit();
        }else if(type === 'naver'){
            this.naverInit();
        }else if(type === 'openlayers'){
            this.openLayersInit();
        }
    },
    naverInit:async function(){
        const {drawNaverMap} = await import("../component/maps/naver_maps/naver_map.js")
        this.currentMap = new drawNaverMap();
        this.currentMap.init('divMap');

    },
    kakaoInit:async function(){
        const {drawKakaoMap} = await import("../component/maps/kakao_maps/kakao_map.js")
        this.currentMap = new drawKakaoMap()
        this.currentMap.init('divMap');
        this.sseInit()
    },
    openLayersInit:async function(){
        const {drawOpenMap} = await import("../component/maps/openlayers/dist/draw_open_layer.mjs");
        this.currentMap = new drawOpenMap()
        this.currentMap.init('divMap');
        this.sseInit()
    },

}

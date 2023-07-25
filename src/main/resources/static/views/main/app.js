import {CurrenciesView} from "../component/currencies.js";
import {ProgressView} from "../component/charts/progress.js";
import {CompareView} from "../component/charts/compare.js";
import {GeoView} from "../component/charts/geo.js";
import {StatisticsView} from "../component/charts/statistics.js";
import {logic} from "./logic.js";

export const mainView = {
    id: "mainView",
    type: "space",
    view:"scrollview",
    body:{
      type:"space",
      rows: [
        { type:"wide",
          cols:[
            {view:"template", template: '<div id="divMap" style="height: inherit;width: inherit"></div>', height: 800},
            { rows: [
                { view: "button", label: "naver", type: "form", width: 80, align: "right",
                  css:"webix_primary",
                  click: function() {
                  console.log('naver')
                    logic.convertMap('naver')
                  }
                },

                { view: "button", label: "kakao", type: "form", width: 80, align: "right",
                  css:"webix_primary",
                  click: function() {
                    logic.convertMap('kakao')
                  }
                },
                { view: "button", label: "openlayers", type: "form", width: 80, align: "right",
                  css:"webix_primary",
                  click: function() {
                    logic.convertMap('openlayers')
                  }
                },
                { view: "button", label: "info", type: "form", width: 80, align: "right",
                  css:"webix_primary",
                  click: function() {
                    logic.currentMap.getInfo()
                  }
                },
                { view: "button", label: "위성지도", type: "form", width: 80, align: "right",
                  css:"webix_primary",
                  click: function() {
                    logic.currentMap.convertSatellite()
                  }
                },
              ]},
          ]
        },
        /*{ type:"wide", cols:[
            {view:"template", template: '<div id="divOpenLayer" style="height: inherit;width: inherit"></div>', height: 800},
            { rows: [
                { view: "button", label: "저장", type: "form", width: 80, align: "right",
                  css:"webix_primary",
                  click: function() {

                  }
                },
            ]},
          ]
        },*/
        {
          type:"wide",
          cols:[ CurrenciesView, ProgressView ]
        },
          StatisticsView
        ,
        { type:"wide", cols:[
            GeoView, CompareView,
          ]}
        ,

      ]
    }
};
